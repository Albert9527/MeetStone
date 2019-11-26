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
import com.ZCZ1024.MeetStone.Adapter.RecyclerViewAdapter;
import com.ZCZ1024.MeetStone.Entity.Dynamic;
import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentDynmic extends Fragment {
    public RecyclerView recyclerView;
    public DynamicViewAdpter viewAdapter;
    public List<Dynamic> dynamics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynmic, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_dynmic);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getBaseContext()));

        viewAdapter = new DynamicViewAdpter(null);

        FragmentAllTeam.OnItemClickListener listener = new FragmentAllTeam.OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                Toast.makeText(view.getContext(),position+"被点击",Toast.LENGTH_LONG).show();
            }
        };

        viewAdapter.setOnItemClickListener(listener);
        recyclerView.setAdapter(viewAdapter);

        initData();

        return recyclerView;
    }

    private void initData() {
        dynamics = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            Dynamic dynamic = new Dynamic("index" + i);
            dynamics.add(dynamic);
        }

        viewAdapter.setDynamics(dynamics);
    }


    public static interface OnItemClickListener{
        void itemClick(int position, View view);
    }
}