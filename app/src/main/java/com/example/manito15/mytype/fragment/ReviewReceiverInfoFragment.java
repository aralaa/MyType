package com.example.manito15.mytype.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manito15.mytype.MainActivity;
import com.example.manito15.mytype.R;
import com.example.manito15.mytype.lib.GoLib;

/**
 * 선물 후기를 등록하는 액티비티
 */
public class ReviewReceiverInfoFragment extends Fragment implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    //public static final String INFO_SEQ = "INFO_SEQ";

    Activity context;
    //int infoseq;

    View v;

    public ReviewReceiverInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = this.getActivity();
        v = inflater.inflate(R.layout.fragment_review_receiver_info, container, false);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_info_next = (Button) v.findViewById(R.id.btn_info_next);
        Button select_offline = (Button) v.findViewById(R.id.select_offline);
        btn_info_next.setOnClickListener(this);
        select_offline.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_info_next:
                GoLib.getInstance().goFragmentBack(((AppCompatActivity)getActivity()).getSupportFragmentManager(), R.id.content_main, new ReviewPhotoFragment());
                break;
            case R.id.select_offline:
                //map fragment로 가는 것 코딩 필요
                GoLib.getInstance().goFragmentBack(((AppCompatActivity)getActivity()).getSupportFragmentManager(), R.id.content_main, new MapsFragment());
                //Toast.makeText(context, "버튼 클릭", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
