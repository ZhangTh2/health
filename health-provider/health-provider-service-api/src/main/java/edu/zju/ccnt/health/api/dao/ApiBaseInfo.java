package edu.zju.ccnt.health.api.dao;

import lombok.Data;

import java.util.Date;

/**
 * 基本的表单中展示数据
 *
 * @author zth
 * @date 2019.4.24
 */
@Data
public class ApiBaseInfo {
    private Integer id;
    private String name;
    private Integer status;
    private Date create_time;
    private Date update_time;

    public String toString() {
        return name + " status:" + status + " createTime:" + create_time + " updateTime:" + update_time;
    }
}
