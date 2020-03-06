package edu.zju.ccnt.health.api.model.vo;

import lombok.Data;

/**
 * 基本的表单中展示数据
 *
 * @author zth
 * @date 2019.4.24
 */
@Data
public class ApiBaseInfoVO {
    private Integer id;
    private String name;
    private Integer status;
    private String createTime;
    private String updateTime;
}
