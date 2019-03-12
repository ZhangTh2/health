package edu.zju.ccnt.healthservicecategory.service.Impl;

import edu.zju.ccnt.healthservicecategory.dao.ServiceCategoryMapper;
import edu.zju.ccnt.healthservicecategory.model.ServiceCategory;
import edu.zju.ccnt.healthservicecategory.response.ServerResponse;
import edu.zju.ccnt.healthservicecategory.service.IServiceCategoryService;
import edu.zju.ccnt.healthservicecategory.vo.ServiceCategoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zth
 * @date 2019.3.12
 */
@Service("ServiceCategoryImpl")
public class ServiceCategoryImpl implements IServiceCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCategoryImpl.class);
    private ServiceCategoryMapper serviceCategoryMapper;
    public ServerResponse<List<ServiceCategoryVO>> listCategories(){
        logger.info("获取服务类别列表");
        List<ServiceCategoryVO> serviceCategoryVOList = new ArrayList<>();
        List<ServiceCategory> serviceCategoryList1 = serviceCategoryMapper.selectByParentId(0);
        for (ServiceCategory serviceCategory1 : serviceCategoryList1){
            ServiceCategoryVO serviceCategoryVO1 = new ServiceCategoryVO();
            serviceCategoryVO1.setServiceCategory(serviceCategory1);
            List<ServiceCategory> serviceCategoryList2 = serviceCategoryMapper.selectByParentId(serviceCategory1.getId());
            for (ServiceCategory serviceCategory2 :serviceCategoryList2) {
                ServiceCategoryVO serviceCategoryVO2 = new ServiceCategoryVO();
                serviceCategoryVO2.setServiceCategory(serviceCategory2);
                List<ServiceCategory> serviceCategoryList3 = serviceCategoryMapper.selectByParentId(serviceCategory2.getId());
                for(ServiceCategory serviceCategory3 : serviceCategoryList3){
                    ServiceCategoryVO serviceCategoryVO3 = new ServiceCategoryVO();
                    serviceCategoryVO3.setServiceCategory(serviceCategory3);
                    serviceCategoryVO3.setChilds(null);
                    serviceCategoryVO2.getChilds().add(serviceCategoryVO3);
                }
                serviceCategoryVO1.getChilds().add(serviceCategoryVO2);
            }
            serviceCategoryVOList.add(serviceCategoryVO1);
        }
        return ServerResponse.createBySuccess(serviceCategoryVOList);
    }
}
