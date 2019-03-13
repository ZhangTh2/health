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

    /**
     * 更新商品分类
     * @param id
     * @param name
     * @return 状态码
     */
    ServerResponse update(Integer id,String name);

    /**
     * 添加商品分类
     * @param parentId
     * @param name
     * @param level
     * @return 状态码
     */
    ServerResponse create(Integer parentId,String name,Integer level);

    /**
     * 删除服务分类
     * @param id
     * @return 状态码
     */
    ServerResponse remove(Integer id);
}

