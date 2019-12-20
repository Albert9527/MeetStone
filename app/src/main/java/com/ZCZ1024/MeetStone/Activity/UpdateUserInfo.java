package com.ZCZ1024.MeetStone.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.BitMapUtil;
import com.ZCZ1024.MeetStone.Util.UserPortraitDialog;

import java.io.FileNotFoundException;

public class UpdateUserInfo extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        initView();
    }

    private void initView(){
        LinearLayout layout = findViewById(R.id.update_userinfo_tx);
        layout.setOnClickListener(this);
    }

    public void RePrivedActivity(View view){
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_userinfo_tx:
                showPortraitDialog();
                break;

            default:
                break;
        }
    }

    public void showPortraitDialog() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.img_portrait_xc:
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent,2);
                        break;
                    case R.id.img_portrait_pz:
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(intent1, 1);
                        break;
                }
            }
        };
        new UserPortraitDialog(this,0,listener).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode  != RESULT_OK){
            Log.d("errer", "canceled or other exception!");
            return;
        }

        //启用相机返回图片
        if (requestCode == 1){
            if (data != null){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                bitmap = BitMapUtil.getBitmap(this,bitmap);
                bitmap = BitMapUtil.getZoomImage(bitmap,20.00);
                ImageView img = findViewById(R.id.img_info_tx);
                img.setImageBitmap(bitmap);
            }
        }

        //启用相册返回图片
        if (requestCode == 2) {
            if (data != null) {
                //获取图片定位符
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));

                    bitmap = BitMapUtil.getBitmap(this, bitmap);
                    bitmap = BitMapUtil.getZoomImage(bitmap, 20.00);

                    Log.d("test","运行到此步");
                    //将图片显示在图片控件中
                    ImageView img = findViewById(R.id.img_info_tx);
                    img.setImageBitmap(bitmap);

                    //字符串和bitmap互转
                    String imgstr = BitMapUtil.bitToStr(bitmap);
                    Bitmap bitmap1 = BitMapUtil.strToBit(imgstr);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
