package com.ZCZ1024.MeetStone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Entity.Martch;
import com.ZCZ1024.MeetStone.Fragments.FragmentMartch;
import com.ZCZ1024.MeetStone.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class MartchViewAdpter extends RecyclerView.Adapter<MartchViewAdpter.ListViewHolder> {
   private List<Martch> martches;
   private FragmentMartch.OnItemClickListener listener;
   private Context context;

    public MartchViewAdpter(List<Martch> martches) {
        this.martches = martches;
    }

    public void setOnItemClickListener(FragmentMartch.OnItemClickListener listener){
        this.listener = listener;
    }

    public void setMartches(List<Martch> martches){
        this.martches = martches;
        //刷新数据
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MartchViewAdpter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_martch,parent,false);
        this.context = parent.getContext();
        return new MartchViewAdpter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MartchViewAdpter.ListViewHolder holder, final int position) {

        final Martch martch = martches.get(position);
        setViewValue(martch);
        if (martch.getImg()!=null)
            //加载网络图片
            Glide.with(context)
                    .load("http://120.55.47.24:8080/img/" + martch.getImg())
                    .into(holder.img_photo);

        if (martch.getName()!=null)
        holder.MartchName.setText(martch.getName());

        if (martch.getCtgy()!=null)
            holder.tv_martch_ctgy.setText(martch.getCtgy());

        if (martch.getUpuser()!=null)
            holder.tv_upuser.setText(martch.getUpuser());

        if (martch.getUrl()!=null)
            holder.MartchUrl.setText(martch.getUrl());

        if (martch.getUptime()!=null)
            holder.tv_Date.setText(martch.getUptime());

        holder.bt_ToMakeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.itemClick(position, view);
                }
            }
        });
    }

    private void setViewValue(Martch martch) {
    }

    @Override
    public int getItemCount() {
        if (martches!=null)
        return martches.size();
        else
            return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView MartchName,MartchUrl,tv_Date,tv_upuser,tv_martch_ctgy;
        Button bt_ToMakeTeam;
        ImageView img_photo;

        public ListViewHolder(@NonNull View itemView){

            //获取itemUi
            super(itemView);
            bt_ToMakeTeam = itemView.findViewById(R.id.bt_tomaketeam);
            MartchName = itemView.findViewById(R.id.tv_martchname);
            MartchUrl = itemView.findViewById(R.id.tv_martch_url);
            tv_Date = itemView.findViewById(R.id.tv_martch_maketime);
            tv_upuser = itemView.findViewById(R.id.tv_martch_upuser);
            img_photo = itemView.findViewById(R.id.img_martch_photo);
            tv_martch_ctgy = itemView.findViewById(R.id.tv_martch_ctgy);

        }
    }
}
