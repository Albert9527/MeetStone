package com.ZCZ1024.MeetStone.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Entity.User;
import com.ZCZ1024.MeetStone.Fragments.FragmentMyTeam;
import com.ZCZ1024.MeetStone.R;

import java.util.List;

public class MemberViewAdpter extends RecyclerView.Adapter<MemberViewAdpter.ListviewAdpter> {

    private TextView textView;
    private List<User> users;
    private FragmentMyTeam.OnItemClickListener listener;

    public MemberViewAdpter(List<User> users) {
        this.users = users;
    }
    public void setOnItemClickListener(FragmentMyTeam.OnItemClickListener listener){
        this.listener = listener;
    }

    public void setMember(List<User> users) {
        this.users = users;

        //刷新数据
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MemberViewAdpter.ListviewAdpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menber,parent,false);
        return new MemberViewAdpter.ListviewAdpter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListviewAdpter holder, final int position) {
        User user = users.get(position);
        holder.textView.setText(user.getUname());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.itemClick(position,v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ListviewAdpter extends RecyclerView.ViewHolder {
        TextView textView;
        public ListviewAdpter(@NonNull View itemView) {
            super(itemView);

        textView = itemView.findViewById(R.id.tv_member_uname);
        }

    }

}
