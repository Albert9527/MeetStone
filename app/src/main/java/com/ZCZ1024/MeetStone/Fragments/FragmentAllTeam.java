package com.ZCZ1024.MeetStone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Activity.Apply_Team;
import com.ZCZ1024.MeetStone.Adapter.AllteamViewAdapter;
import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentAllTeam extends Fragment {
    private RecyclerView recyclerView;
    private AllteamViewAdapter viewAdapter;
    private List<Team> teams;

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
        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.tv_teamname:
                        Toast.makeText(view.getContext(), position + "被点击", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_teamapply:
                        startActivity(new Intent(getContext(),Apply_Team.class));
                }
            }
        };

        viewAdapter.setOnItemClickListener(listener);
        recyclerView.setAdapter(viewAdapter);

        initData();

        return recyclerView;
    }

    private void initData() {
        teams = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Team team = new Team("index" + i);
            teams.add(team);
        }

        viewAdapter.setAllTeamData(teams);
    }


    public static interface OnItemClickListener {
        void itemClick(int position, View view);
    }
}
