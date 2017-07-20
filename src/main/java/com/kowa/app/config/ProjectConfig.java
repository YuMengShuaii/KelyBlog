package com.kowa.app.config;

/**
 * 
 * @Auther yumengshuai【kely】
 * @Date   17/7/3 下午1:02
 *
 */
public class ProjectConfig {
    /**
     * 七牛AccessKey
     */
    public static final String AccessKey = "KuY46KI0HRuw7vRPblfYA8tYI2FcqYMC7w0EyIOA";
    /**
     * 七牛秘钥
     */
    public static final String SecretKey = "zdz4KA7N7z50QF6KR9-rFVfSd-IH79Zfwej8FRDY";

    /**
     * 七牛存储空间名
     */
    public static final String SpaceName = "blog";
    /**
     * 七牛图片BaseUrl
     */
    public static final String imageBaseUrl = "http://onghqryqs.bkt.clouddn.com/";
    /**
     * 本地图片BaseUrl
     */
    public static final String LocalImageurl = "http://127.0.0.1:8080/static/file/";
    /**
     * Windows平台图片路径
     */
    private static final String IMAGESOURCE_WINDOWS = "E:/ImageCache";
    /**
     * Mac平台图片路径
     */
    private static final String IMAGESOURCE_MAC = "/Users/LDD/Downloads";

    /**
     * 根据平台获取对应图片路径
     *
     * @return 图片路径
     */
    public static String getImageSource(){
        if (System.getProperty("os.name").contains("Mac")){
            return IMAGESOURCE_MAC;
        }else{
            return IMAGESOURCE_WINDOWS;
        }
    }

}