package com.ZCZ1024.MeetStone.Util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SMSDKUtil extends SMSSDK{

    public void setSMSSDK(final Context context){

    EventHandler handler = new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE){

                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                    new Runnable(){
                        @Override
                        public void run() {
                            //验证成功200
                            Toast.makeText(context,"验证成功",Toast.LENGTH_SHORT).show();
                            AcuntInfo.seteditInfo(context,"SMSCode","200");
                        }
                    };
                }
                else if (event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE){
                    new Runnable() {
                        @Override
                        public void run() {
                            AcuntInfo.seteditInfo(context,"SMSCode","201");
                        }
                    };
                }
                else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    new Runnable() {
                        @Override
                        public void run() {
                            AcuntInfo.seteditInfo(context,"SMSCode","202");
                        }
                    };
                }else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    Log.i("test","test");
                }
            }
            else {
                ((Throwable)data).printStackTrace();
                Throwable throwable = (Throwable) data;
                throwable.printStackTrace();
                Log.i("1234",throwable.toString());
                try {
                    JSONObject obj = new JSONObject(throwable.getMessage());
                    final String des = obj.optString("detail");
                    if (des.equals("")){
                        new Runnable() {
                            @Override
                            public void run() {
                                AcuntInfo.seteditInfo(context,"SMSCode",des);
                            }
                        };
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    SMSDKUtil.registerEventHandler(handler);
    }

}
