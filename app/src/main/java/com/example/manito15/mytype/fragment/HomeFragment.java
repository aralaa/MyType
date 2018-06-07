package com.example.manito15.mytype.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manito15.mytype.MyApp;
import com.example.manito15.mytype.R;
import com.example.manito15.mytype.adapter.ReviewListAdapter;
import com.example.manito15.mytype.item.ReviewItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    View v;
    Context context;
    int memberId;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TextView noDataText;

    ReviewListAdapter reviewListAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
//        memberId = ((MyApp)this.getActivity().getApplicationContext()).getMemberId();

        v =  inflater.inflate(R.layout.fragment_home, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        reviewListAdapter = new ReviewListAdapter(context, R.layout.row_review_list, new ArrayList<ReviewItem>());
        recyclerView.setAdapter(reviewListAdapter);

    }

    @Override
    public void onClick(View v) {

    }

}