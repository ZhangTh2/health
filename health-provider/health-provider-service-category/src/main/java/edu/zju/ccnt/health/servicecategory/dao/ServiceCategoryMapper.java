package edu.zju.ccnt.health.servicecategory.dao;

import edu.zju.ccnt.health.servicecategory.model.ServiceCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ServiceCategoryMapper {
    /**
     * generator by mybatis
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
    List<ServiceCategory> selectByParentId(@Param("parent_id") Integer parentId);

    /**
     * 假删除，将parent_id置为-1
     *
     * @param id
     * @return 影响条数
     */
    int removeById(@Param("id") Integer id);


}