package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ManualClient {
    public static String resStr = "";

    public static String bypass() throws InterruptedException {
        String res;

        System.out.println("监听接收到的人工打码结果...");
        do {
            Thread.sleep(1);

        } while (resStr.equals(""));
        System.out.println("人工打码结束...");
        res = resStr;
        System.out.println("人工打码结果：" + res);

        resStr = "";
        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        bypass();
    }
}
