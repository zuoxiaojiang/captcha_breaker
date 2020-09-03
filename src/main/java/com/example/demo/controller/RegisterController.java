package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public JSONObject register(@RequestBody JSONObject formData){

        String name = formData.getString("user_name");
        String pwd = formData.getString("password");
        String mail = formData.getString("mail");
        String role_id = formData.getString("role_id");

        String role = "";
        switch (Integer.parseInt(role_id)){
            case 0 :
                role = "管理员";
                break;
            case 1:
                role = "打码请求者";
                break;
            case 2:
                role = "人工打码客户端";
                break;
            default: role = "error in role setting";

        }

        User u = new User();
        u.setUser_name(name);
        u.setPassword(pwd);
        u.setMail(mail);
        u.setRole(role);
        u.setStatus("登录");

        JSONObject res = userService.addUser(u);

        System.out.println("res is : " + res.toString());

        return res;
    }
}
