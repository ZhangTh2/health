package edu.zju.ccnt.health.admin.service.Impl;

import edu.zju.ccnt.health.admin.dao.AdminMapper;
import edu.zju.ccnt.health.admin.model.Admin;
import edu.zju.ccnt.health.admin.response.ServerResponse;
import edu.zju.ccnt.health.admin.security.JwtUtil;
import edu.zju.ccnt.health.admin.service.IAdminService;
import edu.zju.ccnt.health.admin.vo.BaseInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
