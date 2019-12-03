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

import com.ZCZ1024.MeetStone.Adapter.MartchViewAdpter;
import com.ZCZ1024.MeetStone.Adapter.MemberViewAdpter;
import com.ZCZ1024.MeetStone.Entity.Martch;
import com.ZCZ1024.MeetStone.Entity.User;
import com.ZCZ1024.MeetStone.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentMartch extends Fragment {
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

    public static interface OnItemClickListener{
        void itemClick(int position, View view);
    }
}
