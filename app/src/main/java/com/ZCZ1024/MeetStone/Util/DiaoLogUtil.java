package com.ZCZ1024.MeetStone.Util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class DiaoLogUtil {

    private static int sure;

    public static int showWindow(Context context,String msg){
        AlertDialog.Builder alertLog = new AlertDialog.Builder(context);
        alertLog.setMessage(msg);

        alertLog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sure = 1;
            }
        });

        alertLog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sure = 0;
            }
        });
        alertLog.show();
        return sure;
    }

}
