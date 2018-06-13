package com.example.manito15.mytype;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        // 글씨체 변경
        TextView tv=(TextView)findViewById(R.id.app_name);
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/lotte_medium.ttf");
        tv.setTypeface(face);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);


    }
}
