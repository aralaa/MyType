package com.example.manito15.mytype.Review;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.manito15.mytype.App.MainActivity;
import com.example.manito15.mytype.R;
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

        mImgProfile.add("https://i.pinimg.com/564x/56/80/c7/5680c753fcc8840501009bcf4eba7da7.jpg");
        mName.add("나리");
        mAge.add("30대");
        mAgeRange.add("후반");
        mRegDate.add("16분전");
        mImgReview.add("https://i.pinimg.com/564x/0f/e6/59/0fe659c6c8c0364449dbf815c5f73e2d.jpg");

        mImgProfile.add("https://i.pinimg.com/564x/e4/19/cb/e419cb3e56e6900e3a949da5677d6329.jpg");
        mName.add("모모");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("15분전");
        mImgReview.add("https://i.pinimg.com/564x/56/80/c7/5680c753fcc8840501009bcf4eba7da7.jpg");

        mImgProfile.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");
        mName.add("하니");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("16분전");
        mImgReview.add("https://i.pinimg.com/564x/b6/20/f2/b620f23dd9d0f5d0928cecf4409fdf4f.jpg");

        mImgProfile.add("https://techcrunch.com/wp-content/uploads/2018/05/android-jetpack.jpg?w=1390&crop=1");
        mName.add("유나");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("16분전");
        mImgReview.add("https://i.pinimg.com/564x/8a/ed/e1/8aede1e5053e8508eb7cb93c2086abf7.jpg");


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
        TextView exit = (TextView) findViewById(R.id.tv_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
