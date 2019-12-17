package edu.zju.ccnt.health.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseInfoDTO {
    private Integer id;
    private String service_name;
    private Integer category_id;
    private Integer checked;
    private Integer user_id;
    private String remarks;
    private Date create_time;
}
