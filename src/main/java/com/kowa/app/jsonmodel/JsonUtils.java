package com.kowa.app.jsonmodel;

import com.google.gson.Gson;

/**
 * Json字符串辅助类
 */
public class JsonUtils {
    /**
     * Json解析器
     */
    private static Gson gson = new Gson();

    /**
     * 获取请求成功json
     * @param message 提示消息
     * @param data    具体数据
     * @return        json
     */
    public static String getSuccessJson(String message, Object data){
        return gson.toJson(new Result(message,data));
    }

    /**
     * 获取请求失败json
     * @param message 错误提示
     * @return        json
     */
    public static String getErrorJson(String message){
        return gson.toJson(new Result(message));
    }
}
