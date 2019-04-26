package edu.zju.ccnt.health.admin.service;

import edu.zju.ccnt.health.admin.model.Admin;
import edu.zju.ccnt.health.admin.response.ServerResponse;
import edu.zju.ccnt.health.admin.vo.BaseInfoVo;
import edu.zju.ccnt.health.admin.vo.DetailInfoVo;
import edu.zju.ccnt.health.admin.vo.SearchInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IAdminService {
    //登录
    ServerResponse<String> login(String username, String password);
    //登出
    ServerResponse<String> logout();
    //由Token获取管理员基本信息
    ServerResponse<BaseInfoVo> getInfo(String token);
    //分类别获取管理员列表
    ServerResponse<Map<String,List<DetailInfoVo> >> fetchList(String type);
    //将管理员离职
    ServerResponse<Integer> delete(Integer id);
    //审核管理员
    ServerResponse<Integer> check(Integer id);
    //验证用户名是否已经被使用
    ServerResponse<Integer> checkName(String name);
    //超级管理员新建的管理员
    ServerResponse createAdmin(Admin admin);
    //条件搜索管理员列表，分页
//  ServerResponse<List<DetailInfoVo> > searchAdminlist(Integer role_id,String username,String name,Integer checked,String sort,Integer page,Integer limit);
    ServerResponse<SearchInfoVo> searchAdminlist(Integer role_id,String username,String name,Integer checked,String sort,Integer page,Integer limit);
    //id获取管理员
    ServerResponse<Admin> getAdmin(Integer id);
    //管理员更新
    ServerResponse update(Admin admin);
    //管理员总数
    ServerResponse<Integer> getToatl();
    //当前登录的管理员的详细信息返回
    ServerResponse<Admin> getSelf(String token);
    //自己申请注册的管理员
    ServerResponse insert(Admin admin);

    /**
     * 获取未审核的管理员
     * @return
     */
    ServerResponse<Map<String,Integer> > getUnchecked();

    /**
     * 将图片上传到ftp服务器
     * @param uploadFile
     * @return
     */
    ServerResponse insertImg( MultipartFile uploadFile);

    /**
     * 更新用户头像
     * @param id
     * @param imgUrl
     * @return
     */
    ServerResponse updateAvata(Integer id,String imgUrl);
}
