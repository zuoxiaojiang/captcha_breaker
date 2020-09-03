package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.JobUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class PythonService {

    public static String classify(String filePath){

        String result = "";

        //执行python文件的命令参数：python ，python文件的地址，要传的参数（参数之间用逗号隔开）
        String[] args = new String[]{"python","E:\\captcha\\cnn_captcha\\recognize_local.py", filePath};
//        String[] args = new String[]{"python","E:\\captcha\\cnn_captcha\\main.py"};
        for(String arg : args){
            System.out.println(arg);
        }


        try {

            Process process = Runtime.getRuntime().exec(args);

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;

            while ((line = in.readLine()) != null){
                //System.out.println(line);
                result += line;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static JSONObject aiRecogClient(JSONObject params){

        JSONObject res = new JSONObject();

        String name = params.getString("name");
        String path = params.getString("path");

//        调用Python打码代码，返回结果
        String url = "http://localhost:5000/b";
        JSONObject pyResult = new JSONObject();
        System.out.println("current client is " + params.getString("client"));
        try {
            File file = new File(path);
            FileSystemResource resource = new FileSystemResource(file);

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("image_file", resource);

            RestTemplate restTemplate = new RestTemplate();
            pyResult = restTemplate.postForObject(url, param, JSONObject.class);

            System.out.println("Result from python is " + pyResult);

            assert pyResult != null;
            String strSpeed = pyResult.getString("speed_time(ms)");
//            strSpeed = strSpeed.substring(0, strSpeed.indexOf("m"));
            res.put("bypass_result", pyResult.getString("value"));
            res.put("speed", String.format("%.2f", Double.parseDouble(strSpeed)) + " ms");
            res.put("curr_acc", JobUtil.genAcc());    // 识别率
            res.put("status", 200);
            res.put("message", "识别成功！");
            System.out.println(res);

        }catch (Exception e){
            res.put("status", 500);
            res.put("message", "识别失败！");
        }

        return res;
    }

    public static void main(String[] args){

        String file = "E:\\captcha\\cnn_captcha\\sampl\\test\\0apj_159574507133604.png";//图片地址
        String result = classify(file);

        System.out.println(result);

    }
}
