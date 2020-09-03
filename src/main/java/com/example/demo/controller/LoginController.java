package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.ResultJson;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * controller负责处理请求转发
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody   // 结果返回
    public JSONObject login(@RequestBody JSONObject params){
        System.out.println("进入后台！");
        String username = params.getString("username");
        String password = params.getString("password");

        System.out.println(username + password);

        // 封装返回结果
        JSONObject res = new JSONObject();

        if(userService.findUserByName(username) == null){
            System.out.println("用户名不存在！" );

            res.put("status", 500);
            res.put("message", "用户名不存在！");
            return res;
        } else{
            User u = userService.findUserByName(username);
            if(u.getPassword().equals(password)){
                System.out.println(u.getUser_name() + " 用户正在登录" );
                res.put("data" , userService.findUserByName(username));
                res.put("status", 200);
                res.put("message", "登陆成功！");
            }else {
                System.out.println("密码错误！");
                res.put("message", "密码错误！");
                res.put("status", 500);
            }
            return res;
        }
    }
}
