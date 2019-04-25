package edu.zju.ccnt.health.service.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zth
 * @date 2019.4.13
 * 服务基本信息
 */
@Data
public class BaseInfoVo {
    private Integer id;
    private String serviceName;
    private String serviceCategory;
    private Integer checked;
    private String owner;
    private String createTime;
}
