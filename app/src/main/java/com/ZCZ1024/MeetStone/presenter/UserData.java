package com.ZCZ1024.MeetStone.presenter;


import com.ZCZ1024.MeetStone.Entity.Acount;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;

import io.reactivex.functions.Consumer;

public class UserData {
    private String code;

    public String LoginTest(Acount acount) {
        code = "200";
        RetrofitFactory.getRetrofit()
                .create(UserDataService.class)
                .LoginTest(acount.getUsername(), acount.getPassword())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //请求成功，获取返回值
                        code = s;
                    }
                }, new Consumer<Throwable>() {
                    //请求失败
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        code = "200";
                    }
                });
        return code;
    }

    public void showUserinfo(){

    }
}
