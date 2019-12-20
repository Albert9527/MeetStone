package com.ZCZ1024.MeetStone.Util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ZCZ1024.MeetStone.Activity.UpdateUserInfo;
import com.ZCZ1024.MeetStone.R;

public class UserPortraitDialog extends Dialog {
    Activity context;
    private View.OnClickListener clickListener;

    public UserPortraitDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public UserPortraitDialog(Activity context, int theme,View.OnClickListener clickListener) {
        super(context,theme);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //指定布局
        this.setContentView(R.layout.item_user_portrait);


        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */

        Window window = this.getWindow();

        WindowManager manager = context.getWindowManager();
        Display display = manager.getDefaultDisplay();

        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (display.getHeight() * 0.3); // 高度设置为屏幕的0.6
        params.width = (int) (display.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        window.setAttributes(params);

        //根据id在布局中找到控件对象
        TextView cancle = findViewById(R.id.tv_portrait_cancle);
        ImageView imgxc = findViewById(R.id.img_portrait_xc);
        ImageView imgpz = findViewById(R.id.img_portrait_pz);

        imgpz.setOnClickListener(clickListener);
        imgxc.setOnClickListener(clickListener);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPortraitDialog.this.dismiss();
            }
        });
        this.setCancelable(true);
    }

}
