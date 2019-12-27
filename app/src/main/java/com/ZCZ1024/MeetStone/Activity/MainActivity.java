package com.ZCZ1024.MeetStone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ZCZ1024.MeetStone.R;

public class MainActivity extends BaseActivity {

    private Context context;
    private Button button_jump;
    private TextView tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        button_jump = findViewById(R.id.btn_main_jump);
        tv_time = findViewById(R.id.tv_main_time);

        for (int i=3;i>0;i--){
            final int finalI = i;
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },1000);
            tv_time.setText(finalI +"s");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context,HomePageActivity.class));
                finish();
            }
        },3000);

        button_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,HomePageActivity.class));
                finish();
            }
        });

    }


}
