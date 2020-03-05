package edu.zju.ccnt.health.provider.service;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ServiceCategory-SERVICE")
public interface IServiceCategoryFeignApi {

    @RequestMapping(value = "/serviceCategoryApi/getsubcategory", method = RequestMethod.GET)
    public List<Integer> getsubcategory(@RequestParam("id") Integer id);

    @RequestMapping(value = "/serviceCategoryApi/getname", method = RequestMethod.GET)
    public String getname(@RequestParam("id") Integer id);

}
