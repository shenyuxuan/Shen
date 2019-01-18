package com.bw.shen.bean;

/**
 * Created by lenovo on 2019-1-5.
 */

public class Result<T> {


        private String message;
        private String status;
        private T result;
        private T orderList;

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

        public T getResult() {
            return result;
        }

        public void setResult(T result) {
            this.result = result;
        }
        public T getOrderList() {
            return orderList;
        }

        public void setOrderList(T orderList) {
            this.orderList = orderList;
        }


    }
