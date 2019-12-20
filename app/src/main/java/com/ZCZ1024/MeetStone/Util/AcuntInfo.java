package com.ZCZ1024.MeetStone.Util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class AcuntInfo {

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

    public static void cleanUserId(Context context){
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        sharedPre.edit().clear();
    }
}
