package com.ZCZ1024.MeetStone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ZCZ1024.MeetStone.Entity.UserInfo;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UpdateUserInfoAcitivity extends BaseActivity {
    private EditText et_name, et_age, et_address, et_intro;
    private RadioGroup radioGroup;
    private RadioButton man, women;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info_acitivity);
        iniview();
        //setViewValue();
    }

    private void iniview() {

        et_name = findViewById(R.id.et_update_info_mininame);
        et_age = findViewById(R.id.et_update_info_age);
        et_address = findViewById(R.id.et_update_info_address);
        et_intro = findViewById(R.id.et_update_user_intro);
        radioGroup = findViewById(R.id.rag_update_sex);
        man = findViewById(R.id.rbtn_man);
        women = findViewById(R.id.rbtn_women);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (man.isChecked())
                    userInfo.setSex("nan");
                else if (women.isChecked())
                    userInfo.setSex("women");
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
        if (userInfo.getSex().equals("nan")){
            man.setChecked(true);
        }
        else
            women.setChecked(false);

    }

    private UserInfo getnewUserinfo(){
        userInfo.setNickname(et_name.getText().toString());
        userInfo.setAge(et_age.getText().toString());
        userInfo.setAddress(et_address.getText().toString());
        userInfo.setIntro(et_intro.getText().toString());
        return userInfo;
    }

    public void UpdateUserInfo(View view){
        String userid = AcuntInfo.geteditInfo(this,"userid");
        addDisposable(
                RetrofitFactory.getRetrofit()
                .create(UserDataService.class)
                .updateUinfo(userid,userInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String,String>>() {
                    @Override
                    public void accept(Map<String,String> result) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })
        );
    }

    public void closeActivity(View view){
        this.finish();
    }
}
