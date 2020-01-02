package com.ZCZ1024.MeetStone.presenter.service;

import com.ZCZ1024.MeetStone.Entity.Martch;
import com.ZCZ1024.MeetStone.EntityVo.MartchVo;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MartchDataService {
    @GET("Contest/API/getcontest")
    Flowable <MartchVo> getMartches();
}
