package com.example.manito15.mytype.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.manito15.mytype.fragment.LikeFragment;
import com.example.manito15.mytype.fragment.ProfileFragment;
import com.example.manito15.mytype.fragment.StoryFragment;

public class MyTypeViewPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public MyTypeViewPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StoryFragment();
            case 1:
                return new LikeFragment();
            case 2:
                return new ProfileFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
