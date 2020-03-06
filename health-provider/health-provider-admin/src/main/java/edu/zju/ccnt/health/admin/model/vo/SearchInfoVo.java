package edu.zju.ccnt.health.admin.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchInfoVo {
    private Integer total;
    private List<DetailInfoVo> list;
}
