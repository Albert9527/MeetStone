package com.ZCZ1024.MeetStone.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.BitMapUtil;
import com.ZCZ1024.MeetStone.Util.CreatDialogUtil;
import com.ZCZ1024.MeetStone.Util.UserPortraitDialog;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MakeDynmicActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_send, btn_addimg;
    private EditText editText;
    private ImageView img1, img2, img3;
    private UserPortraitDialog dialog;
    List<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_dynmic);
        initview();
    }

    public void initview() {
        btn_send = findViewById(R.id.btn_dynmic_send);
        btn_addimg = findViewById(R.id.btn_dynmic_addimg);
        img1 = findViewById(R.id.img_dynmic_1);
        img2 = findViewById(R.id.img_dynmic_2);
        img3 = findViewById(R.id.img_dynmic_3);

        imageViews = new ArrayList<>();
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);


        btn_send.setOnClickListener(this);
        btn_addimg.setOnClickListener(this);
    }


    public void closeActivity(View view) {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dynmic_send:
                break;
            case R.id.btn_dynmic_addimg:
                    showPortraitDialog();
                break;

        }
    }

    public void imgviewclick(View view){
        switch (view.getId()){
            case R.id.img_dynmic_1:
                img1.setVisibility(View.GONE);
                break;
            case R.id.img_dynmic_2:
                img2.setVisibility(View.GONE);
                break;
            case R.id.img_dynmic_3:
                img3.setVisibility(View.GONE);
                break;

        }
    }

    public void showPortraitDialog() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.img_portrait_xc:
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent, 2);
                        break;
                    case R.id.img_portrait_pz:
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(intent1, 1);
                        break;
                }
            }
        };
        dialog = new UserPortraitDialog(this, 0, listener);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog.dismiss();
        if (resultCode != RESULT_OK) {
            Log.d("errer", "canceled or other exception!");
            return;
        }

        Bitmap bitmap = null;
        final ImageView img = findViewById(R.id.img_info_tx);

        //启用相机返回图片
        if (requestCode == 1) {
            if (data != null) {
                bitmap = (Bitmap) data.getExtras().get("data");
                bitmap = BitMapUtil.getBitmap(this, bitmap);
                bitmap = BitMapUtil.getZoomImage(bitmap, 20.00);
            }
        }

        //启用相册返回图片
        if (requestCode == 2) {
            if (data != null) {
                //获取图片定位符
                Uri uri = data.getData();
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));

                    bitmap = BitMapUtil.getBitmap(this, bitmap);
                    bitmap = BitMapUtil.getZoomImage(bitmap, 20.00);

                    //将图片显示在图片控件中
                    /* img.setImageBitmap(bitmap);*/

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

        for (ImageView imgview : imageViews) {
            if (imgview.getVisibility() == View.GONE) {
                imgview.setVisibility(View.VISIBLE);
                imgview.setImageBitmap(bitmap);
                return;
            }
        }
    }
}
