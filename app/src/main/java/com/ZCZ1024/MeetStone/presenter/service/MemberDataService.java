package com.ZCZ1024.MeetStone.presenter.service;

import com.ZCZ1024.MeetStone.Entity.Member;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MemberDataService {

    @GET()
    Flowable<List<Member>> getMembers();
}
