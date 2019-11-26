package com.ZCZ1024.MeetStone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Adapter.MemberViewAdpter;
import com.ZCZ1024.MeetStone.Entity.User;
import com.ZCZ1024.MeetStone.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentMyTeam extends Fragment {
    private List<User> users;
    private RecyclerView recyclerView;
    private MemberViewAdpter viewAdpter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_myteam, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_member);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getBaseContext()));

        viewAdpter = new MemberViewAdpter(null);

        FragmentMyTeam.OnItemClickListener listener = new FragmentMyTeam.OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                Toast.makeText(view.getContext(),position+"被点击",Toast.LENGTH_LONG).show();
            }
        };

        viewAdpter.setOnItemClickListener(listener);
        recyclerView.setAdapter(viewAdpter);

        initData();

        return recyclerView;
    }

    private void initData() {
        users = new ArrayList<>();
        for (int i = 0;i<5;i++){
            User user = new User("index"+i);
            users.add(user);
        }
        viewAdpter.setMember(users);
    }

    public static interface OnItemClickListener{
        void itemClick(int position, View view);
    }
}
