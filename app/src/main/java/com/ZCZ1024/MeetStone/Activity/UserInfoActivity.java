package com.ZCZ1024.MeetStone.Activity;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ZCZ1024.MeetStone.Entity.UserInfo;
import com.ZCZ1024.MeetStone.EntityVo.UserInfoVo;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.BitMapUtil;
import com.ZCZ1024.MeetStone.Util.UserPortraitDialog;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private UserPortraitDialog dialog;
    private TextView tv_uname, tv_uacount, tv_sex, tv_age, tv_address, tv_ocpt, tv_intro;
    private UserInfo userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);
        initView();
    }

    private void initView() {
        LinearLayout layout = findViewById(R.id.update_userinfo_tx);
        layout.setOnClickListener(this);
        tv_uname = findViewById(R.id.tv_info_name);
        tv_uacount = findViewById(R.id.tv_info_acount);
        tv_sex = findViewById(R.id.tv_info_sex);
        tv_age = findViewById(R.id.tv_info_age);
        tv_address = findViewById(R.id.tv_info_address);
        tv_ocpt = findViewById(R.id.tv_info_occupation);
        tv_intro = findViewById(R.id.tv_info_intro);
    }

    /**
     * 通过网络请求初始化用户信息数据
     */
    private void initData() {
        final String userid = "ff88dc16a6f74b9dae4b97644cab5d16";
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .getuinfo(userid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<UserInfoVo>() {
                            @Override
                            public void accept(UserInfoVo userInfoVo) throws Exception {
                                if (userInfoVo.getSuccess().equals(true)) {
                                    userinfo = userInfoVo.getUserInfo();
                                    setViewValue(userinfo);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
        );
    }

    /**
     * 根据获取到的userinfo设置用户资料界面的值
     * @param userInfo
     */
    private void setViewValue(UserInfo userInfo) {
        tv_uname.setText(userInfo.getName());
        tv_sex.setText(userInfo.getSex());
        tv_age.setText(userInfo.getAge());
        tv_address.setText(userInfo.getAddress());
        tv_ocpt.setText(userInfo.getOcpt());
        tv_intro.setText(userInfo.getIntro());
    }

    public void Btn_user_info(View view) {
        switch (view.getId()) {
            case R.id.btn_preActivity:
                this.finish();
                break;

            case R.id.Btn_ToUpdate:
                Intent intent = new Intent(this, UpdateUserInfoAcitivity.class);
                /*Bundle bundle = new Bundle();
                bundle.putSerializable("userinfo", userinfo);
                intent.putExtra("unseinfoBundle", bundle);*/
                startActivity(intent);

            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

        String photo = null;
        try {
            photo = new JSONObject(BitMapUtil.bitToStr(bitmap)).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (photo != null) {
            addDisposable(
                    RetrofitFactory.getRetrofit()
                            .create(UserDataService.class)
                            .putUserHeadpit(photo)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Map<String, String>>() {
                                @Override
                                public void accept(Map<String, String> map) throws Exception {
                                    if (map.get("succsse").equals("true"))
                                        //加载网络图片
                                        Glide.with(getBaseContext())
                                                .load(map.get("headfile"))
                                                .into(img);
                                    else if (map.get("succse").equals(false)) {
                                        Toast.makeText(getBaseContext(), "图片加载错误", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.e("error", throwable.getMessage());
                                }
                            })
            );

        }

    }
}
