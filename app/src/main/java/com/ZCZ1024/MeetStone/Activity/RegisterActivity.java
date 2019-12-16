package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity{
    private EditText et_username;
    private EditText et_password;
    private EditText et_surepswd;
    private EditText et_getcaptcha;

    private  String username;
    private String password;
    private String surepswd;
    private String captcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        initview();
    }
    private void initview(){
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_pswd);
        et_surepswd = findViewById(R.id.et_surepswd);
        et_getcaptcha = findViewById(R.id.et_testcode);

        username = et_username.getText().toString();
        password = et_password.getText().toString();
        surepswd = et_surepswd.getText().toString();
        captcha = et_getcaptcha.getText().toString();
    }
    public void postregister(View view){

        //usermap用于post请求的参数传递
        HashMap usermap = new HashMap();
        usermap.put("username",username);
        usermap.put("password",password);
        if (usermap.get("username") == null){
            Toast.makeText(RegisterActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();

        }
        else if (usermap.get("password") == null){
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }
        else if (usermap.get("password").equals(surepswd) == false){
            Toast.makeText(RegisterActivity.this,"两次输入密码不相符",Toast.LENGTH_SHORT).show();
        }
        else {

        addDisposable(
                RetrofitFactory.getRetrofit()
                .create(UserDataService.class)
                .regist(usermap)

                //切换到IO线程执行网络请求
                .subscribeOn(Schedulers.io())
                //切换到UI线程执行UI操作
                .observeOn(AndroidSchedulers.mainThread())
                // 返回请求数据
                .subscribe(new Consumer<Map<String,String>>() {
                    @Override
                    public void accept(Map<String,String> resultmap) throws Exception {
                        if (resultmap.get("success").equals("true")){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    finish();
                                }
                            },2500);
                        }
                        else if (resultmap.get("success").equals("false")){
                            Toast.makeText(RegisterActivity.this,resultmap.get("Error"),Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("errow",throwable.toString());
                    }
                }));
        }
    }

    public void getVerifyCode(View view){


        String appcode = "0db40c5784b94bd2ae65a51aced70c51";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", username);
        querys.put("param", "code:1234");
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();
        addDisposable(

                RetrofitFactory.getRetrofit("http://dingxin.market.alicloudapi.com/")
                .create(UserDataService.class)
                .getVerifvCode(headers,querys)
                 //切换到io线程执行网络请求
                .subscribeOn(Schedulers.io())
                //切换到ui线程执行ui操作
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String,String>>() {
                    @Override
                    public void accept(Map<String, String> map) throws Exception {
                        if (map.get("return_code").equals("00000")){

                        }
                    }

                })
        );
    }
}
