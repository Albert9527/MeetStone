package com.ZCZ1024.MeetStone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Activity.MakeTeamActivity;
import com.ZCZ1024.MeetStone.Adapter.MartchViewAdpter;
import com.ZCZ1024.MeetStone.Entity.Martch;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.MartchDataService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FragmentMartch extends BaseFragment{
    private List<Martch> martches;
    private RecyclerView recyclerView;
    private MartchViewAdpter viewAdpter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_martch, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_martch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewAdpter = new MartchViewAdpter(null);
        FragmentMartch.OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                Toast.makeText(view.getContext(),position+"被点击",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), MakeTeamActivity.class);
                intent.putExtra("martchname",martches.get(position).getMartchname());
                startActivity(intent);
            }
        };

        viewAdpter.setOnItemClickListener(listener);

        recyclerView.setAdapter(viewAdpter);

        initData();

        return view;
    }

    private void initData() {
        martches = new ArrayList<>();
        for (int i = 0;i<5;i++){
            Martch martch = new Martch("index"+i);
            martches.add(martch);
        }
        viewAdpter.setMartches(martches);
    }

    private void getMartchData(){
        addDisposable(
        RetrofitFactory.getRetrofit()
                .create(MartchDataService.class)
                .getMartches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Martch>>() {
                    @Override
                    public void accept(List<Martch> martches) throws Exception {
                        viewAdpter.setMartches(martches);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    public static interface OnItemClickListener{
        void itemClick(int position, View view);
    }
}
