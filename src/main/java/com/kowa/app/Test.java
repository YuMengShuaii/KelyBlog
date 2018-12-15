package com.kowa.app;

import com.enation.app.mobile.tangram.core.Tangram;
import com.enation.app.mobile.tangram.core.TangramFactory;
import com.enation.app.mobile.tangram.model.HorizontalViewStyleBean;
import com.enation.app.mobile.tangram.model.TangramItemBean;
import com.enation.app.mobile.tangram.utils.TangramUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.boot.json.GsonJsonParser;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) throws ParseException {
        ArrayList<Map> datas = new Gson().fromJson(getJson(),ArrayList.class);

        removeChild(datas);

        System.out.print(new Gson().toJson(datas));

    }

    public static void removeChild(ArrayList<Map> datas){
        List<Integer> indexArray = new ArrayList();
        for (Map data : datas) {
            if (data != null){
                if (((Boolean)data.get("checked"))){
                    if (data.get("children")!= null && ((List)data.get("children")).size() > 0){
                        removeChild((ArrayList<Map>) data.get("children"));
                    }
                }else{
                    indexArray.add(datas.indexOf(data));
                }
            }
        }
        for (Integer integer : indexArray) {
            datas.remove((int)integer);
        }
    }

    public static String getJson(){

        return "[\n" +
                "    {\n" +
                "      \"auth_regular\": \"/goods/**\",\n" +
                "      \"checked\": true,\n" +
                "      \"children\": [{\n" +
                "          \"auth_regular\": \"/goods/**\",\n" +
                "          \"checked\": true,\n" +
                "          \"children\": [{\n" +
                "              \"auth_regular\": \"/goods/**\",\n" +
                "              \"checked\": false,\n" +
                "              \"children\": [],\n" +
                "              \"identifier\": \"goods3\",\n" +
                "              \"title\": \"goods3\"\n" +
                "          },{\n" +
                "              \"auth_regular\": \"/goods/**\",\n" +
                "              \"checked\": true,\n" +
                "              \"children\": [],\n" +
                "              \"identifier\": \"goods4\",\n" +
                "              \"title\": \"goods4\"\n" +
                "          }],\n" +
                "          \"identifier\": \"goods1\",\n" +
                "          \"title\": \"goods1\"\n" +
                "      },{\n" +
                "          \"auth_regular\": \"/goods/**\",\n" +
                "          \"checked\": true,\n" +
                "          \"children\": [{\n" +
                "              \"auth_regular\": \"/goods/**\",\n" +
                "              \"checked\": false,\n" +
                "              \"children\": [],\n" +
                "              \"identifier\": \"goods5\",\n" +
                "              \"title\": \"goods5\"\n" +
                "           }],\n" +
                "          \"identifier\": \"goods2\",\n" +
                "          \"title\": \"goods2\"\n" +
                "\n" +
                "      }],\n" +
                "      \"identifier\": \"goods\",\n" +
                "      \"title\": \"goods\"\n" +
                "    },\n" +
                "     {\n" +
                "      \"auth_regular\": \"/order/**\",\n" +
                "      \"checked\": true,\n" +
                "      \"children\": [\n" +
                "        null\n" +
                "      ],\n" +
                "      \"identifier\": \"order\",\n" +
                "      \"title\": \"order\"\n" +
                "    }\n" +
                "  ]";
    }


}
