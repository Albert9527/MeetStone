package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;

import java.sql.Date;

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

    private Team getnewTeam() {
        Team team = new Team();

        team.setName(et_teamName.getText().toString());

        team.setIntro(et_teamIntro.getText().toString());

        team.setCaptain(AcuntInfo.geteditInfo(this, "userid"));

        team.setQuota(Integer.parseInt(et_teamNum.getText().toString()));

        team.setTeamCtgy(et_teambiq.getText().toString());
        // team.setMadetime(new Date());

        return team;
    }

    public void makeNewTeam(View view) {
        Team team = getnewTeam();
        if (team.getName() != null)
        Toast.makeText(this,team.toString(),Toast.LENGTH_SHORT).show();
    }

    public void closeActivity(View view) {
        this.finish();
    }
}
