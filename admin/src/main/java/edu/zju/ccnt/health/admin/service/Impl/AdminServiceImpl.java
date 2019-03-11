package edu.zju.ccnt.health.admin.service.Impl;

import edu.zju.ccnt.health.admin.dao.AdminMapper;
import edu.zju.ccnt.health.admin.model.Admin;
import edu.zju.ccnt.health.admin.response.ServerResponse;
import edu.zju.ccnt.health.admin.security.JwtUtil;
import edu.zju.ccnt.health.admin.service.IAdminService;
import edu.zju.ccnt.health.admin.vo.BaseInfoVo;
import edu.zju.ccnt.health.admin.vo.DetailInfoVo;
import edu.zju.ccnt.health.admin.vo.SearchInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service("iAdminService")
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    public ServerResponse<String> login(String username, String password) {
        log.info(username+"用户登录");
        Integer adminId = adminMapper.validateAdmin(username,password);
        if(adminId==0) return ServerResponse.createByErrorMessage("用户名或密码错误");
        else
            return ServerResponse.createBySuccess(JwtUtil.getToken(String.valueOf(adminId)));
    }

    //由Token获取管理员基本信息
    public ServerResponse<BaseInfoVo> getInfo(String token) {
        log.info("由Token获取基本信息");
        String adminId = JwtUtil.getIdByToekn(token);
        Admin admin = adminMapper.selectByPrimaryKey(Integer.valueOf(adminId));
        BaseInfoVo baseInfoVo = new BaseInfoVo();
        baseInfoVo.setAdminId(admin.getId());
        baseInfoVo.setRole(admin.getRoleId());
        baseInfoVo.setUsername(admin.getUsername());
        log.info(baseInfoVo.toString());
        return ServerResponse.createBySuccess(baseInfoVo);
    }

    //管理员登出
    public ServerResponse<String> logout() {
        log.info("管理员登出");
        //用redis实现登录失效，暂时未实现
        return ServerResponse.createBySuccess();
    }

    //分类别获取管理员列表
    public ServerResponse<Map<String,List<DetailInfoVo> >> fetchList(String type) {
        log.info("获取"+type+"管理员列表");
        //这里需要做一下权限控制，只有超级管理员可以
        Integer roleId;
        if(type.equals("service"))  roleId = 2;
        else if(type.equals("business")) roleId = 3;
        else return ServerResponse.createByError();
        List<Admin> adminList =adminMapper.getAdminByRole(roleId);
        List<DetailInfoVo> voList = new ArrayList<DetailInfoVo>();
        for(int i = 0;i<adminList.size();i++)
        {
            DetailInfoVo vo =new DetailInfoVo();
            vo.setId(adminList.get(i).getId());
            vo.setUsername(adminList.get(i).getUsername());
            vo.setEmail(adminList.get(i).getEmail());
            vo.setPhone(adminList.get(i).getPhone());
            vo.setName(adminList.get(i).getName());
            vo.setChecked(adminList.get(i).getChecked());
            voList.add(vo);
        }
        Map<String,List<DetailInfoVo> > hashMap=new HashMap<>();
        hashMap.put("list",voList);
        return ServerResponse.createBySuccess(hashMap);
    }

    //将管理员离职
    public ServerResponse<Integer> delete(Integer id) {
        log.info("将编号为"+id+"的管理员删除");
        Integer status = adminMapper.deleteById(id);
        return ServerResponse.createBySuccess(status);
    }

    //审核管理员
    public ServerResponse<Integer> check(Integer id) {
        log.info("审核编号为"+id+"的管理员");
        Integer status = adminMapper.checkById(id);
        return ServerResponse.createBySuccess(status);
    }

    //验证用户名是否已经被使用
    public ServerResponse<Integer> checkName(String name) {
        log.info("查看管理员用户名"+name+"是否存在");
        return ServerResponse.createBySuccess(adminMapper.checkName(name));
    }

//    public ServerResponse<List<DetailInfoVo> > searchAdminlist(Integer role_id,String username,String name,Integer checked,String sort,Integer page,Integer limit) {
//        log.info("根据搜索条件获得adminlist");
//        return ServerResponse.createBySuccess(adminMapper.searchByCondition(role_id,username,name,checked,sort,page,limit));
//    }

    public ServerResponse<SearchInfoVo > searchAdminlist(Integer role_id,String username,String name,Integer checked,String sort,Integer page,Integer limit) {
        log.info("根据搜索条件获得adminlist");
        SearchInfoVo searchInfoVo = new SearchInfoVo();
        searchInfoVo.setList(adminMapper.searchByCondition(role_id,username,name,checked,sort,page,limit));
        searchInfoVo.setTotal(adminMapper.searchtotalByCondition(role_id,username,name,checked));
        return ServerResponse.createBySuccess(searchInfoVo);
    }

    //超级管理员新建的管理员
    public ServerResponse createAdmin(Admin admin) {
        log.info("超级管理员新建的管理员");
        adminMapper.insertSelective(admin);
        return ServerResponse.createBySuccess();
    }

    //id获取管理员
    public ServerResponse<Admin > getAdmin(Integer id) {
        log.info("id获取管理员");
        return ServerResponse.createBySuccess(adminMapper.selectByPrimaryKey(id));
    }

    //超级管理员更新普通管理员
    public ServerResponse update(Admin admin) {
        log.info("更新管理员"+admin.getId());
        return ServerResponse.createBySuccess(adminMapper.updateByPrimaryKeySelective(admin));
    }

    //管理员总数
    public ServerResponse<Integer> getToatl(){
        log.info("获取管理员总数");
        return ServerResponse.createBySuccess(adminMapper.getTotal());
    }

    //当前登录的管理员的详细信息返回
    public ServerResponse<Admin> getSelf(String token) {
        log.info("获取管理员自身的信息");
        return ServerResponse.createBySuccess(adminMapper.selectByPrimaryKey(Integer.valueOf(JwtUtil.getIdByToekn(token))));
    }

}
