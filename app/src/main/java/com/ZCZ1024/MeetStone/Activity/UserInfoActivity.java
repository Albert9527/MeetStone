package com.ZCZ1024.MeetStone.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ZCZ1024.MeetStone.Entity.UserInfo;
import com.ZCZ1024.MeetStone.EntityVo.FileVo;
import com.ZCZ1024.MeetStone.EntityVo.UserInfoVo;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.Util.BitMapUtil;
import com.ZCZ1024.MeetStone.Util.PhoneNumUtil;
import com.ZCZ1024.MeetStone.Util.UserPortraitDialog;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.UserDataService;
import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private UserPortraitDialog dialog;
    private TextView tv_uname, tv_uacount, tv_sex, tv_age, tv_address, tv_ocpt, tv_intro;
    private UserInfo userinfo;
    private CircleImageView img_tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        LinearLayout layout = findViewById(R.id.update_userinfo_tx);
        LinearLayout ly_update_pswd = findViewById(R.id.ly_update_pswd);
        layout.setOnClickListener(this);
        ly_update_pswd.setOnClickListener(this);
        tv_uname = findViewById(R.id.tv_info_name);
        tv_uacount = findViewById(R.id.tv_info_acount);
        tv_sex = findViewById(R.id.tv_info_sex);
        tv_age = findViewById(R.id.tv_info_age);
        tv_address = findViewById(R.id.tv_info_address);
        tv_ocpt = findViewById(R.id.tv_info_occupation);
        tv_intro = findViewById(R.id.tv_info_intro);
        img_tx = findViewById(R.id.img_info_tx);
    }

    /**
     * 通过网络请求初始化用户信息数据
     */
    private void initData() {
        final String userid = AcuntInfo.geteditInfo(this, "userid");
        if (userid != null) {
            addDisposable(
                    RetrofitFactory.getRetrofit()
                            .create(UserDataService.class)
                            .getuinfo(userid)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<UserInfoVo>() {
                                @Override
                                public void accept(UserInfoVo userInfoVo) throws Exception {
                                    if (userInfoVo.isSuccess() == true) {
                                        userinfo = userInfoVo.getData();
                                        setViewValue(userinfo);
                                    } else {
                                        Log.e("error", "错误代码" + userInfoVo.getError());
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.d("error", throwable.getMessage());
                                    Toast.makeText(getBaseContext(), "数据获取失败,错误原因：" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            })
            );
        } else
            setViewValue(null);
        }

    /**
     * 根据获取到的userinfo设置用户资料界面的值
     *
     * @param userInfo
     */
    private void setViewValue(UserInfo userInfo) {
        String acount = PhoneNumUtil.dealPhoneNumber(AcuntInfo.geteditInfo(this, "acount"));
        if (acount != null) {
            if (userInfo.getNickname() != null)
                tv_uname.setText(userInfo.getNickname());
            else
                tv_uname.setText(acount);

            tv_uacount.setText(acount);

            if (userInfo.getSex() != null)
                tv_sex.setText(userInfo.getSex());

            if (userInfo.getAge() != null)
                tv_age.setText(userInfo.getAge());

            if (userInfo.getAddress() != null)
                tv_address.setText(userInfo.getAddress());

            if (userInfo.getIntro() != null)
                tv_intro.setText(userInfo.getIntro());

            if (userInfo.getImgurl() != null)
                Glide.with(getBaseContext())
                       /* .load("http://120.55.47.24:8080/img/" + userInfo.getImgurl())*/
                        .load(" http://192.168.10.120:8080/img/" + userInfo.getImgurl())
                        .into(img_tx);
            if (userInfo.getOcpt()!=null)
                tv_ocpt.setText(userInfo.getOcpt());

        }
    }

    public void Btn_user_info(View view) {
        switch (view.getId()) {
            case R.id.btn_preActivity:
                this.finish();
                break;

            case R.id.Btn_ToUpdate:
                Intent intent = new Intent(this, UpdateUserInfoAcitivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userinfo", userinfo);
                intent.putExtra("unseinfoBundle", bundle);
                startActivity(intent);
                break;
            case R.id.btn_logout:
                AcuntInfo.seteditInfo(this, "userid", null);
                AcuntInfo.seteditInfo(this, "acount", null);
                startActivity(new Intent(this, LoginActivity.class));
                this.finish();

            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_userinfo_tx:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                }
                showPortraitDialog();
                break;

            case R.id.ly_update_pswd:
                startActivity(new Intent(this, UpdatePswdActivity.class));
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

        //启用相机返回图片
        if (requestCode == 1) {
            if (data != null) {
                bitmap = (Bitmap) data.getExtras().get("data");
                bitmap = BitMapUtil.getBitmap(this, bitmap);
                bitmap = BitMapUtil.getZoomImage(bitmap, 20.00);

                /*img.setImageBitmap(bitmap);*/

                File file = BitMapUtil.getFile(this, bitmap, bitmap.toString());
            }
        }


        //启用相册返回图片
        if (requestCode == 2) {
            if (data != null) {
                //获取图片定位符
                Uri uri = data.getData();

                Log.d("url", uri.toString());
                File file = new File(BitMapUtil.getRealPathFromUri(this, uri));

                if (file != null) {
                    String id = AcuntInfo.geteditInfo(this,"userid");

                   /* String id = "ff88dc16a6f74b9dae4b97644cab5d16";*/

                    //将参数封装成RequestBody
                    RequestBody requestId = RequestBody.create(null, id);
                    String s = requestId.toString();
                    RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("pic", file.getName(), requestFile);


                    Log.d("file+id", body + ">>>>" + requestId);
                    addDisposable(
                            RetrofitFactory.getRetrofit()
                                    .create(UserDataService.class)
                                    .putUserHeadpit(body, requestId)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<FileVo>() {
                                        @Override
                                        public void accept(FileVo fileVo) throws Exception {
                                            if (fileVo.isSuccess() == true){
                                                Log.e("errorx",fileVo.getData());
                                                Log.e("errorx",fileVo.getError()+"");
                                                //加载网络图片
                                                Glide.with(getBaseContext())
                                                       /* .load("http://120.55.47.24:8080/img/" + fileVo.getData())*/
                                                        .load("http://192.168.10.120:8080/img/" +fileVo.getData())
                                                        .into(img_tx);
                                            }
                                            else if (fileVo.isSuccess() == false) {
                                                Toast.makeText(getBaseContext(), "图片加载错误", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }, new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Exception {
                                            Log.e("errorx", throwable.getMessage());
                                        }
                                    })
                    );
                }
            }
        }
    }


}
