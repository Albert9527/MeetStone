package com.ZCZ1024.MeetStone.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ZCZ1024.MeetStone.Entity.Acount;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.Util.Md5Util;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowUserInfo extends BaseActivity{
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);
        textView = findViewById(R.id.tv_userinfoname);

        showinfo();

    }

    public void showinfo(){
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .getAcount()

                        //切换到IO线程执行网络请求
                        .subscribeOn(Schedulers.io())
                        //切换到UI线程执行UI操作
                        .observeOn(AndroidSchedulers.mainThread())
                        // 返回请求数据
                        .subscribe(new Consumer<List<Acount>>() {
                            @Override
                            public void accept(List<Acount> acounts) throws Exception {
                                for (Acount acount : acounts)
                                {
                                    Log.d("acount",acount.toString());
                                    textView.setText(acount.getUserName());
                                    //AcuntInfo.setUserSPf(getBaseContext(),acount.getUserName());
                                    //Md5Util.encrypt(acount.getUserName());
                                    AcuntInfo.cleanUserId(getBaseContext());
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
