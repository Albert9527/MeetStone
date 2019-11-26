package com.ZCZ1024.MeetStone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Entity.Dynamic;
import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.Fragments.FragmentAllTeam;
import com.ZCZ1024.MeetStone.R;

import java.util.List;

public class DynamicViewAdpter extends RecyclerView.Adapter<DynamicViewAdpter.ListViewHolder>{
    private List<Dynamic> dynamics;
    private FragmentAllTeam.OnItemClickListener listener;

    public DynamicViewAdpter(List<Dynamic> dynamics) {
        this.dynamics = dynamics;
    }

    public void setOnItemClickListener(FragmentAllTeam.OnItemClickListener listener){
        this.listener = listener;
    }

    public void setDynamics(List<Dynamic> dynamics) {
        this.dynamics = dynamics;

        //刷新数据
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DynamicViewAdpter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dynmic,parent,false);
        return new DynamicViewAdpter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {

        Dynamic dynamic = dynamics.get(position);
        holder.textView.setText(dynamic.getContent());
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
        return dynamics.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ListViewHolder(@NonNull View itemView){

            //获取itemUi
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);

        }
    }
}

