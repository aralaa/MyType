package com.example.manito15.mytype.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manito15.mytype.R;
import com.example.manito15.mytype.lib.GoLib;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewWriteFragment extends Fragment implements View.OnClickListener{

    private final String TAG = this.getClass().getSimpleName();
    Activity context;

    View v;

    public ReviewWriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = this.getActivity();
        v = inflater.inflate(R.layout.fragment_review_write, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_write_next = (Button) v.findViewById(R.id.btn_write_next);
        Button btn_prev = (Button) v.findViewById(R.id.btn_prev);

        btn_write_next.setOnClickListener(this);
        btn_prev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_write_next:
                GoLib.getInstance().goFragmentBack(((AppCompatActivity)getActivity()).getSupportFragmentManager(), R.id.content_main, new ReviewPhotoFragment());
                break;
            case R.id.btn_prev:
                GoLib.getInstance().goBackFragment(((AppCompatActivity)getActivity()).getSupportFragmentManager());
                break;
        }

    }
}
