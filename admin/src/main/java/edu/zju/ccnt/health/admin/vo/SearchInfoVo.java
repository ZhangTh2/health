package edu.zju.ccnt.health.admin.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SearchInfoVo {
    private Integer total;
    private List<DetailInfoVo>  list;
}
