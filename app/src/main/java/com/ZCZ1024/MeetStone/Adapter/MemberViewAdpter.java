package com.ZCZ1024.MeetStone.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Entity.Member;
import com.ZCZ1024.MeetStone.Entity.User;
import com.ZCZ1024.MeetStone.Fragments.FragmentMyTeam;
import com.ZCZ1024.MeetStone.R;
import com.liji.circleimageview.CircleImageView;

import java.util.List;

public class MemberViewAdpter extends RecyclerView.Adapter<MemberViewAdpter.ListviewAdpter> {

    private List<Member> members;
    private FragmentMyTeam.OnItemClickListener listener;
    private View.OnClickListener viewlistener;

    public MemberViewAdpter(List<Member> members) {
        this.members = members;
    }
    public void setOnItemClickListener(FragmentMyTeam.OnItemClickListener listener){
        this.listener = listener;
    }

    public void setMember(List<Member> members) {
        this.members = members;

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
        viewlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.itemClick(position,v);
                }
            }
        };
        Member member = members.get(position);
        holder.tv_member_name.setText(member.getUname());
        holder.tv_member_tx.setOnClickListener(viewlistener);
        holder.tv_member_more.setOnClickListener(viewlistener);
        //holder.tv_member_content.setText(member.getContent());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class ListviewAdpter extends RecyclerView.ViewHolder {
        TextView tv_member_name,tv_member_more,tv_member_content,tv_member_teamrole;
        CircleImageView tv_member_tx;

        public ListviewAdpter(@NonNull View itemView) {
            super(itemView);

            tv_member_name = itemView.findViewById(R.id.tv_member_uname);
            tv_member_tx = itemView.findViewById(R.id.img_member_tx);
            tv_member_more = itemView.findViewById(R.id.tv_member_more);
            tv_member_content = itemView.findViewById(R.id.tv_member_content);
            tv_member_teamrole = itemView.findViewById(R.id.tv_member_teamrole);

        }

    }

}
