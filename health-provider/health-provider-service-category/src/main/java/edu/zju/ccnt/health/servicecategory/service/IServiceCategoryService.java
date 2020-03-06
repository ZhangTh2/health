package edu.zju.ccnt.health.servicecategory.service;

import edu.zju.ccnt.health.servicecategory.model.vo.ServiceCategoryVO;
import edu.zju.ccnt.health.response.ServerResponse;
import edu.zju.ccnt.health.servicecategory.model.vo.ServiceVo;

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
     * @return 服务添加管理页面的战术数据
     */
    ServerResponse<List<ServiceVo>> listcategoriesTwo();

    /**
     * 更新服务分类
     *
     * @param id
     * @param name
     * @return 状态码
     */
    ServerResponse update(Integer id, String name);

    /**
     * 添加子服务分类
     *
     * @param parentId
     * @param name
     * @param level
     * @return 状态码
     */
    ServerResponse create(Integer parentId, String name, Integer level);

    /**
     * 删除服务分类
     *
     * @param id
     * @return 状态码
     */
    ServerResponse remove(Integer id);

    /**
     * 添加大的服务分类
     *
     * @param name
     * @return status
     */
    ServerResponse insert(String name);


}

