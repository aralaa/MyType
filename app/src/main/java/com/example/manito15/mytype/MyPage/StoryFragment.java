package com.example.manito15.mytype.MyPage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manito15.mytype.R;
import com.example.manito15.mytype.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryFragment extends Fragment {

    private static final String TAG = "StoryFragment";
    View view;

    //vars
    private ArrayList<String> mImgProfile = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mAge = new ArrayList<>();
    private ArrayList<String> mAgeRange = new ArrayList<>();
    private ArrayList<String> mRegDate = new ArrayList<>();
    private ArrayList<String> mImgReview = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_story, container, false);
        Log.d(TAG, "onCreateView: started");

        initImageBitmaps();

        return view;
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImgProfile.add("https://i.pinimg.com/564x/56/80/c7/5680c753fcc8840501009bcf4eba7da7.jpg");
        mName.add("아라");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("1일전");
        mImgReview.add("https://i.pinimg.com/564x/56/80/c7/5680c753fcc8840501009bcf4eba7da7.jpg");

        mImgProfile.add("https://i.pinimg.com/564x/56/80/c7/5680c753fcc8840501009bcf4eba7da7.jpg");
        mName.add("아라");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("15분전");
        mImgReview.add("https://i.pinimg.com/564x/0f/e6/59/0fe659c6c8c0364449dbf815c5f73e2d.jpg");

        mImgProfile.add("https://i.pinimg.com/564x/56/80/c7/5680c753fcc8840501009bcf4eba7da7.jpg");
        mName.add("아라");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("3일전");
        mImgReview.add("https://i.pinimg.com/564x/b6/20/f2/b620f23dd9d0f5d0928cecf4409fdf4f.jpg");

        mImgProfile.add("https://i.pinimg.com/564x/56/80/c7/5680c753fcc8840501009bcf4eba7da7.jpg");
        mName.add("아라");
        mAge.add("20대");
        mAgeRange.add("후반");
        mRegDate.add("30일전");
        mImgReview.add("https://i.pinimg.com/564x/8a/ed/e1/8aede1e5053e8508eb7cb93c2086abf7.jpg");


        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: ");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(((AppCompatActivity)getActivity()), mImgProfile, mName, mAge, mAgeRange, mRegDate, mImgReview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(((AppCompatActivity)getActivity())));
    }

}
