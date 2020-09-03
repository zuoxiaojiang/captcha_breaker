package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.UserService;
import com.example.demo.util.CaptchaClassify;
import com.example.demo.util.JobUtil;
import com.example.demo.util.ManualClient;
import com.example.demo.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.demo.util.TimeUtil.fromDateStringToLong;
import static com.example.demo.util.TimeUtil.getCurrentTime;

@RestController
public class RecognitionController {


    @PostMapping("/recognition")
    public JSONObject recognition(@RequestBody JSONObject params) throws InterruptedException, IOException {
        String srcType = params.getString("src_type");
        if (srcType.equals("1")){
            WebSocketServer.sendInfo(params.getString("captcha_base64"), "2");
            return (JSONObject) new JSONObject().put("status", "200");
        }



        String imgName = params.getString("name");

        String returnFileName = imgName.substring(0, imgName.indexOf('.'));
        WebSocketServer.sendInfo(returnFileName, "2");


        //获取当前时间为开始时间，转换为long型
        long startTime = getCurrentTime();

//        TODO: 需要对接收到的请求参数进行判断，对请求方式进行判别： iamge 还是 URL

        JSONObject res = new JSONObject();

//      验证码分类
        String imgClass = CaptchaClassify.classify(imgName);
        res.put("class", imgClass);

//      分发client，确定client id
        JobScheduleController scheduler = new JobScheduleController();
        JSONObject schRes = scheduler.schedule(params);
        res.put("client", schRes.getString("client"));

//      开始识别，包括耗时计算
        String manualRes = ManualClient.bypass();
        res.put("bypass_result", manualRes);

        // 耗时计算
        long endTime = TimeUtil.getCurrentTime();
        res.put("speed", TimeUtil.getTimeInterval(startTime, endTime));

//      识别率计算
        res.put("curr_acc", JobUtil.genAcc());

//      分数计算
        MarkController mc = new MarkController();
        JSONObject mcRes = mc.mark(res.getString("speed"), res.getString("curr_acc"), res.getString("client"));
        res.put("mark", mcRes.getString("mark"));

        res.put("status","200");

        return res;
    }
}
