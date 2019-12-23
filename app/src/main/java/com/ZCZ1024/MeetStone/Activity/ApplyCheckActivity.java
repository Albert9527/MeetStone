package com.ZCZ1024.MeetStone.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Adapter.ApplyCheckAdapter;
import com.ZCZ1024.MeetStone.Entity.Apply;
import com.ZCZ1024.MeetStone.R;

import java.util.ArrayList;
import java.util.List;

public class ApplyCheckActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ApplyCheckAdapter Viewadapter;
    private List<Apply> applies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_check);
        initView();
    }

    public void initView(){
        recyclerView = findViewById(R.id.recyclerview_apply_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Viewadapter = new ApplyCheckAdapter(null);

        ApplyCheckActivity.OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.btn_apply_agree_item:
                        Toast.makeText(view.getContext(), "btn_apply_agree_item被点击", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.btn_apply_refuse_item:
                        Toast.makeText(view.getContext(), "btn_apply_refuse_item被点击", Toast.LENGTH_LONG).show();

                        break;

                   /* case R.id.img_member_tx:
                        Toast.makeText(view.getContext(), "img_member_tx被点击", Toast.LENGTH_LONG).show();

                        break;*/

                    default:
                        break;
                }
            }
        };

        Viewadapter.setListener(listener);

        recyclerView.setAdapter(Viewadapter);

        initData();

    }

    private void initData(){
        applies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Apply apply = new Apply("index" + i);
            applies.add(apply);
        }
        Viewadapter.setApplies(applies);
    }
    @Override
    public void onClick(View v) {

    }

    public static interface OnItemClickListener {
        void itemClick(int position, View view);
    }
}
