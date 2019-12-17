package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import com.ZCZ1024.MeetStone.R;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context,HomePageActivity.class));
                finish();
            }
        },1000);

    }


}
