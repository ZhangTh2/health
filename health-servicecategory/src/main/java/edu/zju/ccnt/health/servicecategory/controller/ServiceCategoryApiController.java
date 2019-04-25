package edu.zju.ccnt.health.servicecategory.controller;


import edu.zju.ccnt.health.servicecategory.service.IServiceCategoryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/serviceCategoryApi")
public class ServiceCategoryApiController {
    @Autowired
    private IServiceCategoryApiService iServiceCategoryApiService;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getsubcategory",method = RequestMethod.GET)
    public List<Integer> getsubcategory(@RequestParam Integer id) {
        return  iServiceCategoryApiService.getChildrenById(id);
    }

    @RequestMapping(value = "/getname",method = RequestMethod.GET)
    public String getname(@RequestParam Integer id) {
        return  iServiceCategoryApiService.getNameById(id);
    }
}
