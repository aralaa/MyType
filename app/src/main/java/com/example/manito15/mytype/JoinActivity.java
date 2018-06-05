package com.example.manito15.mytype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class JoinActivity extends AppCompatActivity {

    EditText join_edt_email,join_edt_pw,join_edt_name,join_edt_year,join_edt_month,join_edt_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        setToolbar();
        setView();

        join_edt_email=(EditText) findViewById(R.id.join_edt_email);
        join_edt_pw=(EditText) findViewById(R.id.join_edt_pw);
        join_edt_name=(EditText) findViewById(R.id.join_edt_name);
        join_edt_year=(EditText) findViewById(R.id.join_edt_year);
        join_edt_month=(EditText) findViewById(R.id.join_edt_month);
        join_edt_day=(EditText) findViewById(R.id.join_edt_day);
        //성별에 대한 정보는 아직 가져오지 않음
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

    public void onJoinSuccessClicked(View view) {
        // 값 저장
        SharedPreferences pref = getSharedPreferences("a", MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("ID", join_edt_email.getText().toString());
        editor.putString("PWD", join_edt_pw.getText().toString());
        editor.putString("NAME", join_edt_name.getText().toString());
        editor.putString("YEAR", join_edt_year.getText().toString());
        editor.putString("MONTH", join_edt_month.getText().toString());
        editor.putString("DAY", join_edt_day.getText().toString());


        editor.commit();

        Intent intent =new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
