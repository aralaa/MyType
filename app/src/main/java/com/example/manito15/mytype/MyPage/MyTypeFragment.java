package com.example.manito15.mytype.MyPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manito15.mytype.R;
import com.example.manito15.mytype.adapter.MyTypeViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTypeFragment extends Fragment {

    private final String TAG = "HomeFragment";
    Context context;
    View v;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MyTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_type, container, false);

        setupToolbar(); //툴바

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        MyTypeViewPagerAdapter adapter = new MyTypeViewPagerAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager());

        // 프래그먼트 추가하기
        adapter.AddFragment(new StoryFragment(), "스토리");
        adapter.AddFragment(new LikeFragment(), "좋아요");
        adapter.AddFragment(new ProfileFragment(), "프로필");

        // 어댑터 연결
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        return v;

    }

    /**
     * Toolbar Setup
     */
    private void setupToolbar(){
        Log.d(TAG, "setupToolbar: Toolbar 셋팅");

        final Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        TextView title = (TextView) v.findViewById(R.id.toolbar_title);
        title.setText("나의취향");
    }

}