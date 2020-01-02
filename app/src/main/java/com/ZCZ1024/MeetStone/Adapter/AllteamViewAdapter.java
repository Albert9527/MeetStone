package com.ZCZ1024.MeetStone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Entity.Team;
import com.ZCZ1024.MeetStone.Fragments.FragmentAllTeam;
import com.ZCZ1024.MeetStone.R;

import java.util.List;

public class AllteamViewAdapter extends RecyclerView.Adapter<AllteamViewAdapter.ListViewHolder> {

    private List<Team> allTeamData;
    private FragmentAllTeam.OnItemClickListener listener;

    public AllteamViewAdapter(List<Team> allTeamData) {
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

    public void setOnItemClickListener(FragmentAllTeam.OnItemClickListener listener) {
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
                .inflate(R.layout.item_teamshow, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {

        Team team = allTeamData.get(position);
        if (team.getTime()!=null)
            holder.textViewMadetime.setText(team.getTime());
        if (team.getName()!=null)
            holder.textViewTname.setText(team.getName());

        if (team.getCtgy()!=null)
            holder.textViewCtgy.setText(team.getCtgy());

        if (team.getIntro()!=null)
            holder.textViewTintro.setText(team.getIntro());

        if (team.getCaptain()!=null)
            holder.textViewTcaptain.setText(team.getCaptain());

        if (team.getMax()!=null&&team.getQuota()!=null)
            holder.textViewQuota.setText(Integer.parseInt(team.getMax())-Integer.parseInt(team.getQuota())+"");
        holder.textViewTname.setText(team.getName());
        holder.textViewTname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, v);
                }
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
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
        if (allTeamData!=null)
            return allTeamData.size();
        else
            return 0;
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTname;
        TextView textViewTcaptain;
        TextView textViewTintro;
        TextView textViewMadetime;
        TextView textViewQuota;
        TextView textViewCtgy;
        Button button;

        public ListViewHolder(@NonNull View itemView) {

            //获取itemUi
            super(itemView);
            textViewTname = itemView.findViewById(R.id.tv_teamname);
            textViewTintro = itemView.findViewById(R.id.tv_teamintro);
            textViewTcaptain = itemView.findViewById(R.id.tv_teamcaptain);
            textViewMadetime = itemView.findViewById(R.id.tv_teammadetime);
            textViewQuota = itemView.findViewById(R.id.tv_teamquota);
            button = itemView.findViewById(R.id.bt_teamapply);
            textViewCtgy = itemView.findViewById(R.id.tv_teamctgy);

        }
    }
}
