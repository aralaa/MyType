package com.example.manito15.mytype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PostActivity extends AppCompatActivity {

    private Context mContext = PostActivity.this;

    private static final String TAG = "PostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        setupToolbar();

    }

    /**
     * Toolbar Setup
     */
    private void setupToolbar() {
        Log.d(TAG, "setupToolbar: Toolbar 셋팅");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText("취향저격");

        TextView exit = (TextView) findViewById(R.id.tv_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        getIncomingIntent();
    }

    //데이터 가져오기
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if(getIntent().hasExtra("imgProfile") && getIntent().hasExtra("name")
                && getIntent().hasExtra("age") && getIntent().hasExtra("ageRange")
                && getIntent().hasExtra("regDate") && getIntent().hasExtra("imgReview")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imgProfile = getIntent().getStringExtra("imgProfile");
            String name = getIntent().getStringExtra("name");
            String age = getIntent().getStringExtra("age");
            String ageRange = getIntent().getStringExtra("ageRange");
            String regDate = getIntent().getStringExtra("regDate");
            String imgReview = getIntent().getStringExtra("imgReview");

            setImage(imgProfile, name, age, ageRange, regDate, imgReview);

        }
    }
    
    private void setImage(String imgProfile, String name, String age, String ageRange, String regDate, String imgReview){
        Log.d(TAG, "setImage: setting the image and name th widgets");

        ImageView mImgProfile = findViewById(R.id.img_profie);
        TextView mName = findViewById(R.id.txt_name);
        TextView mAge = findViewById(R.id.txt_age);
        TextView mAgeRange = findViewById(R.id.txt_age_range);
        TextView mRegDate = findViewById(R.id.txt_regdate);
        ImageView mImgReview = findViewById(R.id.img_review);

        mName.setText(name);
        mAge.setText(age);
        mAgeRange.setText(ageRange);
        mRegDate.setText(regDate);

        Glide.with(this)
                .asBitmap()
                .load(imgProfile)
                .into(mImgProfile);

        Glide.with(this)
                .asBitmap()
                .load(imgReview)
                .into(mImgReview);

    }
}
