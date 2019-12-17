package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.ZCZ1024.MeetStone.R;

public class MakeTeamActivity extends BaseActivity {
    private EditText et_teamIntro,et_teamName,et_teamNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_team);
        et_teamIntro = findViewById(R.id.tv_teamintro);
        initview();
       getMartchName();


    }

    private void initview(){
        et_teamIntro = findViewById(R.id.tv_teamintro);
        et_teamName = findViewById(R.id.tv_teamintro);
        et_teamNum = findViewById(R.id.tv_teamintro);
    }

    public void getMartchName(){
        if (getIntent() != null){
            String name = getIntent().getStringExtra("martchname");
            et_teamIntro.setText(name);
        }
    }
}
