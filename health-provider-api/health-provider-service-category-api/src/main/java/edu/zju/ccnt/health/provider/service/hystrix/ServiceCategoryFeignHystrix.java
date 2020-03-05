package edu.zju.ccnt.health.provider.service.hystrix;

import edu.zju.ccnt.health.provider.service.IServiceCategoryFeignApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class ServiceCategoryFeignHystrix implements IServiceCategoryFeignApi {

    @Override
    public List<Integer> getsubcategory(final Integer id) {
        return null;
    }

    @Override
    @RequestMapping(value = "/serviceCategoryApi/getname", method = RequestMethod.GET)
    public String getname(final Integer id){
        return null;
    }
}
