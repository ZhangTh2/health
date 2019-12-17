package edu.zju.ccnt.health.servicecategory.service;

import java.util.List;

/**
 * @author zth
 * @date 2019.4.13
 */
public interface IServiceCategoryApiService {
    /**
     * @param id
     * @return 该id下的所有子服务分类
     */
    List<Integer> getChildrenById(Integer id);

    /**
     * @param id
     * @return 服务分类名
     */
    String getNameById(Integer id);
}
