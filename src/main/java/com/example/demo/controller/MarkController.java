package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Random;

@RestController
public class MarkController {
    @Autowired
    UserService userService;

    @GetMapping("/mark")
    public JSONObject mark(@RequestParam String speed, @RequestParam String curr_acc, @RequestParam String client){
        JSONObject res = new JSONObject();

            // 最后要返回的分数
        double grade = 0.0;

       double accuracy = Double.parseDouble(curr_acc);

        // 将传入的识别时间转化为double类型的数据
        double t = Double.parseDouble(speed.substring(0, speed.indexOf("m")));

        // 这是一个简单的奖惩计算，识别率×100 - 识别时间×1.8 识别率高奖励高，识别时间越长惩罚越大
        grade = accuracy * 100 - t / 500 ;

        // 返回用户的名称和验证码识别分数
        res.put("mark", String.format("%.2f", grade));
        res.put("status","200");
        res.put("message","打分完成！");
//        res.put("user_name",user_name);

        System.out.println("打码评分是：" + res.getString("mark"));
        // 将该用户恢复闲置状态
//        userService.avail(client);
        return res;
    }
}