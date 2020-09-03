package com.example.demo.mapper;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

// Mapper属于DAO层，直接与数据库打交道
@Mapper
public interface UserMapper {
    @Select("select user_name from user where user_id = #{user_id}")
    public String selectUserName(int user_id);

    @Select("select * from user where user_id = #{user_id}")
    public User selectUser(int user_id);

    @Select("select userPassword from user where userName = #{userName}")
    public String selectUserPassword(String userName);

    @Insert("insert into user(user_name, password, mail, role, status, token) value(#{user_name}, #{password}, #{mail}," +
            "#{role}, #{status}, #{token})")
    public void addUser(String user_name, String password, String mail, String role, String status, String token);


    @Select("select * from user where user_name = #{user_name}")
    public User selectUserByName(String user_name);

    @Select("select * from user where role='人工打码客户端' and status='登录'")
    public List<User> findAvailableUser();

    @Update("update user set status='打码中' where user_name=#{user_name}")
    public void busy(String user_name);

    @Update("update user set status='登录' where user_name=#{user_name}")
    public void avail(String user_name);
}
