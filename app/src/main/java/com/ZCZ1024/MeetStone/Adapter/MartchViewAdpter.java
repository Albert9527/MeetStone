package com.ZCZ1024.MeetStone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    public void onBindViewHolder(@NonNull MartchViewAdpter.ListViewHolder holder, final int position) {

        Martch martch = martches.get(position);
        holder.textView.setText(martch.getMartchname());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return martches.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ListViewHolder(@NonNull View itemView){

            //获取itemUi
            super(itemView);
            textView = itemView.findViewById(R.id.tv_martchname);

        }
    }
}
