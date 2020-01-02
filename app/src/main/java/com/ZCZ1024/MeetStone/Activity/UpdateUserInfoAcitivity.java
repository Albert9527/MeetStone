package com.ZCZ1024.MeetStone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.Entity.UserInfo;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UpdateUserInfoAcitivity extends BaseActivity {
    private EditText et_name, et_age, et_address, et_intro, et_occupation;
    private RadioGroup radioGroup;
    private RadioButton man, women;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info_acitivity);
        iniview();
        setViewValue();
    }

    private void iniview() {

        et_name = findViewById(R.id.et_update_info_mininame);
        et_age = findViewById(R.id.et_update_info_age);
        et_address = findViewById(R.id.et_update_info_address);
        et_intro = findViewById(R.id.et_update_user_intro);
        et_occupation = findViewById(R.id.et_update_info_occupation);
        radioGroup = findViewById(R.id.rag_update_sex);
        man = findViewById(R.id.rbtn_man);
        women = findViewById(R.id.rbtn_women);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_man:
                        userInfo.setSex("nan");
                        break;
                    case R.id.rbtn_women:
                        userInfo.setSex("women");
                        break;
                }
            }
        });

    }

    private void setViewValue() {
        //获取上一个页面传过来的用户信息
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("unseinfoBundle");
        userInfo = (UserInfo) bundle.get("userinfo");
        et_name.setText(userInfo.getNickname());
        et_age.setText(userInfo.getAge());
        et_address.setText(userInfo.getAddress());
        et_intro.setText(userInfo.getIntro());
        et_occupation.setText(userInfo.getOcpt());
        if (userInfo.getSex().equals("nan")) {
            man.setChecked(true);
        } else
            women.setChecked(true);

    }

    private Map<String,String> getnewUserinfo() {
        Map map = new HashMap();

        map.put("id",AcuntInfo.geteditInfo(this,"userid"));
        map.put("sex",userInfo.getSex());
        if (et_name.getText().toString() != null)
            map.put("nickname",et_name.getText().toString());
        else
            map.put("nickname",userInfo.getNickname());

        if (et_age.getText().toString() != null)
            map.put("age",et_age.getText().toString());
        else
           map.put("age",userInfo.getAge());

        if (et_address.getText().toString() != null)
            map.put("address",et_address.getText().toString());
        else
            map.put("address",userInfo.getAddress());

        if (et_intro.getText().toString() != null)
            map.put("intro",et_intro.getText().toString());
        else
            map.put("intro",userInfo.getIntro());

        if (et_occupation.getText().toString() != null)
            map.put("ocpt",et_occupation.getText().toString());
        else
            map.put("ocpt",userInfo.getOcpt());
        return map;
    }

    public void UpdateUserInfo(View view) {
       /* String userid = AcuntInfo.geteditInfo(this, "userid");*/
        Map newuserinfo = getnewUserinfo();
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .updateUinfo(newuserinfo)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Map<String, String>>() {
                            @Override
                            public void accept(Map<String, String> result) throws Exception {
                                if (result.get("success").equals("true")) {
                                    Toast.makeText(getBaseContext(), "修改成功", Toast.LENGTH_LONG).show();
                                    UpdateUserInfoAcitivity.this.finish();
                                } else
                                    Toast.makeText(getBaseContext(), "修改操作失败", Toast.LENGTH_LONG).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("errorx", throwable.getMessage());
                                Toast.makeText(getBaseContext(), "数据获取失败,错误原因：" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
        );
    }

    public void closeActivity(View view) {
        this.finish();
    }
}
