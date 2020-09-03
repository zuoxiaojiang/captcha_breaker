package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("categories")
    @ResponseBody
    public JSONObject addCategory(@RequestBody Category category) {
        System.out.println("进入添加类别.");
        return categoryService.addCategory(category);
    }


    @GetMapping("categories")
    @ResponseBody
    public JSONObject getAllCategories(){
        return categoryService.getAllCategories();
    }
}
