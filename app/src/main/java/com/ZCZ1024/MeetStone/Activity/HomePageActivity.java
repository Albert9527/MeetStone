package com.ZCZ1024.MeetStone.Activity;

import android.os.Bundle;

import com.ZCZ1024.MeetStone.Fragments.FragmentAllTeam;
import com.ZCZ1024.MeetStone.Fragments.FragmentDynmic;
import com.ZCZ1024.MeetStone.Fragments.FragmentMartch;
import com.ZCZ1024.MeetStone.Fragments.FragmentMyTeam;
import com.ZCZ1024.MeetStone.R;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.TextView;


public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentAllTeam fragmentAllTeam;
    private FragmentMartch fragmentMartch;
    private FragmentMyTeam fragmentMyTeam;
    private FragmentDynmic fragmentDynmic;
    private FragmentManager fragmentManager;

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

        textView_allteam.setOnClickListener(this);
        textView_martch.setOnClickListener(this);
        textView_myteam.setOnClickListener(this);
        textView_dynmic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_martch:
                setTabSelection(0);
                break;
            case R.id.fragment_allteam:
                setTabSelection(1);
                break;
            case R.id.fragment_myteam:
                setTabSelection(2);
                break;
            case R.id.fragment_dynmic:
                setTabSelection(3);
                break;
            default:
                    break;
        }

    }

    private void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);

        switch (index){
            case 0:
                if (fragmentMartch == null){
                    fragmentMartch = new FragmentMartch();
                transaction.add(R.id.fragment,fragmentMartch);
                }else {
                    transaction.show(fragmentMartch);
                }
                break;

            case 1:
                if (fragmentAllTeam == null){
                    fragmentAllTeam = new FragmentAllTeam();
                    transaction.add(R.id.fragment,fragmentAllTeam);
                }else {
                    transaction.show(fragmentAllTeam);
                }
                break;

            case 2:
                if (fragmentMyTeam == null){
                    fragmentMyTeam = new FragmentMyTeam();
                    transaction.add(R.id.fragment,fragmentMyTeam);
                }else {
                    transaction.show(fragmentMyTeam);
                }
                break;

            case 3:
                if (fragmentDynmic == null){
                    fragmentDynmic = new FragmentDynmic();
                    transaction.add(R.id.fragment,fragmentDynmic);
                }else {
                    transaction.show(fragmentDynmic);
                }
                break;

        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragmentMartch != null){
            transaction.hide(fragmentMartch);
        }
        if (fragmentAllTeam != null){
            transaction.hide(fragmentAllTeam);
        }
        if (fragmentMyTeam != null){
            transaction.hide(fragmentMyTeam);
        }
        if (fragmentDynmic != null){
            transaction.hide(fragmentDynmic);
        }
        transaction.commit();
    }
}