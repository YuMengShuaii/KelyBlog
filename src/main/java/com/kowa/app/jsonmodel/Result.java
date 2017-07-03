package com.kowa.app.jsonmodel;

public class Result<T> {
    private int result;
    private String message;
    private T data;

    public Result(String message, T data) {
        this.message = message;
        this.data = data;
        this.result = 1;
    }

    public Result(String message) {
        this.message = message;
        this.result = 0;
        this.data = null;
    }

    public Result() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setData(int result, String message, T data) {
        this.result = result;
        this.data = data;
        this.message = message;
    }

    public void setSuccessData(String message, T data) {
        setData(1, message, data);
    }

    public void setFaildData(String message) {
        setData(0, message, null);
    }
}
