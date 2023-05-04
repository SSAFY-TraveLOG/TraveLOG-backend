package com.ssafy.travelog.util;

public class Message {

    private int code;
    private StatusEnum status;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(StatusEnum status) {
        this.code = status.statusCode;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Message() {
        this.code = StatusEnum.BAD_REQUEST.statusCode;
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}