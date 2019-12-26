package com.ZCZ1024.MeetStone.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ZCZ1024.MeetStone.Entity.Acount;
import com.ZCZ1024.MeetStone.EntityVo.UserVo;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowUserPage extends BaseActivity{
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);
        textView = findViewById(R.id.tv_userinfoname);

        showinfo();

    }

    public void showinfo(){
        final String userid = AcuntInfo.geteditInfo(this,"userid");
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .getUser(userid)

                        //切换到IO线程执行网络请求
                        .subscribeOn(Schedulers.io())
                        //切换到UI线程执行UI操作
                        .observeOn(AndroidSchedulers.mainThread())
                        // 返回请求数据
                        .subscribe(new Consumer <UserVo>() {
                            @Override
                            public void accept(UserVo userVo) throws Exception {
                                if (userVo.getSuccess().equals(true))
                                    Log.d("acount",userVo.toString());
                                    textView.setText(userVo.getData().getNickname());

                                }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("errow",throwable.toString());
                            }
                        }));
    }

    public void closeActivity(View view){
        this.finish();
    }
}
