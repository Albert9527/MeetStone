package com.ZCZ1024.MeetStone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ZCZ1024.MeetStone.Adapter.ApplyCheckAdapter;
import com.ZCZ1024.MeetStone.Entity.Apply;
import com.ZCZ1024.MeetStone.EntityVo.ApplyVo;
import com.ZCZ1024.MeetStone.R;
import com.ZCZ1024.MeetStone.Util.AcuntInfo;
import com.ZCZ1024.MeetStone.Util.DiaoLogUtil;
import com.ZCZ1024.MeetStone.presenter.NetWorkData.RetrofitFactory;
import com.ZCZ1024.MeetStone.presenter.service.TeamDataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

    public void initView() {
        recyclerView = findViewById(R.id.recyclerview_apply_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Viewadapter = new ApplyCheckAdapter(null);

        ApplyCheckActivity.OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.btn_apply_agree_item:
                        if (DiaoLogUtil.showWindow(view.getContext(), "是否确定同意该申请？") == 1) {
                           /* dealApply(position, 1);*/
                            applies.remove(position);
                            Viewadapter.setApplies(applies);
                        }
                        break;

                    case R.id.btn_apply_refuse_item:
                        if (DiaoLogUtil.showWindow(view.getContext(), "是否确定拒绝该申请？") == 1) {
                           /* dealApply(position, 0);*/
                            applies.remove(position);
                            Viewadapter.setApplies(applies);
                        }
                        break;

                    case R.id.img_member_tx:
                        Toast.makeText(view.getContext(), "img_member_tx被点击", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getBaseContext(),ShowUserPage.class));
                        break;


                    default:
                        break;
                }
            }
        };

        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider));
        recyclerView.addItemDecoration(itemDecoration);

        Viewadapter.setListener(listener);

        recyclerView.setAdapter(Viewadapter);

        initData();

    }

    private void initData() {
        applies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Apply apply = new Apply("index" + i);
            applies.add(apply);
        }
        Viewadapter.setApplies(applies);
    }

    /*private void loadData() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(TeamDataService.class)
                        .getApply()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ApplyVo>() {
                            @Override
                            public void accept(ApplyVo applyVo) throws Exception {

                                if (applyVo.issuccess == true) {
                                    applies = applyVo.getData();
                                    Viewadapter.setApplies(applies);
                                } else
                                    Toast.makeText(getBaseContext(), "请求失败：" + applyVo.geterror, Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getBaseContext(), "网络链接错误：" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    private void dealApply(int p, int result) {
        Map map = new HashMap();
        map.put("", applies.get(p).getId);
        map.put("", result);
        map.put("", AcuntInfo.geteditInfo(this, "userid"));
        RetrofitFactory.getRetrofit()
                .create(TeamDataService.class)
                .dealApply(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, String>>() {
                    @Override
                    public void accept(Map<String, String> map) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


    }*/

    @Override
    public void onClick(View v) {

    }

    public static interface OnItemClickListener {
        void itemClick(int position, View view);
    }

    public void closeActivity(View view) {
        this.finish();
    }
}
