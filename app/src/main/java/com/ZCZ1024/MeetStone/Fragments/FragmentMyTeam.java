package com.ZCZ1024.MeetStone.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Adapter.MemberViewAdpter;
import com.ZCZ1024.MeetStone.Entity.Member;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.RefreshUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentMyTeam extends Fragment implements View.OnClickListener {
    private List<Member> members;
    private RecyclerView recyclerView;
    private MemberViewAdpter viewAdpter;
    private Button btn_member_remove, btn_member_outTeam, btn_member_change_Captain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myteam, container, false);

        initView(view);

        recyclerView = view.findViewById(R.id.recyclerview_member);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewAdpter = new MemberViewAdpter(null);
        FragmentMyTeam.OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.tv_member_more:
                        PopupMenulisener(view);
                        Toast.makeText(view.getContext(), "btn_member_outteam被点击", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.tv_member_uname:
                    Toast.makeText(view.getContext(), "tv_member_uname被点击", Toast.LENGTH_LONG).show();

                    break;

                    case R.id.img_member_tx:
                        Toast.makeText(view.getContext(), "img_member_tx被点击", Toast.LENGTH_LONG).show();

                        break;

                    default:
                        break;
                }

            }
        };

        viewAdpter.setOnItemClickListener(listener);


        //实现recyclerView全屏水滴刷新
        RefreshUtil.refresh(getContext(), view, R.id.refresh_member,
                new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        initData();
                        refreshLayout.finishRefresh(1500);
                    }
                });

        recyclerView.setAdapter(viewAdpter);

        initData();

        return view;
    }

    private void initView(View v) {
        btn_member_remove = v.findViewById(R.id.btn_member_remove);
        btn_member_outTeam = v.findViewById(R.id.btn_member_outTeam);
        btn_member_change_Captain = v.findViewById(R.id.btn_member_change_Captain);

        btn_member_remove.setOnClickListener(this);
        btn_member_outTeam.setOnClickListener(this);
        btn_member_change_Captain.setOnClickListener(this);
    }

    private void initData() {
        members = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Member member = new Member("index" + i);
            members.add(member);
        }
        viewAdpter.setMember(members);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_member_remove:
                Toast.makeText(v.getContext(), "btn_member_remove被点击", Toast.LENGTH_LONG).show();

                break;

            case R.id.btn_member_outTeam:
                Toast.makeText(v.getContext(), "btn_member_outteam被点击", Toast.LENGTH_LONG).show();

                break;

            case R.id.btn_member_change_Captain:
                Toast.makeText(v.getContext(), "btn_member_change_Captain被点击", Toast.LENGTH_LONG).show();

                break;

            default:
                break;
        }
    }

    private void PopupMenulisener(View view){
        final PopupMenu popupMenu = new PopupMenu(getContext(),view);
        popupMenu.getMenuInflater().inflate(R.menu.menber_menu,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.member_menu_remove:
                        Toast.makeText(getContext(), "member_remove被点击", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        popupMenu.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                popupMenu.dismiss();
            }
        },3000);
    }
    public static interface OnItemClickListener {
        void itemClick(int position, View view);
    }
}
