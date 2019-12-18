package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ZCZ1024.MeetStone.R;

public class UpdateUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
    }

    public void RePrivedActivity(View view){
        this.finish();
    }
}
