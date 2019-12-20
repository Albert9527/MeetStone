package com.ZCZ1024.MeetStone.presenter.service;

import com.ZCZ1024.MeetStone.Entity.Acount;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserDataService {
    @POST("dx/sendSms")
    Flowable<Map<String,String>> getVerifvCode(@FieldMap Map<String,String> appcode,@FieldMap Map<String,String> registinfo);

    @GET("")
    Flowable<String> getUserInfo(@Path("userid") String userid);

    @GET("Get/Test")
    Flowable<List<Acount>> getAcount();

    //获取队员集合
    @POST("Get/Member")
    Flowable<List<Acount>> getMember(@Path("teamId") String teamid);


    //注册接口请求，返回一个map，包含一个success（true和false）和error(状态码)
    @FormUrlEncoded
    @POST("Acount/API/signup")
    Flowable<Map<String,String>> regist(@FieldMap Map<String,String> registinfo);

    @FormUrlEncoded
    @POST("login")
    Flowable<String> LoginTest(@FieldMap Map<String,String> acount);

    @FormUrlEncoded
    @POST("updatePswd")
    Flowable<String> updatePswd(@FieldMap Map<String,String> newpswd);

}
