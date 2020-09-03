package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class JobScheduleController {

    @Autowired
    UserService userService;

    @PostMapping("/schedule")
    // 以验证码图片的地址作为任务的标识
    public JSONObject schedule(@RequestBody JSONObject params){

        JSONObject res = new JSONObject();

        // 查找空闲用户返回一个用户列表将第一个作为调度用户
//        List<User> list = userService.findAvailableUser();
////        random
//        Random r =new Random();
//        try{
//            User user = list.get(r.nextInt(list.size()));
//            // 将该用户修改为打码状态不能再接受任务
//            userService.busy(user.getUser_name());
//
//            // 返回打码客户端所需识别图片在服务器中的地址和用户的名称
//            res.put("jobFilePath",params.getString("path"));
//            res.put("client",user.getUser_name());
//            res.put("status","200");
//            res.put("message","分配成功！");
//            System.out.println("分配的打码客户端是：" + res.getString("client"));
//        }catch (IllegalArgumentException e){
//            res.put("status","500");
//            res.put("message","当前没有空闲打码客户端！");
//        }

        res.put("client","user2");
        res.put("status","200");
        res.put("message","分配成功！");
        System.out.println("分配的打码客户端是：" + res.getString("client"));
        return res;
    }
}
