package com.ZCZ1024.MeetStone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.Fragments.FragmentAllTeam;
import com.ZCZ1024.MeetStone.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder> {

    private List<Team> allTeamData;
    private FragmentAllTeam.OnItemClickListener listener;

    public RecyclerViewAdapter(List<Team> allTeamData) {
        this.allTeamData = allTeamData;
    }

    /*public RecyclerViewAdapter(FragmentAllTeam.OnItemClickListener listener) {
        this.listener = listener;
    }
*/

    /*public RecyclerViewAdapter(List<Team> allTeamData, FragmentAllTeam.OnItemClickListener listener) {
        this.allTeamData = allTeamData;
        this.listener = listener;
    }*/

    public void setOnItemClickListener(FragmentAllTeam.OnItemClickListener listener){
        this.listener = listener;
    }

    public void setAllTeamData(List<Team> allTeamData) {
        this.allTeamData = allTeamData;

        //刷新数据
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_teamshow,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {

        Team team = allTeamData.get(position);
        holder.textView.setText(team.getName());
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
        return allTeamData.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ListViewHolder(@NonNull View itemView){

            //获取itemUi
            super(itemView);
            textView = itemView.findViewById(R.id.tv_teamname);

        }
    }
}
