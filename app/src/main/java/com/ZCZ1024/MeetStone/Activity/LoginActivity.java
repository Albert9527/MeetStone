package com.ZCZ1024.MeetStone.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.Entity.Acount;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    private Acount acount;
    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_pswd);
    }

    public void login(View view) {
        //登陆验证
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,String> acount = new HashMap<>();

        acount.put("username",username);
        acount.put("password",password);
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .LoginTest(acount)

                        //切换到IO线程执行网络请求
                        .subscribeOn(Schedulers.io())
                        //切换到UI线程执行UI操作
                        .observeOn(AndroidSchedulers.mainThread())
                        // 返回请求数据
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String code) throws Exception {
                                if (code.equals("200")) {
                                    onfailue();
                                } else {
                                    loginsuccese(code);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                onfailue();
                                Log.e("errow", throwable.toString());
                            }
                        }));

}

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public Acount getAount() {

        acount = new Acount();
        acount.setUserName(et_username.getText().toString());
        acount.setUserPsd(et_password.getText().toString());
        return acount;
    }

    public void onfailue() {
        Toast.makeText(this, "登陆失败",
                Toast.LENGTH_SHORT).show();
    }

    public void loginsuccese(String userid) {
        AcuntInfo.setUserSPf(this,userid);
        Toast.makeText(this, "登陆成功",
                Toast.LENGTH_SHORT).show();


    }
}
