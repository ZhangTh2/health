package edu.zju.ccnt.healthservicecategory.vo;

import edu.zju.ccnt.healthservicecategory.model.ServiceCategory;
import lombok.Data;

import java.util.List;

/**
 * @author zth
 * @date 2019.3.12
 * 供界面展示的服务类别对象
 */
@Data
public class ServiceCategoryVO {
    private ServiceCategory serviceCategory;
    private List<ServiceCategoryVO> childs;
}
