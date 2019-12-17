package com.ZCZ1024.MeetStone.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ZCZ1024.MeetStone.Entity.User;
import com.ZCZ1024.MeetStone.Fragments.FragmentAllTeam;
import com.ZCZ1024.MeetStone.Fragments.FragmentDynmic;
import com.ZCZ1024.MeetStone.Fragments.FragmentMartch;
import com.ZCZ1024.MeetStone.Fragments.FragmentMyTeam;
import com.ZCZ1024.MeetStone.R;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.liji.circleimageview.CircleImageView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentAllTeam fragmentAllTeam;
    private FragmentMartch fragmentMartch;
    private FragmentMyTeam fragmentMyTeam;
    private FragmentDynmic fragmentDynmic;
    private FragmentManager fragmentManager;
    private TextView textViewtitle;
    private LinearLayout linearLayoutseach;

    private ImageView imgfragment;
    private TextView tvfragmentname;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private List<LinearLayout> layouts;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();

        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    private void init() {

        layouts = new ArrayList<>();
        LinearLayout layout_allteam = findViewById(R.id.fragment_allteam);
        LinearLayout textView_martch = findViewById(R.id.fragment_martch);
        LinearLayout textView_myteam = findViewById(R.id.fragment_myteam);
        LinearLayout textView_dynmic = findViewById(R.id.fragment_dynmic);

        layouts.add(layout_allteam);
        layouts.add(textView_martch);
        layouts.add(textView_myteam);
        layouts.add(textView_dynmic);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);
        LinearLayout linearLayout = findViewById(R.id.snavbar);
        layouts.add(linearLayout);

        textViewtitle = findViewById(R.id.tv_title);
        linearLayoutseach = findViewById(R.id.ly_search);
        textViewtitle.setVisibility(View.GONE);
        linearLayoutseach.setVisibility(View.VISIBLE);

        for (LinearLayout layout : layouts) {
            layout.setOnClickListener(this);
        }


        //侧滑栏菜单响应事件
        navigationView.setNavigationItemSelectedListener(navViewclicklisener());

        getUser();

        if (user.getTouxiang() == null) {
            for (CircleImageView circleImageView : initheadpic())
                //加载网络图片
                Glide.with(HomePageActivity.this.getBaseContext())
                        .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575460315770&di=543181b2a7da1b5f045c4b41988d103e&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20111024%2FImg323139260.jpg")
                        .into(circleImageView);
        }

    }

    public User getUser() {
        return user = new User();
    }

    private void setFragmentColor(int imagId, int textvid) {
        imgfragment = findViewById(imagId);
        tvfragmentname = findViewById(textvid);
        imgfragment.setImageResource(R.drawable.all_team_on);
        tvfragmentname.setTextColor(Color.parseColor("#EE3B3B"));
    }

    //设置当前用户所有页面头像
    private List<CircleImageView> initheadpic() {
        List<CircleImageView> imageViews = new ArrayList<>();
        View headerView = navigationView.getHeaderView(0);
        CircleImageView circleView_headpt = headerView.findViewById(R.id.nav_headpt);
        imageViews.add(circleView_headpt);

        CircleImageView img_usertx = findViewById(R.id.img_usertx);

        imageViews.add(img_usertx);

        return imageViews;
    }

    //Fragment
    @Override
    public void onClick(View v) {
        //初始化底部导航栏选定状态
        initbottomNvaStyle();

        switch (v.getId()) {
            case R.id.fragment_martch:
                textViewtitle.setVisibility(View.GONE);
                linearLayoutseach.setVisibility(View.VISIBLE);
                setFragmentColor(R.id.fragment_img_martch, R.id.tv_fragment_martch);
                setTabSelection(0);
                break;
            case R.id.fragment_allteam:
                textViewtitle.setVisibility(View.GONE);
                linearLayoutseach.setVisibility(View.VISIBLE);
                setFragmentColor(R.id.fragment_img_alltem, R.id.tv_fragment_allteam);
                setTabSelection(1);
                break;
            case R.id.fragment_myteam:
                textViewtitle.setVisibility(View.VISIBLE);
                linearLayoutseach.setVisibility(View.GONE);
                setFragmentColor(R.id.fragment_img_myteam, R.id.tv_fragment_myteam);
                textViewtitle.setText("我的队伍");
                setTabSelection(2);
                break;
            case R.id.fragment_dynmic:
                textViewtitle.setVisibility(View.VISIBLE);
                linearLayoutseach.setVisibility(View.GONE);
                setFragmentColor(R.id.fragment_img_dynmic, R.id.tv_fragment_dynmic);
                textViewtitle.setText("动态");
                setTabSelection(3);
                break;

            /*
             * 侧滑栏弹出事件
             * */
            case R.id.snavbar:
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
                break;
            default:
                break;
        }

    }

    /* 侧滑栏头像点击事件
     * 如果用户未登录，跳转到登陆界面
     * 如果用户已登录，跳转到用户主页
     * */

    public void Nvaclick(View view) {

        //设置侧滑栏头像点击响应
        if (user.getUname() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, ShowUserInfo.class));
        }
    }

    /**
     * 侧滑栏菜单点击事件响应
     */

    public NavigationView.OnNavigationItemSelectedListener navViewclicklisener() {
        return new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.fans:
                        Toast.makeText(HomePageActivity.this, menuItem.getTitle()
                                .toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.foucs:
                        Toast.makeText(HomePageActivity.this, menuItem.getTitle()
                                .toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.userinfo:
                        Toast.makeText(HomePageActivity.this, menuItem.getTitle()
                                .toString(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        };
    }

    private void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);

        switch (index) {
            case 0:
                if (fragmentMartch == null) {
                    fragmentMartch = new FragmentMartch();
                    transaction.add(R.id.fragment, fragmentMartch);
                } else {
                    transaction.show(fragmentMartch);
                }
                break;

            case 1:
                if (fragmentAllTeam == null) {
                    fragmentAllTeam = new FragmentAllTeam();
                    transaction.add(R.id.fragment, fragmentAllTeam);
                } else {
                    transaction.show(fragmentAllTeam);
                }
                break;

            case 2:
                if (fragmentMyTeam == null) {
                    fragmentMyTeam = new FragmentMyTeam();
                    if (!fragmentMyTeam.isAdded()) {

                        transaction.add(R.id.fragment, fragmentMyTeam);
                    }
                } else {
                    transaction.show(fragmentMyTeam);
                }
                break;

            case 3:
                if (fragmentDynmic == null) {
                    fragmentDynmic = new FragmentDynmic();
                    transaction.add(R.id.fragment, fragmentDynmic);
                } else {
                    transaction.show(fragmentDynmic);
                }
                break;

        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragmentMartch != null) {
            transaction.hide(fragmentMartch);
        }
        if (fragmentAllTeam != null) {
            transaction.hide(fragmentAllTeam);
        }
        if (fragmentMyTeam != null) {
            transaction.hide(fragmentMyTeam);
        }
        if (fragmentDynmic != null) {
            transaction.hide(fragmentDynmic);
        }
        transaction.commit();
    }

    private void initbottomNvaStyle() {
        TextView textView1 = findViewById(R.id.tv_fragment_martch);
        TextView textView2 = findViewById(R.id.tv_fragment_allteam);
        TextView textView3 = findViewById(R.id.tv_fragment_myteam);
        TextView textView4 = findViewById(R.id.tv_fragment_dynmic);

        ImageView image1 = findViewById(R.id.fragment_img_martch);
        ImageView image2 = findViewById(R.id.fragment_img_alltem);
        ImageView image3 = findViewById(R.id.fragment_img_myteam);
        ImageView image4 = findViewById(R.id.fragment_img_dynmic);

        textView1.setTextColor(Color.parseColor("#8a8a8a"));
        textView2.setTextColor(Color.parseColor("#8a8a8a"));
        textView3.setTextColor(Color.parseColor("#8a8a8a"));
        textView4.setTextColor(Color.parseColor("#8a8a8a"));
        image1.setImageResource(R.drawable.all_team_off);
        image2.setImageResource(R.drawable.all_team_off);
        image3.setImageResource(R.drawable.all_team_off);
        image4.setImageResource(R.drawable.all_team_off);
    }

}