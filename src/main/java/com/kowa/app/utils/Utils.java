package com.kowa.app.utils;

/**
 * Created by LDD on 17/7/3.
 */
public class Utils {
    public static boolean isEmpty(String value){
        if (value==null||value.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
