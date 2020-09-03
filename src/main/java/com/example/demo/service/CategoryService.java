package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Category;
import com.example.demo.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加单个验证码类别
     * @param category
     * @return
     */
    public JSONObject addCategory(Category category){


        JSONObject res = new JSONObject();


        if(categoryMapper.selectCateByCode(category.getCode()) == null && categoryMapper.selectCateByName(category.getName()) == null){
            categoryMapper.addCategory(category.getCode(), category.getName(), category.getDetails());

            category.setCategory_id(categoryMapper.selectCateByCode(category.getCode()).getCategory_id());
            res.put("data", category);
            res.put("status", 200);
            res.put("message", "添加验证码类型成功！");
            System.out.println("添加类型成功！");
            return res;
        }

//        category.setCategory_id(-1);
        res.put("data", category);
        res.put("status", 500);
        res.put("message", "该类型已存在，请填写其他类型码或类型名称！");

        return res;
    }


    /**
     * 获取验证码类型列表
     * @return
     */
    public JSONObject getAllCategories(){
        JSONObject res = new JSONObject();

        JSONArray cates = new JSONArray();
        List<Category> categories = categoryMapper.selectAll();
        cates.addAll(categories);

        res.put("data", cates);
        res.put("status", 200);
        res.put("message", "获取验证码类别列表成功！");

        return res;
    }
}
