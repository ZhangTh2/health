package edu.zju.ccnt.health.api.controller;

import edu.zju.ccnt.health.api.model.Api;
import edu.zju.ccnt.health.api.response.ServerResponse;
import edu.zju.ccnt.health.api.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zth
 * @date 2019.4.24
 */
@RestController
@RequestMapping("/ApiManage")
public class ApiManageController {

    @Autowired
    private IApiService iApiService;

    /**
     * 根据serviceId获取服务所有的Api列表
     * @param serviceId
     * @return
     */
    @RequestMapping(value = "/listApi",method = RequestMethod.GET)
    public ServerResponse listApi(@RequestParam Integer serviceId) {
        return  iApiService.listApi(serviceId);
    }

    @RequestMapping(value = "/insertApi",method = RequestMethod.POST)
    public ServerResponse insetApi(@RequestBody Api api) {
        return  iApiService.insertApi(api);
    }

    @RequestMapping(value = "/updateApi",method = RequestMethod.POST)
    public ServerResponse updateApi(@RequestBody Api api) {
        return  iApiService.updateApi(api   );
    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    public ServerResponse getInfo(@RequestParam Integer id) {
        return  iApiService.getInfo(id);
    }

    @RequestMapping(value = "/updateStatus",method = RequestMethod.GET)
    public ServerResponse updateStatus(@RequestParam Integer id,@RequestParam Integer status) {
        return  iApiService.updateStatus(id,status);
    }
}
