package com.ZCZ1024.MeetStone.presenter.service;

import com.ZCZ1024.MeetStone.Entity.Dynamic;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface DynamicDataService {
    @GET()
    Flowable<Dynamic> getAllDynmic();
}
