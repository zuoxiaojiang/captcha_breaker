package com.example.demo.bean;

public class ResultJson <T>{
    private T data;
    private Meta meta;

    public ResultJson(Meta meta){
        this.meta = meta;
    };

    public static final ResultJson SUCCESS = new ResultJson(new Meta("200", "成功"));
    public static final ResultJson FAILED = new ResultJson(new Meta("401", "失败"));

    public ResultJson<T> setData(T data) {
        this.data = data;
        return this;
    }
}
