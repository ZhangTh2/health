package edu.zju.ccnt.health.admin.model.vo;

import lombok.Data;

/**
 * 供管理员登录后基本信息的显示和权限认证
 */
@Data
public class BaseInfoVo {

    private Integer adminId;

    private String username;

    private Integer role;

    private String avatar;
}
