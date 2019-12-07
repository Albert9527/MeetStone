package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.Entity.Acount;
import com.ZCZ1024.MeetStone.Model.UserModel;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.presenter.UserData;

public class LoginActivity extends AppCompatActivity {
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
        UserData userData = new UserData();
        String code = userData.LoginTest(getAount());
        if (code.equals("200")){
            onfailue();
        }else {
            loginsuccese();
        }
    }

    public void register(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public Acount getAount() {

            acount = new Acount();
            acount.setUsername(et_username.getText().toString());
            acount.setPassword(et_username.getText().toString());
            return acount;
    }

    public void onfailue() {
        Toast.makeText(this, "登陆失败",
                Toast.LENGTH_SHORT).show();
    }

    public void loginsuccese() {
        Toast.makeText(this, "登陆成功",
                Toast.LENGTH_SHORT).show();
    }
}
