package com.example.manito15.mytype.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manito15.mytype.Review.PostActivity;
import com.example.manito15.mytype.R;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;

    private ArrayList<String> mImgProfile = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mAge = new ArrayList<>();
    private ArrayList<String> mAgeRange = new ArrayList<>();
    private ArrayList<String> mRegDate = new ArrayList<>();
    private ArrayList<String> mImgReview = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mImgProfile, ArrayList<String> mName, ArrayList<String> mAge, ArrayList<String> mAgeRange, ArrayList<String> mRegDate, ArrayList<String> mImgReview) {
        this.mContext = mContext;
        this.mImgProfile = mImgProfile;
        this.mName = mName;
        this.mAge = mAge;
        this.mAgeRange = mAgeRange;
        this.mRegDate = mRegDate;
        this.mImgReview = mImgReview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_review_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .load(mImgProfile.get(position))
                .into(holder.imgProfie);

        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .load(mImgReview.get(position))
                .into(holder.imgReview);

        holder.name.setText(mName.get(position));
        holder.age.setText(mAge.get(position));
        holder.ageRange.setText(mAgeRange.get(position));
        holder.regDate.setText(mRegDate.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mName.get(position));

                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra("imgProfile", mImgProfile.get(position));
                intent.putExtra("name", mName.get(position));
                intent.putExtra("age", mAge.get(position));
                intent.putExtra("ageRange", mAgeRange.get(position));
                intent.putExtra("regDate", mRegDate.get(position));
                intent.putExtra("imgReview", mImgReview.get(position));

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgProfie;
        public TextView name;
        public TextView age;
        public TextView ageRange;
        public TextView regDate;
        public ImageView imgLike;
        public ImageView imgReview;
        public RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            imgProfie = (ImageView) itemView.findViewById(R.id.img_profie);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            age = (TextView) itemView.findViewById(R.id.txt_age);
            ageRange = (TextView) itemView.findViewById(R.id.txt_age_range);
            regDate = (TextView) itemView.findViewById(R.id.txt_regdate);
            imgLike = (ImageView) itemView.findViewById(R.id.img_like);
            imgReview = (ImageView) itemView.findViewById(R.id.img_review);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.parent_layout);

        }

    }
}
