package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class ImageController {
    @GetMapping("/image/{filename}")
    public void getImage(@PathVariable String filename, HttpServletResponse response) {
        System.out.println("开始加载图片...");
        File file = null;
        FileInputStream fis = null;
        String path = "C:/upload/" + filename + ".png";

        try {
            file = new File(path);
            if (!file.exists()) {
                System.out.println(path);
                return;
            }

            fis = new FileInputStream(file);
            final byte[] buf = new byte[1024];
            while (fis.read(buf) > 0) {
                response.getOutputStream().write(buf);
            }
            response.getOutputStream().flush();
            System.out.println("加载完毕");
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            System.out.println("加载出错");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (final IOException e) {
                    fis = null;
                }
            }
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            System.out.println("finally 加载完毕");
        }
    }
}
