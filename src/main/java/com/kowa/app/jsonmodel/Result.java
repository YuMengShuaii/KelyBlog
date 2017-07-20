package com.kowa.app.jsonmodel;

public class Result {
    private int result;
    private String message;
    private Object data;

    public Result(String message, Object data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private void setData(int result, String message, Object data) {
        this.result = result;
        this.data = data;
        this.message = message;
    }
}
