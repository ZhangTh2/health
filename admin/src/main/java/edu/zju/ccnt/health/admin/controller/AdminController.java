package edu.zju.ccnt.health.admin.controller;

import edu.zju.ccnt.health.admin.response.ServerResponse;
import edu.zju.ccnt.health.admin.security.JwtUtil;
import edu.zju.ccnt.health.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService iAdminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse login(@RequestBody Map<String,String> map){
        String username=map.get("username");
        String password=map.get("password");
        return iAdminService.login(username,password);
    }


    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    public ServerResponse getInfo(@RequestParam String token) {
        return iAdminService.getInfo(token);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ServerResponse logout() {
        return iAdminService.logout();
    }

}
