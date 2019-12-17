package edu.zju.ccnt.health.service.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zth
 * @date 2019.4.13
 * 服务列表
 */
@Data
public class SearchInfoVo {
    private Integer total;
    private List<BaseInfoVo> list;
}
