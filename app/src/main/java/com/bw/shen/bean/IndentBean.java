package com.bw.shen.bean;

/**
 * Created by lenovo on 2019-1-9.
 */

public class IndentBean<T> {
    private String message;
    private String status;
    private T orderList;

    public IndentBean(String message, String status, T orderList) {
        this.message = message;
        this.status = status;
        this.orderList = orderList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getOrderList() {
        return orderList;
    }

    public void setOrderList(T orderList) {
        this.orderList = orderList;
    }
}
