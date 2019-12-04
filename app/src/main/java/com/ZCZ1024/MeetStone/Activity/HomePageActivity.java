package com.ZCZ1024.MeetStone.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ZCZ1024.MeetStone.CustomView.CircleView;
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


public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentAllTeam fragmentAllTeam;
    private FragmentMartch fragmentMartch;
    private FragmentMyTeam fragmentMyTeam;
    private FragmentDynmic fragmentDynmic;
    private FragmentManager fragmentManager;
    private TextView textViewtitle;
    private LinearLayout linearLayoutseach;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CircleImageView circleView_headpt;
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
        TextView textView_allteam = findViewById(R.id.fragment_allteam);
        TextView textView_martch = findViewById(R.id.fragment_martch);
        TextView textView_myteam = findViewById(R.id.fragment_myteam);
        TextView textView_dynmic = findViewById(R.id.fragment_dynmic);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);
        LinearLayout linearLayout = findViewById(R.id.snavbar);
        View headerView = navigationView.getHeaderView(0);


        circleView_headpt = headerView.findViewById(R.id.nav_headpt);
        textViewtitle = findViewById(R.id.tv_title);
        linearLayoutseach = findViewById(R.id.ly_search);
        textViewtitle.setVisibility(View.GONE);
        linearLayoutseach.setVisibility(View.VISIBLE);

        textView_allteam.setOnClickListener(this);
        textView_martch.setOnClickListener(this);
        textView_myteam.setOnClickListener(this);
        textView_dynmic.setOnClickListener(this);

        linearLayout.setOnClickListener(this);
        circleView_headpt.setOnClickListener(this);


        navigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(HomePageActivity.this,menuItem.getTitle()
                        .toString(),Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
        getUser();

        if (user.getTouxiang() == null) {
            //加载网络图片
            Glide.with(HomePageActivity.this.getBaseContext())
                    .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575460315770&di=543181b2a7da1b5f045c4b41988d103e&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20111024%2FImg323139260.jpg")
                    .into(circleView_headpt);
        }

    }

    public User getUser(){
       /*user = new User("name",
                "C:\\Users\\Shinelon\\Desktop\\nva_bg.JPG");
        Log.d("zd1120",user.getTouxiang());
        return user;*/
      return user = new User();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_martch:
                textViewtitle.setVisibility(View.GONE);
                linearLayoutseach.setVisibility(View.VISIBLE);
                setTabSelection(0);
                break;
            case R.id.fragment_allteam:
                textViewtitle.setVisibility(View.GONE);
                linearLayoutseach.setVisibility(View.VISIBLE);
                setTabSelection(1);
                break;
            case R.id.fragment_myteam:
                textViewtitle.setVisibility(View.VISIBLE);
                textViewtitle.setText("我的队伍");
                linearLayoutseach.setVisibility(View.GONE);
                setTabSelection(2);
                break;
            case R.id.fragment_dynmic:
                textViewtitle.setVisibility(View.VISIBLE);
                textViewtitle.setText("动态");
                linearLayoutseach.setVisibility(View.GONE);
                setTabSelection(3);
                break;
            case R.id.snavbar:
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else {
                    drawerLayout.openDrawer(navigationView);
                }
                break;
            case R.id.nav_headpt:
                if (user.getUname() == null){
                    startActivity(new Intent(this,LoginActivity.class));
                }
            default:
                break;
        }

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
}