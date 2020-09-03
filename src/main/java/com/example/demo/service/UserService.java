package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.ResultJson;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public String selectUsername(int user_id) {

        if(userMapper.selectUserName(user_id) == null){
            return "No such person";
        }else {
            System.out.println(userMapper.selectUserName(user_id));
            return userMapper.selectUserName(user_id);
        }
    }

    /**
     * 根据user_id返回单个用户的所有信息
     * @param user_id
     * @return
     */
    public User selectUser(int user_id) {
        if(userMapper.selectUser(user_id) == null)
            return null;
        else{
            return userMapper.selectUser(user_id);
        }
    }

    /**
     * 根据用户名称查找用户
     * @param name
     * @return User实体
     */
    public User findUserByName(String name){
        if(userMapper.selectUserByName(name) == null)
            return null;
        else return  userMapper.selectUserByName(name);
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    public JSONObject addUser(User user){
        String uname = user.getUser_name();

        JSONObject res = new JSONObject();

        if(userMapper.selectUserByName(uname) == null){
            user.setToken(genToken());

            userMapper.addUser(user.getUser_name(), user.getPassword(), user.getMail(), user.getRole(),
            user.getStatus(), user.getToken());

            user.setUser_id(userMapper.selectUserByName(user.getUser_name()).getUser_id());
            res.put("data", user);
            res.put("status", 200);
            res.put("message", "注册成功！");
            return res;
        }

        user.setUser_id(-1);
        res.put("data", user);
        res.put("status", 500);
        res.put("message", "用户名已存在，注册失败！");
        return res;
    }

    /**
     * TODO：生成 token 作为请求拦截，目前仅返回固定字符串
     * @return
     */
    private String genToken(){
        return "abcde";
    }

    /**
     * 搜索可用的登录用户
     */
    public List<User> findAvailableUser(){
        return userMapper.findAvailableUser();
    }

    /**
     * 将用户变成正在打码状态
     */
    public void busy(String user_name){
        userMapper.busy(user_name);
    }

    /**
     * 用户完成打码后变为登录状态
     */
    public void avail(String user_name){
        userMapper.avail(user_name);
    }
}
