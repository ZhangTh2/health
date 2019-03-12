package edu.zju.ccnt.healthservicecategory.controller;

import edu.zju.ccnt.healthservicecategory.response.ServerResponse;
import edu.zju.ccnt.healthservicecategory.service.IServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zth
 * @date 2019.3.12
 */

@RestController
@RequestMapping("/serviceCategory")
public class ServiceCategoryController {
    @Autowired
    private IServiceCategoryService iServiceCategoryService;

    /**
     * 获取所有服务类别
     * @return 包含三级的服务分类列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ServerResponse list() {
        return  iServiceCategoryService.listCategories();
    }
}
