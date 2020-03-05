package edu.zju.ccnt.health.servicecategory.controller.frontend;

import edu.zju.ccnt.health.servicecategory.service.IServiceCategoryService;
import edu.zju.ccnt.health.servicecategory.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     *
     * @return 包含三级的服务分类列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse list() {
        return iServiceCategoryService.listCategories();
    }

    /**
     * 服务管理展示页使用的数据
     * 获取所有服务类别
     *
     * @return 包含三级的服务分类列表
     */
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ServerResponse getlist() {
        return iServiceCategoryService.listcategoriesTwo();
    }

    /**s'j'k's
     * 更新操djndcj
     * @param category
     * @return statusCode
     */










    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ServerResponse update(@RequestBody Map<String, String> categoryMap) {
        Integer id = Integer.valueOf(categoryMap.get("id"));
        String name = categoryMap.get("label");
        return iServiceCategoryService.update(id, name);
    }

    /**
     * 插入子分类操作
     *
     * @param categoryMap
     * @return statusCode
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServerResponse create(@RequestBody Map<String, String> categoryMap) {
        Integer parentId = Integer.valueOf(categoryMap.get("id"));
        String name = categoryMap.get("label");
        Integer level = Integer.valueOf(categoryMap.get("level"));
        return iServiceCategoryService.create(parentId, name, level);
    }

    /**
     * 删除操作
     *
     * @param id
     * @return statusCode
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public ServerResponse create(@RequestParam Integer id) {
        return iServiceCategoryService.remove(id);
    }

    /**
     * 添加服务大分类
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
        public ServerResponse insert(@RequestParam String category) {
        return iServiceCategoryService.insert(category);
    }
}


