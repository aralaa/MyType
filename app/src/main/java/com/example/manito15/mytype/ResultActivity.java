package com.example.manito15.mytype;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.manito15.mytype.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private final String TAG = "TestFragment";
    Context mContext;

    //vars
    private ArrayList<String> mImgProfile = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mAge = new ArrayList<>();
    private ArrayList<String> mAgeRange = new ArrayList<>();
    private ArrayList<String> mRegDate = new ArrayList<>();
    private ArrayList<String> mImgReview = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Log.d(TAG, "onCreate: started");

        setupToolbar();
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImgProfile.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");
        mName.add("고아라");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("16분전");
        mImgReview.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");

        mImgProfile.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");
        mName.add("고아라");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("16분전");
        mImgReview.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");

        mImgProfile.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");
        mName.add("고아라");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("16분전");
        mImgReview.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");

        mImgProfile.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");
        mName.add("고아라");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("16분전");
        mImgReview.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: ");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImgProfile, mName, mAge, mAgeRange, mRegDate, mImgReview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Toolbar Setup
     */
    private void setupToolbar(){
        Log.d(TAG, "setupToolbar: Toolbar 셋팅");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText("검색결과");
    }
}
