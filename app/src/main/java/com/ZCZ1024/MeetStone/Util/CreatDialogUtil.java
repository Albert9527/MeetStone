package com.ZCZ1024.MeetStone.Util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.R;

public class CreatDialogUtil extends Dialog {
    Activity context;

    private Button btn_submit,bt_applycancel;
    public EditText et_Apply_reason;
    public TextView tv_Apply_group,tv_Apply_user;

    private View.OnClickListener clickListener;

    public CreatDialogUtil(Activity context) {
        super(context);
        this.context = context;
    }

    public CreatDialogUtil(Activity context, int theme,View.OnClickListener clickListener) {
        super(context,theme);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //指定布局
        this.setContentView(R.layout.activity_apply_team);
        tv_Apply_user = findViewById(R.id.et_applyuname);
        tv_Apply_group = findViewById(R.id.et_applytname);
        et_Apply_reason = findViewById(R.id.et_applyteamreason);


        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */

        final Window dialogwindow = this.getWindow();

        final WindowManager manager = context.getWindowManager();

        Display display = manager.getDefaultDisplay();// 获取屏幕宽、高用

        WindowManager.LayoutParams params = dialogwindow.getAttributes();// 获取对话框当前的参数值
        params.height = (int) (display.getHeight() * 0.6); // 高度设置为屏幕的0.6
        params.width = (int) (display.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogwindow.setAttributes(params);

        //根据id在布局中找到控件对象
        btn_submit = findViewById(R.id.bt_applyteam);
        bt_applycancel = findViewById(R.id.bt_applycancel);

        btn_submit.setOnClickListener(clickListener);
        bt_applycancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //点击自定义按钮关闭弹窗
                CreatDialogUtil.this.dismiss();
            }
        });
        this.setCancelable(true);
    }

    public void showinfo() {
        Toast.makeText(this.context,tv_Apply_user.getText().toString(),Toast.LENGTH_SHORT).show();
    }

}
