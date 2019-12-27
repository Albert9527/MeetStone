package com.ZCZ1024.MeetStone.presenter.service;

import com.ZCZ1024.MeetStone.Entity.Acount;
import com.ZCZ1024.MeetStone.Entity.UserInfo;
import com.ZCZ1024.MeetStone.EntityVo.FileVo;
import com.ZCZ1024.MeetStone.EntityVo.UserInfoVo;
import com.ZCZ1024.MeetStone.EntityVo.UserVo;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserDataService {

    @GET("Acount/API/getuser")
    Flowable<UserVo> getUser(@Query("id") String id);

    @GET("Get/Test")
    Flowable<List<Acount>> getAcount();

    //获取队员集合
    @GET("Acount/API/getuau")
    Flowable<UserInfoVo> getuinfo(@Query("id") String userid);


    //注册接口请求，返回一个map，包含一个success（true和false）和error(状态码)
    @FormUrlEncoded
    @POST("Acount/API/signup")
    Flowable<Map<String,String>> regist(@FieldMap Map<String,String> registinfo);


    /**
     * 登陆接口请求。返回状态码
     * @param acount 用户账户{username，password}
     * @return
     */
    @FormUrlEncoded
    @POST("Acount/API/login")
    Flowable<Map<String,String>> LoginTest(@FieldMap Map<String,String> acount);


    /**
     * 修改密码
     * @param newpswd 新密码
     * @return 返回码，
     */
    @FormUrlEncoded
    @POST("updatePswd")
    Flowable<String> updatePswd(@FieldMap Map<String,String> newpswd);

    /**
     * 修改用户信息
     * @param userid 用户id
     * @param userInfo 用户新的信息
     * @return
     */
    @FormUrlEncoded
    @POST("updateUinfo")
    Flowable<Map<String,String>> updateUinfo(@Path("userid") String userid,@Body UserInfo userInfo);

    /**
     * 上传用户头像文件
     * @param pic
     * @return
     */
    @Multipart
    @POST("Acount/API/uploadimg")
    Flowable<FileVo> putUserHeadpit(@Part MultipartBody.Part pic, @Part("id") RequestBody id);

}
