package com.ZCZ1024.MeetStone.Util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class AcuntInfo {

    public static void setUserSPf(Context context, String userid) {

        //获取SharedPreferences对象
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("config", MODE_PRIVATE);

        //获取Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();


        //设置参数
        editor.putString("userid", userid);

        //提交
        editor.commit();

    }

    public static String getUserId(Context context) {
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        String userid = sharedPre.getString("userid", null);
        return userid;
    }

    public static void cleanUserId(Context context){
        setUserSPf(context,null);
    }
}
