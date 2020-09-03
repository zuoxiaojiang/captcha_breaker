package com.example.demo.mapper;

import com.example.demo.bean.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category")
    public List<Category> selectAll();

    @Insert("insert into category(code, name, details) value(#{code}, #{name}, #{details})")
    public void addCategory(String code, String name, String details);

    @Select("select * from category where code = #{code}")
    public Category selectCateByCode(String code);

    @Select("select * from category where name = #{name}")
    public Category selectCateByName(String name);
}
