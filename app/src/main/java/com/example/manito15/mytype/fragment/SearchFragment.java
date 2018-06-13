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

    TextView seekBarText_satis;
    TextView seekBarText_price;

    String satisfaction;
    String price;

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

        button_filter.setOnClickListener(this);

        seekBarText_satis = (TextView) v.findViewById(R.id.seekBarText_satis);
        seekBarText_price = (TextView) v.findViewById(R.id.seekBarText_price);

        SeekBar seekBar_satis = (SeekBar) v.findViewById(R.id.seekBar_satis);
        SeekBar seekBar_price = (SeekBar) v.findViewById(R.id.seekBar_price);

        seekBar_satis.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarText_satis.setText(Integer.toString(progress));
                satisfaction = Integer.toString(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_price.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarText_satis.setText(Integer.toString(progress));
                price = Integer.toString(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
