package edu.zju.ccnt.health.admin.controller;

import edu.zju.ccnt.health.admin.model.Admin;
import edu.zju.ccnt.health.admin.response.ServerResponse;
import edu.zju.ccnt.health.admin.security.JwtUtil;
import edu.zju.ccnt.health.admin.service.IAdminService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Timestamp;
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

    @RequestMapping(value = "/getTotal",method = RequestMethod.GET)
    public ServerResponse getTotal() {
        return iAdminService.getToatl();
    }

    @RequestMapping(value = "/fetchAdminList",method = RequestMethod.GET)
    public ServerResponse fetchlist(@RequestParam String type) {
        return iAdminService.fetchList(type);
    }
    @RequestMapping(value = "/getAdminById",method = RequestMethod.GET)
    public ServerResponse getById(@RequestParam Integer id) {
        return iAdminService.getAdmin(id);
    }

    @RequestMapping(value = "/deleteAdmin",method = RequestMethod.GET)
    public ServerResponse delete(@RequestParam Integer id) {
        return iAdminService.delete(id);
    }

    @RequestMapping(value = "/checkAdmin",method = RequestMethod.GET)
    public ServerResponse check(@RequestParam Integer id) {
        return iAdminService.check(id);
    }

    @RequestMapping(value = "/searchAdmin",method = RequestMethod.POST)
   // public ServerResponse search(@RequestParam Integer role_id,@RequestParam String username,@RequestParam String name,@RequestParam Integer checked,@RequestParam String sort,@RequestParam Integer page,@RequestParam Integer limit) {
    public ServerResponse search(@RequestBody Map<String,String> query) {
       // System.out.println(query);
        Integer role_id = Integer.valueOf(query.get("role_id"));
       // System.out.println("role_id是"+role_id);
        String username=query.get("username");
        //System.out.println("username是"+username);
        String name = query.get("name");
        //System.out.println("name是"+name);
        Integer checked;
        if(query.get("checked").isEmpty()) {
            checked = 3;
        }else {
            checked = Integer.valueOf(query.get("checked"));
        }
       // System.out.println("checked是"+checked);
        String sort=query.get("sort");
        //System.out.println("sort是"+sort);
        Integer page = Integer.valueOf(query.get("page"));
       // System.out.println("page是"+page);
        Integer limit =Integer.valueOf(query.get("limit"));
       // System.out.println("limit是"+limit);
        return iAdminService.searchAdminlist(role_id,username,name,checked,sort,page,limit);
    }

    @RequestMapping(value = "/checkName",method = RequestMethod.GET)
    public ServerResponse check(@RequestParam String name) {
        return iAdminService.checkName(name);
    }

    @RequestMapping(value = "/createAdmin",method = RequestMethod.POST)
    public ServerResponse create(@RequestBody Map<String,String> adminmap) {
        System.out.println("进来了"+adminmap.toString());
        Admin admin =new Admin();
        admin.setUsername(adminmap.get("username"));
        admin.setPassword(adminmap.get("password"));
        admin.setChecked(Integer.valueOf(adminmap.get("checked")));
        admin.setRoleId(Integer.valueOf(adminmap.get("roleId")));
        admin.setName(adminmap.get("name"));
        admin.setPhone(adminmap.get("phone"));
        admin.setEmail(adminmap.get("email"));
        admin.setBankType(adminmap.get("bankType"));
        admin.setAccountNumber(adminmap.get("accountNumber"));
        admin.setRemarks(adminmap.get("remarks"));
        java.util.Date date =new java.util.Date();
        admin.setCreateTime(new Timestamp(date.getTime()));
        return iAdminService.createAdmin(admin);
    }

    @RequestMapping(value = "/updateAdmin",method = RequestMethod.POST)
    public ServerResponse update(@RequestBody Map<String,String> adminmap) {
        System.out.println(adminmap.toString());
        Admin admin =new Admin();
        admin.setId(Integer.valueOf(adminmap.get("id")));
        admin.setName(adminmap.get("name"));
        admin.setPassword(adminmap.get("password"));
        admin.setPhone(adminmap.get("phone"));
        admin.setEmail(adminmap.get("email"));
        admin.setBankType(adminmap.get("bankType"));
        admin.setAccountNumber(adminmap.get("accountNumber"));
        admin.setRemarks(adminmap.get("remarks"));
        java.util.Date date =new java.util.Date();
        admin.setUpdateTime(new Timestamp(date.getTime()));
        return iAdminService.update(admin);
    }

    @RequestMapping(value = "/getSelf",method = RequestMethod.GET)
    public ServerResponse getSelf(@RequestParam String token) {
        return iAdminService.getSelf(token);
    }




}
