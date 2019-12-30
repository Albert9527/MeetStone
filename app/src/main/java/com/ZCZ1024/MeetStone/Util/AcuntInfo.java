package com.ZCZ1024.MeetStone.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ZCZ1024.MeetStone.Entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;

public class AcuntInfo {

    /**
     * 本地保存用户信息
     * @param context
     * @param key
     * @param userid
     */
    public static void seteditInfo(Context context,String key, String userid) {

        //获取SharedPreferences对象
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("config", MODE_PRIVATE);


        //获取Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();


        //设置参数
        editor.putString(key, userid);

        //提交
        editor.commit();

    }

    public static String geteditInfo(Context context,String key) {
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        String userid = sharedPre.getString(key, null);
        return userid;
    }

    /**
     * 使用SharedPreferences存储对象
     * @param context
     * @param key
     * @param object
     */
    public static void setObject(Context context,String key, Object object) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("config", MODE_PRIVATE);
        Gson gson = new Gson();
        String value = gson.toJson(object);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }


    public static Object getObject(Context context,String key,Object object){
        SharedPreferences shared = context
                .getSharedPreferences("config", MODE_PRIVATE);
        String value = shared.getString(key,null);
        Gson gson = new Gson();
        return gson.fromJson(value,object.getClass());
    }

    public static void cleanUserId(Context context){
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        sharedPre.edit().clear();
    }
}
