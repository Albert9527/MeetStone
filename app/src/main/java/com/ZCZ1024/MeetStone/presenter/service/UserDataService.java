package com.ZCZ1024.MeetStone.presenter.service;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserDataService {
    @GET("")
    Flowable<String> LoginTest(@Path("username") String username, String password);

    @GET("")
    Flowable<String> getUserInfo(@Path("userid") String userid);
}
