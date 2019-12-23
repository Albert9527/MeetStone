package com.ZCZ1024.MeetStone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Activity.ApplyCheckActivity;
import com.ZCZ1024.MeetStone.Entity.Apply;
import com.ZCZ1024.MeetStone.R;

import java.util.List;

public class ApplyCheckAdapter extends RecyclerView.Adapter<ApplyCheckAdapter.ListViewHolder>{

    private List<Apply> applies;
    private ApplyCheckActivity.OnItemClickListener listener;
    private View.OnClickListener viewlistener;

    public ApplyCheckAdapter(List<Apply> applies) {
        this.applies = applies;
    }

    public void setListener(ApplyCheckActivity.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setApplies(List<Apply> applies) {
        this.applies = applies;

        //刷新数据
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iteam_apply_list,parent,false);
        return new ApplyCheckAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
            viewlistener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.itemClick(position,v);
                }
            };

            Apply apply = applies.get(position);
            holder.tv_appler_name.setText(apply.getContent());
            holder.img_agree.setOnClickListener(viewlistener);
            holder.img_refuse.setOnClickListener(viewlistener);
    }

    @Override
    public int getItemCount() {
        return applies.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_appler_name;
        ImageView img_agree,img_refuse;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_appler_name = itemView.findViewById(R.id.tv_applycheck_username);
            img_agree = itemView.findViewById(R.id.btn_apply_agree_item);
            img_refuse = itemView.findViewById(R.id.btn_apply_refuse_item);
        }
    }
}
