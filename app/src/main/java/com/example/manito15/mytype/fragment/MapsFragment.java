package com.example.manito15.mytype.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manito15.mytype.R;
import com.example.manito15.mytype.lib.GoLib;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements View.OnClickListener{


    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setToolbar();
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }
    /**
     * 액티비티 툴바를 설정
     */
    private void setToolbar(){
        final Toolbar toolbar = (Toolbar) ((AppCompatActivity)getActivity()).findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.location);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goback:
                //돌아가는 것 코딩 필요
                GoLib.getInstance().goFragmentBack(((AppCompatActivity)getActivity()).getSupportFragmentManager(), R.id.content_main, new ReviewReceiverInfoFragment());
                //Toast.makeText(context, "버튼 클릭", Toast.LENGTH_LONG).show();
                break;}
    }
}
