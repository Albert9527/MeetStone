package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.Util.DiaoLogUtil;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.TeamDataService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MakeTeamActivity extends BaseActivity {
    private EditText et_teamIntro, et_teamName, et_teamNum, et_teambiq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_team);
        initview();
        getMartchName();

    }

    private void initview() {
        et_teamIntro = findViewById(R.id.et_teamintro);
        et_teamName = findViewById(R.id.et_teamintro);
        et_teamNum = findViewById(R.id.et_teamnum);
        et_teambiq = findViewById(R.id.et_teamlable);
    }

    public void getMartchName() {
        if (getIntent() != null) {
            String name = getIntent().getStringExtra("martchname");
            et_teamIntro.setText(name);
        }
    }

    private Map getnewTeam() {
        Map newteam = new HashMap();

        if (et_teamName.getText().toString() != null)
            newteam.put("name", et_teamName.getText().toString());
        else {
            DiaoLogUtil.showWindow(this, "队伍名不能为空！");
            return null;
        }

        newteam.put("intro", et_teamIntro.getText().toString());

        newteam.put("max", et_teamNum.getText().toString());

        newteam.put("ctgy", et_teambiq.getText().toString());
        // team.setMadetime(new Date());
        newteam.put("captain",AcuntInfo.geteditInfo(this,"userid"));
        return newteam;
    }


    public void makeNewTeam(final View view) {
        Map team = getnewTeam();
        if (team.get("name") != null) {
            addDisposable(
                    RetrofitFactory.getRetrofit()
                            .create(TeamDataService.class)
                            .maketeam(team)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Map<String, String>>() {
                                @Override
                                public void accept(Map<String, String> map) throws Exception {

                                    if (map.get("success").equals("true")) {
                                        Toast.makeText(getBaseContext(), "创建成功！", Toast.LENGTH_LONG).show();
                                        MakeTeamActivity.this.finish();
                                    } else
                                        Toast.makeText(getBaseContext(), "创建失败！", Toast.LENGTH_LONG).show();

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.e("error", throwable.getMessage());
                                    Toast.makeText(getBaseContext(), "网络请求失败！" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
            );
        } else
            Toast.makeText(this, "队伍名不能为空", Toast.LENGTH_LONG).show();
    }

    public void closeActivity(View view) {
        this.finish();
    }
}
