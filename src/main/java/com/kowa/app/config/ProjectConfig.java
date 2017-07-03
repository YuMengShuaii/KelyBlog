package com.kowa.app.config;

public class ProjectConfig {
    public static final String AccessKey = "KuY46KI0HRuw7vRPblfYA8tYI2FcqYMC7w0EyIOA";
    public static final String SecretKey = "zdz4KA7N7z50QF6KR9-rFVfSd-IH79Zfwej8FRDY";
    public static final String SpaceName = "blog";
    public static final String imageBaseUrl = "http://onghqryqs.bkt.clouddn.com/";
    public static final String LocalImageurl = "http://192.168.1.17:8080/static/file/";
    private static final String IMAGESOURCE_WINDOWS = "E:/ImageCache";
    private static final String IMAGESOURCE_MAC = "/Users/LDD/Downloads";
    public static String getImageSource(){
        String source;
        if (System.getProperty("os.name").contains("Mac")){
            source=IMAGESOURCE_MAC;
        }else{
            source=IMAGESOURCE_WINDOWS;
        }
        return source;
    }
}