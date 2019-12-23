package com.ZCZ1024.MeetStone.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ZCZ1024.MeetStone.Entity.User;
import com.ZCZ1024.MeetStone.Fragments.FragmentAllTeam;
import com.ZCZ1024.MeetStone.Fragments.FragmentDynmic;
import com.ZCZ1024.MeetStone.Fragments.FragmentMartch;
import com.ZCZ1024.MeetStone.Fragments.FragmentMyTeam;
import com.ZCZ1024.MeetStone.MakeDynmicActivity;
import com.ZCZ1024.MeetStone.R;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.liji.circleimageview.CircleImageView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends BaseActivity implements View.OnClickListener {

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

        textViewtitle = findViewById(R.id.tv_title);
        linearLayoutseach = findViewById(R.id.ly_search);
        textViewtitle.setVisibility(View.GONE);
        linearLayoutseach.setVisibility(View.VISIBLE);

        for (LinearLayout layout : layouts) {
            layout.setOnClickListener(this);
        }

        //侧滑栏菜单响应事件
        navigationView.setNavigationItemSelectedListener(navViewclicklisener());

        /*
         * 侧滑栏弹出事件
         * */
        LinearLayout linearLayout = findViewById(R.id.snavbar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });

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

            default:
                break;
        }

    }

    /* 侧滑栏头像点击事件
     * 如果用户未登录，跳转到登陆界面
     * 如果用户已登录，跳转到用户主页
     *
     * 顶部图标点击事件
     * */

    public void TopNavbar(View view) {
        switch (view.getId()){
            case R.id.nav_headpt:
                //设置侧滑栏头像点击响应
                if (user.getUname() != null) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    startActivity(new Intent(this, ShowUserPage.class));
                }
                break;
            case R.id.bt_search:
                break;

            case R.id.img_homepage_msg:
                startActivity(new Intent(this,ApplyCheckActivity.class));
                break;

            case R.id.img_homepage_add_dynmic:
                startActivity(new Intent(this, MakeDynmicActivity.class));
                break;

            default:
                break;
        }

    }

    /**
     * 侧滑栏菜单点击事件响应
     * 根据对应菜单选项跳转到对应页面
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
                        startActivity(new Intent(getBaseContext(), UserInfoActivity.class));
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

    /**
     * 初始化底部导航按钮的样式（未点击状态）
     */
    private void initbottomNvaStyle() {
        TextView textView1 = findViewById(R.id.tv_fragment_martch);
        TextView textView2 = findViewById(R.id.tv_fragment_allteam);
        TextView textView3 = findViewById(R.id.tv_fragment_myteam);
        TextView textView4 = findViewById(R.id.tv_fragment_dynmic);

        ImageView martch = findViewById(R.id.fragment_img_martch);
        ImageView team = findViewById(R.id.fragment_img_alltem);
        ImageView myteam = findViewById(R.id.fragment_img_myteam);
        ImageView dynmic = findViewById(R.id.fragment_img_dynmic);

        textView1.setTextColor(Color.parseColor("#8a8a8a"));
        textView2.setTextColor(Color.parseColor("#8a8a8a"));
        textView3.setTextColor(Color.parseColor("#8a8a8a"));
        textView4.setTextColor(Color.parseColor("#8a8a8a"));
        martch.setImageResource(R.drawable.martch_off);
        team.setImageResource(R.drawable.team_off);
        myteam.setImageResource(R.drawable.myteam_off);
        dynmic.setImageResource(R.drawable.dynmic_off);
    }


    /**
     *设置底部导航栏对应点击样式
     */
    private void setFragmentColor(int imagId, int textvid) {
        imgfragment = findViewById(imagId);
        tvfragmentname = findViewById(textvid);
        tvfragmentname.setTextColor(getColor(R.color.home_footer));
        ImageView img_msg = findViewById(R.id.img_homepage_msg);
        ImageView img_add_dynmic = findViewById(R.id.img_homepage_add_dynmic);

        img_msg.setVisibility(View.VISIBLE);
        img_add_dynmic.setVisibility(View.GONE);
        switch (imagId) {
            case R.id.fragment_img_martch:
                imgfragment.setImageResource(R.drawable.martch_on);
                break;

            case R.id.fragment_img_alltem:
                imgfragment.setImageResource(R.drawable.team_on);
                break;

            case R.id.fragment_img_myteam:
                imgfragment.setImageResource(R.drawable.myteam_on);
                break;

            case R.id.fragment_img_dynmic:
                imgfragment.setImageResource(R.drawable.dynmic_on);
                img_msg.setVisibility(View.GONE);
                img_add_dynmic.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }

}