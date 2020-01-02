package com.ZCZ1024.MeetStone.presenter.service;

import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.EntityVo.ApplyVo;
import com.ZCZ1024.MeetStone.EntityVo.CodeVo;
import com.ZCZ1024.MeetStone.EntityVo.TeamVo;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TeamDataService {

    @GET("Team/API/getteams")
    Flowable<TeamVo> getAllTeam();

    @FormUrlEncoded
    @POST("Acount/API/apply")
    Flowable<Map<String,String>> ApplyTeam(@FieldMap Map<String,String> apply);

    @FormUrlEncoded
    @POST("Team/API/creatteam")
    Flowable<Map<String,String>> maketeam(@FieldMap Map<String,String> newTeam);

    Disposable getTeamInfo();

    @GET("")
    Flowable<ApplyVo> getApply();

    @POST
    Flowable<Map<String,String>> dealApply(@FieldMap Map dealResult);
}
