package com.ZCZ1024.MeetStone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Activity.MakeTeamActivity;
import com.ZCZ1024.MeetStone.Adapter.MartchViewAdpter;
import com.ZCZ1024.MeetStone.Entity.Martch;
import com.ZCZ1024.MeetStone.EntityVo.MartchVo;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.RefreshUtil;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.MartchDataService;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FragmentMartch extends BaseFragment {
    private List<Martch> martches;
    private RecyclerView recyclerView;
    private MartchViewAdpter viewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_martch, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_martch);

        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity().getBaseContext(), 2));

        viewAdapter = new MartchViewAdpter(null);
        FragmentMartch.OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {

                //组队按钮跳转页面
                Intent intent = new Intent(view.getContext(), MakeTeamActivity.class);
                intent.putExtra("martchname", martches.get(position).getName());
                startActivity(intent);
            }
        };

        viewAdapter.setOnItemClickListener(listener);

        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider));
        recyclerView.addItemDecoration(itemDecoration);

        //刷新功能
        RefreshUtil.refresh(getContext(), view, R.id.refresh_martch,
                new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        loadData();
                        refreshLayout.finishRefresh(1500);
                    }
                });


        recyclerView.setAdapter(viewAdapter);
        martches = new ArrayList<>();

        loadData();

        return view;
    }

/*    private void initData() {
        for (int i = 0; i < 8; i++) {
            Martch martch = new Martch("动态内容" + i);
            martches.add(martch);
        }
        viewAdapter.setMartches(martches);
    }*/


    private void loadData() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(MartchDataService.class)
                        .getMartches()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<MartchVo>() {
                            @Override
                            public void accept(MartchVo martchVo) throws Exception {
                                if (martchVo.isSuccess()== true) {
                                    martches = martchVo.getData();
                                    if (martches!=null)
                                    viewAdapter.setMartches(martches);
                                    else
                                        Toast.makeText(getContext(),"没有获取到数据",Toast.LENGTH_LONG).show();
                                }
                                else
                                    Toast.makeText(getContext(),martchVo.getError()+"数据获取失败",Toast.LENGTH_LONG).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("error",throwable.getMessage());
                                Toast.makeText(getContext(),"数据获取失败,错误原因："+throwable.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        })
        );
    }


   /* private void getMartchData() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(MartchDataService.class)
                        .getMartches()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Martch>>() {
                            @Override
                            public void accept(List<Martch> martches) throws Exception {
                                viewAdapter.setMartches(martches);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }));
    }*/

    public static interface OnItemClickListener {
        void itemClick(int position, View view);
    }
}
