package edu.zju.ccnt.health.servicecategory.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zth
 * @date 2019.3.12
 * 供界面展示的服务类别对象
 */
@Data
public class ServiceCategoryVO {
    private int id;
    private String label;
    private Integer level;
    private List<ServiceCategoryVO> children = new ArrayList<>();
}
