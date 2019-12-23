package com.ZCZ1024.MeetStone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Adapter.DynamicViewAdpter;
import com.ZCZ1024.MeetStone.Entity.Dynamic;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.RefreshUtil;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.DynamicDataService;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FragmentDynmic extends BaseFragment {
    public RecyclerView recyclerView;
    public DynamicViewAdpter viewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynmic, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_dynmic);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getBaseContext()));

        viewAdapter = new DynamicViewAdpter(null);

        FragmentDynmic.OnItemClickListener listener = new FragmentDynmic.OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                Toast.makeText(view.getContext(), position + "被点击", Toast.LENGTH_LONG).show();
            }
        };

        viewAdapter.setOnItemClickListener(listener);


        //全屏水滴样式刷新实现
        RefreshUtil.refresh(getContext(), view, R.id.refresh_dynmic,
                new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        loadData();
                        refreshLayout.finishRefresh(1000);
                    }
                });

        recyclerView.setAdapter(viewAdapter);

        loadData();

        return view;
    }

    private void loadData() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(DynamicDataService.class)
                        .getAllDynmic()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Dynamic>>() {
                            @Override
                            public void accept(List<Dynamic> dynamics) throws Exception {

                                viewAdapter.setDynamics(dynamics);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
        );
    }


    public static interface OnItemClickListener {
        void itemClick(int position, View view);
    }
}