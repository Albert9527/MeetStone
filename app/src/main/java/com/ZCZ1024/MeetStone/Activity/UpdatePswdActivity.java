package com.ZCZ1024.MeetStone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UpdatePswdActivity extends BaseActivity {
    private EditText et_username;
    private EditText et_password;
    private EditText et_surepswd;
    private EditText et_getcaptcha;

    private  String username;
    private String password;
    private String surepswd;
    private String captcha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pswd);
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

    public void updatePswd(View view){
        //usermap用于post请求的参数传递
        HashMap usermap = new HashMap();
        usermap.put("username",username);
        usermap.put("password",password);
        if (usermap.get("username") == null){
            Toast.makeText(this,"账号不能为空",Toast.LENGTH_SHORT).show();

        }
        else if (usermap.get("password") == null){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }
        else if (usermap.get("password").equals(surepswd) == false){
            Toast.makeText(this,"两次输入密码不相符",Toast.LENGTH_SHORT).show();
        }

        else {
            addDisposable(
                    RetrofitFactory.getRetrofit()
                    .create(UserDataService.class)
                    .updatePswd(usermap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    // 返回请求数据
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String code) throws Exception {
                            //判断返回码
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    })
            );
        }
    }

    public void closeActivity(View view){
        this.finish();
    }

    private void updatefailue(){
        Toast.makeText(this, "密码修改失败",
                Toast.LENGTH_SHORT).show();
    }

    private void updatesuccess(){
        Toast.makeText(this, "修改成功",
                Toast.LENGTH_SHORT).show();
        AcuntInfo.cleanUserId(this);
        startActivity(new Intent(this,LoginActivity.class));
    }
}
