package com.ZCZ1024.MeetStone.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity {
    private EditText et_username;
    private EditText et_password;
    private EditText et_surepswd;
    private EditText et_getcaptcha;

    private String username;
    private String password;
    private String surepswd;
    private String captcha;

    private Map<String, String> usermap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initview();


    }

    private void initview() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_pswd);
        et_surepswd = findViewById(R.id.et_surepswd);
        et_getcaptcha = findViewById(R.id.et_testcode);

        EventHandler handler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {

                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                                addDisposable(
                                        RetrofitFactory.getRetrofit()
                                                .create(UserDataService.class)
                                                .regist(usermap)

                                                //切换到IO线程执行网络请求
                                                .subscribeOn(Schedulers.io())
                                                //切换到UI线程执行UI操作
                                                .observeOn(AndroidSchedulers.mainThread())
                                                // 返回请求数据
                                                .subscribe(new Consumer<Map<String, String>>() {
                                                    @Override
                                                    public void accept(Map<String, String> resultmap) throws Exception {
                                                        if (resultmap.get("success").equals("true")) {
                                                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                            new Handler().postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                                                    finish();
                                                                }
                                                            }, 2500);
                                                        } else if (resultmap.get("success").equals("false")) {
                                                            Toast.makeText(RegisterActivity.this, resultmap.get("Error"), Toast.LENGTH_SHORT).show();
                                                        }

                                                    }
                                                }, new Consumer<Throwable>() {
                                                    @Override
                                                    public void accept(Throwable throwable) throws Exception {
                                                        Log.e("errow", throwable.toString());
                                                    }
                                                })
                                );
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "语音验证发送", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        Log.i("test", "test");
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                    Log.i("1234", throwable.toString());
                    try {
                        JSONObject obj = new JSONObject(throwable.getMessage());
                        final String des = obj.optString("detail");
                        if (!des.equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, des, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        SMSSDK.registerEventHandler(handler);
    }

    public void getMassgeCode(View view) {
        username = et_username.getText().toString();
        if (username.equals("")) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("1234", username);
            SMSSDK.getVerificationCode("86", username, (OnSendMessageHandler) null);
        }
    }

    public void postregister(View view) {

        username = et_username.getText().toString();
        password = et_password.getText().toString();
        surepswd = et_surepswd.getText().toString();
        captcha = et_getcaptcha.getText().toString();


        //usermap用于post请求的参数传递
        usermap = new HashMap();
        usermap.put("username", username);
        usermap.put("password", password);
        if (username.equals("")) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();

        } else if (usermap.get("username").equals("")) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (surepswd.equals(usermap.get("password")) == false) {
            Toast.makeText(this, "两次输入密码不相符", Toast.LENGTH_SHORT).show();
        } else if (captcha.equals("")) {
            Toast.makeText(this, "验证码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            SMSSDK.submitVerificationCode("86", username, captcha);
        }
    }
}
