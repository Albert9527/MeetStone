package com.ZCZ1024.MeetStone.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Activity.MakeTeamActivity;
import com.ZCZ1024.MeetStone.Entity.Martch;
import com.ZCZ1024.MeetStone.Fragments.FragmentMartch;
import com.ZCZ1024.MeetStone.R;

import java.util.List;

public class MartchViewAdpter extends RecyclerView.Adapter<MartchViewAdpter.ListViewHolder> {
   private List<Martch> martches;
   private FragmentMartch.OnItemClickListener listener;

    public MartchViewAdpter(List<Martch> martches) {
        this.martches = martches;
    }

    public void setOnItemClickListener(FragmentMartch.OnItemClickListener listener){
        this.listener = listener;
    }

    public void setMartches(List<Martch> martches){
        this.martches = martches;
    }

    @NonNull
    @Override
    public MartchViewAdpter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_martch,parent,false);
        return new MartchViewAdpter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MartchViewAdpter.ListViewHolder holder, final int position) {

        final Martch martch = martches.get(position);
        holder.MartchName.setText(martch.getMartchname());
        holder.bt_ToMakeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.itemClick(position, view);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return martches.size();
    }

    public void startac(Intent intent ,Class c){
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView MartchName,MartchUrl,Date,fabuzhe;
        Button bt_ToMakeTeam;

        public ListViewHolder(@NonNull View itemView){

            //获取itemUi
            super(itemView);
            bt_ToMakeTeam = itemView.findViewById(R.id.bt_tomaketeam);
            MartchName = itemView.findViewById(R.id.tv_martchname);
        }
    }
}
