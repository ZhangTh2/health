package edu.zju.ccnt.health.admin.service.Impl;

import edu.zju.ccnt.health.admin.dao.AdminMapper;
import edu.zju.ccnt.health.admin.model.Admin;
import edu.zju.ccnt.health.admin.response.ServerResponse;
import edu.zju.ccnt.health.admin.security.JwtUtil;
import edu.zju.ccnt.health.admin.service.IAdminService;
import edu.zju.ccnt.health.admin.utils.FtpUtils;
import edu.zju.ccnt.health.admin.utils.ImageIdUtils;
import edu.zju.ccnt.health.admin.vo.BaseInfoVo;
import edu.zju.ccnt.health.admin.vo.DetailInfoVo;
import edu.zju.ccnt.health.admin.vo.SearchInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service("iAdminService")
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     *临时debug 登录待重写
     */
    public ServerResponse<String> login(String username, String password) {
        log.info(username+"用户登录");
        Integer adminId = adminMapper.validateAdmin(username,password);
        if(adminId==null) return ServerResponse.createByErrorMessage("用户名或密码错误");
        else
        {
            Admin admin = adminMapper.selectByPrimaryKey(adminId);
            Integer checked = admin.getChecked();
            if(checked==0)
                return ServerResponse.createByErrorMessage("您的账号未被审核通过");
            else if(checked==1) return ServerResponse.createBySuccess(JwtUtil.getToken(String.valueOf(adminId)));
            else if(checked==2) return ServerResponse.createByErrorMessage("您已经不是本系统的管理员");
            else if(checked==3) return ServerResponse.createByErrorMessage("您的账号审核未通过:"+admin.getRemarks());
            else return ServerResponse.createByErrorMessage("未知错误");
        }
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
        baseInfoVo.setAvatar(admin.getAvatar());
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

    /**
     * 用户自己注册管理员，置为未审核状态
     * @param admin
     * @return status
     */
    public ServerResponse insert(Admin admin) {
        log.info("自己注册的管理员");
        try {
            adminMapper.insertSelective(admin);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            log.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    /**
     * 获取未审核的管理员数量
     * @return {service:num;bussiness:num}
     */
    public ServerResponse<Map<String,Integer> > getUnchecked(){
        log.info("获取未审核的管理员数量");
        Map<String,Integer> result = new HashMap<>();
        Integer number = adminMapper.getUnckeckedNumByRoleId(3);
        result.put("service",number);
        number = adminMapper.getUnckeckedNumByRoleId(2);
        result.put("bussiness",number);
        return ServerResponse.createBySuccess(result);

    }

    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private Integer port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.basepath}")
    private String basepath;
    /**
     * @param uploadFile
     * @return 上传图片是否成功
     */
    public ServerResponse insertImg(MultipartFile uploadFile){
        String oldName = uploadFile.getOriginalFilename();
        log.info("上传图片"+oldName);
        String imgName = ImageIdUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));
        String filepath = new String("/adminAvatarImages");
        String imgUrl = new String("http://192.168.12.110/images/adminAvatarImages/")+imgName;
        try {
            InputStream input = uploadFile.getInputStream();
            boolean result = FtpUtils.uploadFile(host,port,username,password,basepath,filepath,imgName,input);
            if(result) {
                return ServerResponse.createBySuccess(imgUrl);
            }
            else return ServerResponse.createByErrorMessage("图片上传失败");
        }catch (Exception e){
            log.error(e.toString());
            return ServerResponse.createByErrorMessage("图片上传失败");
        }
    }

    /**
     * 更新用户头像
     * @param id
     * @param imgUrl
     * @return
     */
    public ServerResponse updateAvata(Integer id,String imgUrl){
        try{
            adminMapper.updateAvataById(id,imgUrl);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            log.error(e.fillInStackTrace().toString());
            return  ServerResponse.createByError();
        }
    }

    /**
     * 超级管理员审核管理员不通过
     * @param id
     * @param remarks
     * @return
     */
    public ServerResponse uncheckAdmin(Integer id,String remarks){
        Admin admin = new Admin();
        admin.setId(id);
        admin.setRemarks(remarks);
        admin.setChecked(3);
        admin.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try{
            adminMapper.updateByPrimaryKeySelective(admin);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            log.error(e.fillInStackTrace().toString());
            return  ServerResponse.createByError();
        }
    }
}
