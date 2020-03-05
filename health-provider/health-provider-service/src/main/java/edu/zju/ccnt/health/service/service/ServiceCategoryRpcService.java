package edu.zju.ccnt.health.service.service;

import edu.zju.ccnt.health.provider.service.IServiceCategoryFeignApi;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ServiceCategoryRpcService {
    @Resource
    private IServiceCategoryFeignApi iServiceCategoryFeignApi;

    public List<Integer> getsubcategory(Integer id) {
        return iServiceCategoryFeignApi.getsubcategory(id);
    }

    public String getname(Integer id) {
        return iServiceCategoryFeignApi.getname(id);
    }

}
