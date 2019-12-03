package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ZCZ1024.MeetStone.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        //登陆验证
        if(1==1){
            //提示登陆成功，延时跳转至首页
        }
    }

    public void register(View view){
        //注册
        //提示注册成功，延时跳转至登陆界面
    }
}
