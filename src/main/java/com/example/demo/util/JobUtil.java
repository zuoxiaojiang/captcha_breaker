package com.example.demo.util;

import java.util.Random;

public class JobUtil {

    /**
     * 随机生成一个识别率
     * @return
     */
    public static double genAcc(){
        // 随机数生成类
        Random random = new Random();

        // 生成一个（0，1）内的随机数
        double temp = random.nextDouble();
        // 将数值控制在0.6-1.0之间
        return temp / 2.5 + 0.6;
    }
}
