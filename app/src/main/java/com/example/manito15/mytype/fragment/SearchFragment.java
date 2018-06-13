package com.example.manito15.mytype.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.manito15.mytype.MainActivity;
import com.example.manito15.mytype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{

    private final String TAG = "HomeFragment";
    Context context;
    View v;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        context = this.getActivity();
        v =  inflater.inflate(R.layout.fragment_search, container, false);

        setupToolbar(); //툴바 셋팅

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button_filter = (Button) v.findViewById(R.id.button_filter); //필터로 검색 버튼

        //Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar); //toolbar
        //setSupportActionBar(toolbar);

        button_filter.setOnClickListener(this);

        SeekBar sb  = (SeekBar) v.findViewById(R.id.seekBar1);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_filter: //필터로 검색 버튼
                break;


        }

    }

    /**
     * Toolbar Setup
     */
    private void setupToolbar(){
        Log.d(TAG, "setupToolbar: Toolbar 셋팅");

        final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        TextView title = (TextView) v.findViewById(R.id.toolbar_title);
        title.setText("검색툴바");
    }

}
