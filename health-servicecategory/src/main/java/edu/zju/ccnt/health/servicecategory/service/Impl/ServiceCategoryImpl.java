package edu.zju.ccnt.health.servicecategory.service.Impl;

import edu.zju.ccnt.health.servicecategory.service.IServiceCategoryService;
import edu.zju.ccnt.health.servicecategory.vo.ServiceCategoryVO;
import edu.zju.ccnt.health.servicecategory.vo.ServiceVo;
import edu.zju.ccnt.health.servicecategory.vo.ServiceVo1;
import edu.zju.ccnt.health.servicecategory.dao.ServiceCategoryMapper;
import edu.zju.ccnt.health.servicecategory.model.ServiceCategory;
import edu.zju.ccnt.health.servicecategory.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zth
 * @date 2019.3.12
 */
@Service("iServiceCategoryService")
public class ServiceCategoryImpl implements IServiceCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCategoryImpl.class);

    @Autowired
    private ServiceCategoryMapper serviceCategoryMapper;

    public ServerResponse<List<ServiceCategoryVO>> listCategories(){
        logger.info("获取服务类别列表");
        List<ServiceCategoryVO> serviceCategoryVOList = new ArrayList<>();
        List<ServiceCategory> serviceCategoryList1 = serviceCategoryMapper.selectByParentId(0);
        for (ServiceCategory serviceCategory1 : serviceCategoryList1){
            ServiceCategoryVO serviceCategoryVO1 = new ServiceCategoryVO();
            serviceCategoryVO1.setId(serviceCategory1.getId());
            serviceCategoryVO1.setLabel(serviceCategory1.getName());
            serviceCategoryVO1.setLevel(serviceCategory1.getLevel());
            List<ServiceCategory> serviceCategoryList2 = serviceCategoryMapper.selectByParentId(serviceCategory1.getId());
            for (ServiceCategory serviceCategory2 :serviceCategoryList2) {
                ServiceCategoryVO serviceCategoryVO2 = new ServiceCategoryVO();
                serviceCategoryVO2.setId(serviceCategory2.getId());
                serviceCategoryVO2.setLabel(serviceCategory2.getName());
                serviceCategoryVO2.setLevel(serviceCategory2.getLevel());
                List<ServiceCategory> serviceCategoryList3 = serviceCategoryMapper.selectByParentId(serviceCategory2.getId());
                for(ServiceCategory serviceCategory3 : serviceCategoryList3){
                    ServiceCategoryVO serviceCategoryVO3 = new ServiceCategoryVO();
                    serviceCategoryVO3.setId(serviceCategory3.getId());
                    serviceCategoryVO3.setLabel(serviceCategory3.getName());
                    serviceCategoryVO3.setLevel(serviceCategory3.getLevel());
                    serviceCategoryVO2.getChildren().add(serviceCategoryVO3);
                }
                serviceCategoryVO1.getChildren().add(serviceCategoryVO2);
            }
            serviceCategoryVOList.add(serviceCategoryVO1);
        }
        return ServerResponse.createBySuccess(serviceCategoryVOList);
    }

    public ServerResponse<List<ServiceVo> > listcategoriesTwo() {
        logger.info("服务添加管理页获取服务类别列表");
        List<ServiceVo> serviceVOList = new ArrayList<>();
        List<ServiceCategory> serviceCategoryList1 = serviceCategoryMapper.selectByParentId(0);
        for (ServiceCategory serviceCategory1 : serviceCategoryList1){

            List<ServiceCategory> serviceCategoryList2 = serviceCategoryMapper.selectByParentId(serviceCategory1.getId());
            if(serviceCategoryList2.size()==0) {
                ServiceVo serviceVO1 = new ServiceVo();
                serviceVO1.setValue(serviceCategory1.getId());
                serviceVO1.setLabel(serviceCategory1.getName());
                serviceVOList.add(serviceVO1);
                continue;
            }
            ServiceVo1 serviceVo1 = new ServiceVo1();
            serviceVo1.setValue(serviceCategory1.getId());
            serviceVo1.setLabel(serviceCategory1.getName());
            for (ServiceCategory serviceCategory2 :serviceCategoryList2) {
                List<ServiceCategory> serviceCategoryList3 = serviceCategoryMapper.selectByParentId(serviceCategory2.getId());
                if(serviceCategoryList3.size()==0){
                    ServiceVo serviceVO2 = new ServiceVo();
                    serviceVO2.setValue(serviceCategory2.getId());
                    serviceVO2.setLabel(serviceCategory2.getName());
                    serviceVo1.getChildren().add(serviceVO2);
                    continue;
                }
                ServiceVo1 serviceVo2 = new ServiceVo1();
                serviceVo2.setValue(serviceCategory2.getId());
                serviceVo2.setLabel(serviceCategory2.getName());
                for(ServiceCategory serviceCategory3 : serviceCategoryList3){
                    ServiceVo serviceVo3 = new ServiceVo();
                    serviceVo3.setValue(serviceCategory3.getId());
                    serviceVo3.setLabel(serviceCategory3.getName());
                    serviceVo2.getChildren().add(serviceVo3);
                }
                serviceVo1.getChildren().add(serviceVo2);
            }
            serviceVOList.add(serviceVo1);
        }
        return ServerResponse.createBySuccess(serviceVOList);
    }

    public ServerResponse update(Integer id, String name){
        logger.info("更新"+id+"的商品分类信息");
        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setId(id);
        serviceCategory.setName(name);
        serviceCategory.setGmtModified(new Timestamp(System.currentTimeMillis()));
        try{
            serviceCategoryMapper.updateByPrimaryKeySelective(serviceCategory);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    /**
     * 添加子分类的服务
     * @param parentId
     * @param name
     * @param level
     * @return
     */
    public ServerResponse create(Integer parentId,String name,Integer level) {
        logger.info("添加"+name+"分类信息");
        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setParentId(parentId);
        serviceCategory.setName(name);
        serviceCategory.setLevel(level);
        serviceCategory.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        try{
            serviceCategoryMapper.insertSelective(serviceCategory);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    /**
     * 先简单做一下假删除，置parentId为-1,应该考虑如果这个分类及其子分类下有商品的话不应该删除这个分类
     */
    public ServerResponse remove(Integer id){
        try {
            serviceCategoryMapper.removeById(id);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    /**
     * 添加大分类的服务
     * @param name
     * @return
     */
    public ServerResponse insert(String name){
        logger.info("添加"+name+"大类");
        try {
            ServiceCategory serviceCategory = new ServiceCategory();
            serviceCategory.setName(name);
            serviceCategory.setParentId(0);
            serviceCategory.setLevel(1);
            serviceCategory.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            serviceCategoryMapper.insertSelective(serviceCategory);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }


}
