package com.example.manito15.mytype;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        setToolbar();
        setView();

    }

    /**
     * 액티비티 툴바를 설정
     */
    private void setToolbar(){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.title_join);
        }
    }
    /**
     * 액티비티 화면 설정
     */
    private void setView(){
        ImageView join_Image = (ImageView) findViewById(R.id.join_image);
    }
}
