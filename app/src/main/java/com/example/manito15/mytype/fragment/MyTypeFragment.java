package com.example.manito15.mytype.fragment;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manito15.mytype.R;
import com.example.manito15.mytype.adapter.MyTypeViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTypeFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    MyTypeViewPagerAdapter pageAdapter;
    TabItem tabStory;
    TabItem tabLike;
    TabItem tabProfile;

    public MyTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_type, container, false);

        setToolbar();

        tabLayout = ((AppCompatActivity)getActivity()).findViewById(R.id.tablayout);
        tabStory = ((AppCompatActivity)getActivity()).findViewById(R.id.tab_story);
        tabLike = ((AppCompatActivity)getActivity()).findViewById(R.id.tab_like);
        tabProfile = ((AppCompatActivity)getActivity()).findViewById(R.id.tab_profile);
        viewPager =  ((AppCompatActivity)getActivity()).findViewById(R.id.viewPager);

        pageAdapter = new MyTypeViewPagerAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        return rootView;
    }

    /**
     * 액티비티 툴바를 설정
     */
    private void setToolbar(){
        final Toolbar toolbar = (Toolbar) ((AppCompatActivity)getActivity()).findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.title_mytype);
        }
    }


}
