package com.example.manito15.mytype.Review;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.manito15.mytype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "MapsFragment";

    View view;

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_maps, container, false);

        Button btnBack = (Button) view.findViewById(R.id.goback);
        btnBack.setOnClickListener(this);

        setupToolbar();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goback:
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.content_main, new ReviewReceiverInfoFragment());
                fr.commit();
                break;
        }
    }

    /**
     * Toolbar Setup
     */
    private void setupToolbar(){
        Log.d(TAG, "setupToolbar: Toolbar 셋팅");

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        TextView title = (TextView) view.findViewById(R.id.toolbar_title);
        title.setText("위치등록");
        TextView exit = (TextView) view.findViewById(R.id.tv_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.content_main, new ReviewReceiverInfoFragment());
                fr.commit();
            }
        });
    }
}
