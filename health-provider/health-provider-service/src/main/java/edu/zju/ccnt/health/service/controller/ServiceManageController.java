package edu.zju.ccnt.health.service.controller;

import edu.zju.ccnt.health.service.model.domin.Service;
import edu.zju.ccnt.health.service.service.IServiceService;
import edu.zju.ccnt.health.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/serviceManage")
public class ServiceManageController {

    @Autowired
    private IServiceService iServiceService;

    @RequestMapping(value = "/fetchServiceList", method = RequestMethod.POST)
    public ServerResponse search(@RequestBody Map<String, String> query) {
        Integer type = Integer.valueOf(query.get("type"));
        String serviceName = query.get("service_name");
        Integer checked;
        if (query.get("checked").isEmpty()) {
            checked = 7;
        } else {
            checked = Integer.valueOf(query.get("checked"));
        }
        Integer categoryId;
        String category = query.get("category_id");
        if (category.isEmpty()) {
            categoryId = -1;
        } else {
            categoryId = Integer.valueOf(category);
        }
        String sort = query.get("sort");
        Integer page = Integer.valueOf(query.get("page"));
        Integer limit = Integer.valueOf(query.get("limit"));
        return iServiceService.listService(type, categoryId, serviceName, checked, sort, page, limit);
    }

//    /**
//     * 管理员新建服务，直接设为已审核
//     * @param service
//     *  serviceName
//     *  serviceImg
//     *  price
//     *  introduction
//     *  detailIntroduction
//     *  type
//     *  category_id
//     *  format
//     * @return
//     */
//    @RequestMapping(value="/createService",method = RequestMethod.POST)
//    public ServerResponse create(@RequestBody Map<String,String> service) {
//        Integer type = Integer.valueOf(service.get("type"));
//        String serviceImg = service.get("serviceImg");
//        BigDecimal price = BigDecimal.valueOf(Double.valueOf(service.get("price")));
//        Integer category_id = Integer.valueOf(service.get("category_id"));
//        String introduction = service.get("introduction");
//        String detailIntroduction = service.get("detailIntroduction");
//        String serviceName = service.get("serviceName");
//        String format = service.get("format");
//        return iServiceService.createService(type,0,category_id,serviceName,1,serviceImg,price,introduction,detailIntroduction,format);
//    }

    @RequestMapping(value = "/createService", method = RequestMethod.POST)
    public ServerResponse create(@RequestBody Service service) {
        return iServiceService.insertService(service);
    }


    @RequestMapping(value = "/updateChecked", method = RequestMethod.GET)
    public ServerResponse insertImg(@RequestParam Integer id, @RequestParam Integer checked) {
        return iServiceService.updateChecked(id, checked);
    }

    @RequestMapping(value = "/unChecked", method = RequestMethod.GET)
    public ServerResponse unCkecked(@RequestParam Integer id, @RequestParam String remarks) {
        return iServiceService.unckeck(id, remarks);
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public ServerResponse<Service> getInfo(@RequestParam Integer id) {
        return iServiceService.getInfo(id);
    }

    @RequestMapping(value = "/updateService", method = RequestMethod.POST)
    public ServerResponse update(@RequestBody Service service) {
        return iServiceService.updateService(service);
    }

    @RequestMapping(value = "/getUncheck", method = RequestMethod.GET)
    public ServerResponse getUncheck() {
        return iServiceService.getUncheck();
    }

    @RequestMapping(value = "/insertImg", method = RequestMethod.POST)
    public ServerResponse insertImg(@RequestBody MultipartFile file) {
        return iServiceService.insertImg(file);
    }

}
