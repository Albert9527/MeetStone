package com.ZCZ1024.MeetStone.Util;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;


public class RefreshUtil {

    public static void refresh(Context context, View view, int id, OnRefreshListener onRefreshListener){
        RefreshLayout refreshLayout = view.findViewById(id);
        refreshLayout.setRefreshHeader(new WaveSwipeHeader(context));
        //设置 Header 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(context));
        //设置 Footer 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(onRefreshListener);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1500);
            }
        });

    }
}
