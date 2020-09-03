package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.CaptchaClassify;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaClassifyController {

    @PostMapping("/classify")
    public static JSONObject classify(@RequestBody JSONObject params){

        JSONObject res = new JSONObject();

        try{
            String classification = CaptchaClassify.classify(params.getString("name"));

            res.put("status","200");
            res.put("message","分类成功！");
            res.put("class",classification);
        }catch (Exception e){
            res.put("status","500");
            res.put("message","分类失败");
        }
        return res;
    }
}
