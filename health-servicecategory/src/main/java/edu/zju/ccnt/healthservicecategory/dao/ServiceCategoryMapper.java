package edu.zju.ccnt.healthservicecategory.dao;

import edu.zju.ccnt.healthservicecategory.model.ServiceCategory;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ServiceCategoryMapper {
    /**
     *generator by mybatis
     */
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceCategory record);

    int insertSelective(ServiceCategory record);

    ServiceCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceCategory record);

    int updateByPrimaryKey(ServiceCategory record);

    /**
     * @param parentId
     * @return 根据parentId搜索符合条件的服务类别列表
     */
    List<ServiceCategory> selectByParentId(@RequestParam("parent_id") Integer parentId);
}