package com.example.carmanager.entity;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/23 22:43
 */
public class Result<T> {
    
    private String msg;
    private boolean success;
    private T detail;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }
}
