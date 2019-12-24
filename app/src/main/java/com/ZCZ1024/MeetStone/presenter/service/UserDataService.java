package com.ZCZ1024.MeetStone.presenter.service;

import com.ZCZ1024.MeetStone.Entity.Acount;
import com.ZCZ1024.MeetStone.Entity.UserInfo;
import com.ZCZ1024.MeetStone.EntityVo.UserInfoVo;
import com.ZCZ1024.MeetStone.EntityVo.UserVo;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserDataService {

    @GET("Acount/API/getuser")
    Flowable<UserVo> getUser(@Query("id") String id);

    @GET("Get/Test")
    Flowable<List<Acount>> getAcount();

    //获取队员集合
    @POST("Acount/API")
    Flowable<UserInfoVo> getuinfo(@Path("id") String userid);


    //注册接口请求，返回一个map，包含一个success（true和false）和error(状态码)
    @FormUrlEncoded
    @POST("Acount/API/signup")
    Flowable<Map<String,String>> regist(@FieldMap Map<String,String> registinfo);


    //登陆接口请求。返回状态码
    @FormUrlEncoded
    @POST("Acount/API/login")
    Flowable<Map<String,String>> LoginTest(@FieldMap Map<String,String> acount);

    @FormUrlEncoded
    @POST("updatePswd")
    Flowable<String> updatePswd(@FieldMap Map<String,String> newpswd);

    @FormUrlEncoded
    @POST("updateUinfo")
    Flowable<Map<String,String>> updateUinfo(@Path("userid") String userid,@Body UserInfo userInfo);

}
