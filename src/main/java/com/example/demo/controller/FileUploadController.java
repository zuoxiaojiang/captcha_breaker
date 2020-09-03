package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    private String filePath="C:/upload/";

    /**
     *
     * @param upload
     * @param userId
     * @return 返回了一个 不带文件后缀的 文件名路径， 作为后续 image/{filename} 的路径参数，  因为url参数中不能带有.后缀
     */
    @PostMapping("/upload")
    public JSONObject upLoad(@RequestParam("upload") MultipartFile upload, @RequestParam("user_id") String userId){

        JSONObject res = new JSONObject();

        //判断文件夹是否存在,不存在则创建
        File file = new File(filePath);

        if (!file.exists()){
            file.mkdirs();
        }
        String originalFileName = upload.getOriginalFilename();//获取原始图片的扩展名
        System.out.println(originalFileName);
        String newFileName = UUID.randomUUID()+originalFileName;
        System.out.println(newFileName);
        String newFilePath=filePath+originalFileName; //新文件的路径
        System.out.println(newFilePath);

        try {

            upload.transferTo(new File(newFilePath));//将传来的文件写入新建的文件

            System.out.println("上传图片成功!");

            //返回信息
            res.put("status","200");
            res.put("message","文件上传成功！");
            res.put("fileName",originalFileName);
            res.put("filePath",newFilePath);


        }catch (IllegalStateException e ) {
            //处理异常
            e.printStackTrace();
            res.put("status","500");
            res.put("message","文件上传失败！");
            res.put("fileName",originalFileName);
            res.put("filePath",newFilePath);
        }catch(IOException e1){
            //处理异常
            e1.printStackTrace();
            res.put("status","500");
            res.put("message","文件上传失败！");
            res.put("fileName",originalFileName);
            res.put("filePath",newFilePath);
        }

        return res;

    }

}
