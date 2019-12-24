package com.ZCZ1024.MeetStone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Activity.LoginActivity;
import com.ZCZ1024.MeetStone.Adapter.AllteamViewAdapter;
import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.Util.CreatDialogUtil;
import com.ZCZ1024.MeetStone.Util.RefreshUtil;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.TeamDataService;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentAllTeam extends BaseFragment {
    private RecyclerView recyclerView;
    private AllteamViewAdapter viewAdapter;
    private List<Team> teams;
    private OnItemClickListener listener;
    private View.OnClickListener clicklistener;
    private CreatDialogUtil creatDialogUtil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allteam, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_allteam);

        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity().getBaseContext(), 2));

        //设置适配器
        viewAdapter = new AllteamViewAdapter(null);
        listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.tv_teamname:
                        Toast.makeText(view.getContext(), position + "被点击", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_teamapply:
                        if(AcuntInfo.geteditInfo(getContext(),"userid") == null)
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        else{
                            showEditDialog();
                            creatDialogUtil.tv_Apply_user.setText(AcuntInfo.geteditInfo(getContext(),"userid"));
                            creatDialogUtil.tv_Apply_group.setText(teams.get(position).getName());
                        }
                        break;
                    case R.id.bt_applyteam:
                        creatDialogUtil.showinfo();
                        break;
                    default:
                        break;
                }
            }
        };

        viewAdapter.setOnItemClickListener(listener);

        //全屏水滴刷新
        RefreshUtil.refresh(getContext(), view, R.id.refresh_allteam,
                new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        initData();
                        refreshLayout.finishRefresh(1000);
                    }
                });

        recyclerView.setAdapter(viewAdapter);

        initData();
        return view;
    }

    private void  initData(){
        teams = new ArrayList<>(teams);
        for (int i=0;i<8;i++){
            Team team = new Team("队伍名"+i);
            teams.add(team);
        }
        viewAdapter.setAllTeamData(teams);
    }

    /**
     * 设置数据
     *
     */
    private void loadData() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(TeamDataService.class)
                        .getAllTeam()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Team>>() {
                            @Override
                            public void accept(List<Team> teams) throws Exception{

                                viewAdapter.setAllTeamData(teams);
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

    public void showEditDialog() {
        clicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDialogUtil.showinfo();
            }
        };
        creatDialogUtil = new CreatDialogUtil(this.getActivity(),R.style.ownColorbyZD,clicklistener);
        creatDialogUtil.show();
    }


}
