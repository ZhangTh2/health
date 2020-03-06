package edu.zju.ccnt.health.admin.model.vo;

import lombok.Data;

/**
 * 页面表单展示的数据
 */
@Data
public class DetailInfoVo {
    private Integer id;
    private String username;
    private String name;
    private String phone;
    private int checked;
    private String email;
}
