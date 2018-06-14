package com.example.manito15.mytype;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.manito15.mytype.fragment.MapsFragment;
import com.example.manito15.mytype.fragment.ReviewReceiverInfoFragment;
import com.example.manito15.mytype.lib.GoLib;

/**
 * 선물 후기 등록 액티비티
 * 실제 사용자 화면은 프래그먼트로 구성한다.
 */
public class ReviewRegisterActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_register);

        context = this;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.content_main, new ReviewReceiverInfoFragment());
        //fragmentTransaction.add(R.id.content_main, new MapsFragment());
        fragmentTransaction.commit();



        //setToolbar();

        // ReviewReceiverInfoFragment를 화면에 보여준다.
        //GoLib.getInstance().goFragment(getSupportFragmentManager(), R.id.content_main, new ReviewReceiverInfoFragment());
    }

//    /**
//     * 툴바 설정
//     */
//    private void setToolbar(){
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        ActionBar actionBar = getSupportActionBar();
//
//        TextView title = toolbar.findViewById(R.id.toolbar_title);
//        TextView close = toolbar.findViewById(R.id.toolbar_right);
//
//        if(actionBar != null){
//            actionBar.setDisplayShowTitleEnabled(false);
//            title.setText(R.string.title_review);
//            close.setText(R.string.close);
//
//            close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            });
//        }
//    }

}
