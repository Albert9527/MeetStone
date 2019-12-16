package com.ZCZ1024.MeetStone.presenter.service;

import com.ZCZ1024.MeetStone.Entity.Martch;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MartchDataService {
    @GET("")
    Flowable <List<Martch>> getMartches();
}
