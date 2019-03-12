package edu.zju.ccnt.healthservicecategory.service;

import edu.zju.ccnt.healthservicecategory.response.ServerResponse;
import edu.zju.ccnt.healthservicecategory.vo.ServiceCategoryVO;

import java.util.List;

/**
 * @author zth
 * @date 2019.3.12
 */
public interface IServiceCategoryService {
    /**
     * @return 用于服务类别展示页的所有服务类别的列表
     */
    ServerResponse<List<ServiceCategoryVO>> listCategories();
}
