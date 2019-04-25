package edu.zju.ccnt.health.servicecategory.service.Impl;

import edu.zju.ccnt.health.servicecategory.dao.ServiceCategoryMapper;
import edu.zju.ccnt.health.servicecategory.model.ServiceCategory;
import edu.zju.ccnt.health.servicecategory.service.IServiceCategoryApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iServiceCategoryApiService")
public class ServiceCategoryApiServiceImpl implements IServiceCategoryApiService {
    private static final Logger logger = LoggerFactory.getLogger(ServiceCategoryImpl.class);

    @Autowired
    private ServiceCategoryMapper serviceCategoryMapper;

    public List<Integer> getChildrenById(Integer id){
        logger.info("查询"+id+"下的所有子分类");
        List<ServiceCategory> serviceCategoryList = serviceCategoryMapper.selectByParentId(id);
        List<Integer> list = new ArrayList<>();
        for(ServiceCategory serviceCategory :serviceCategoryList){
            list.add(serviceCategory.getId());
        }
        return list;
    }

    public String getNameById(Integer id){
        logger.info("查询"+id+"的分类名");
        ServiceCategory serviceCategory = serviceCategoryMapper.selectByPrimaryKey(id);
        return serviceCategory.getName();
    }
}
