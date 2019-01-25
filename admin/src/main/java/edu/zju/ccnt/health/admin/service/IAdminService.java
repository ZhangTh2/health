package edu.zju.ccnt.health.admin.service;

import edu.zju.ccnt.health.admin.response.ServerResponse;
import edu.zju.ccnt.health.admin.vo.BaseInfoVo;

public interface IAdminService {
    //登录
    ServerResponse<String> login(String username, String password);
    //登出
    ServerResponse<String> logout();
    //由Token获取管理员基本信息
    ServerResponse<BaseInfoVo> getInfo(String token);
}
