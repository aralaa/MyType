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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manito15.mytype.MyApp;
import com.example.manito15.mytype.R;
import com.example.manito15.mytype.adapter.ReviewListAdapter;
import com.example.manito15.mytype.item.ReviewItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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

    //ReviewListAdapter reviewListAdapter;
    ListAdapter listAdapter;
    List<ReviewItem> lstReview;

    private List<ImageDTO> imageDTOs = new ArrayList<>();
    private List<String> uidLists = new ArrayList<>();
    private FirebaseDatabase database;

    CheckBox img_like;
    private String like=null;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
//        memberId = ((MyApp)this.getActivity().getApplicationContext()).getMemberId();

        v =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview); //리싸이클러뷰 가져옴

        database = FirebaseDatabase.getInstance();


        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        //reviewListAdapter = new ReviewListAdapter(context, R.layout.row_review_list, new ArrayList<ReviewItem>());
        //recyclerView.setAdapter(reviewListAdapter);
        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        img_like=(CheckBox) v.findViewById(R.id.img_like);
        img_like.setOnClickListener(this);
        database.getReference().child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                imageDTOs.clear();;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ImageDTO imageDTO = snapshot.getValue(ImageDTO.class);
                    imageDTOs.add(imageDTO);
                }
                listAdapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //lstReview = new ArrayList<>();
        //lstReview.add(new ReviewItem("고아라1"));
        //lstReview.add(new ReviewItem("고아라2"));
        //lstReview.add(new ReviewItem("고아라3"));
        //lstReview.add(new ReviewItem("고아라4"));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_like:
                if (img_like.isChecked()) {
                    // TODO : CheckBox is checked.
                    img_like.setButtonDrawable(R.drawable.ic_favorite_on_black_24dp);
                    like="good";
                } else {
                    // TODO : CheckBox is unchecked.
                    img_like.setButtonDrawable(R.drawable.ic_favorite_off_black_24dp);
                    like=null;
                }
                break;
        }
    }
    class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((CustomViewHolder)holder).txt_name.setText(imageDTOs.get(position).userId);
            ((CustomViewHolder)holder).txt_age.setText(imageDTOs.get(position).edt_price);

            Glide.with(holder.itemView.getContext()).load(imageDTOs.get(position).imageUrl).into(((CustomViewHolder)holder).imageView);


        }

        @Override
        public int getItemCount() {
            return imageDTOs.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView txt_name;
            TextView txt_age;
            TextView txt_age_range;

            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.img_review);
                txt_name = (TextView) view.findViewById(R.id.txt_name);
                txt_age = (TextView) view.findViewById(R.id.txt_age);
                txt_age_range= (TextView) view.findViewById(R.id.txt_age_range);
            }
        }
    }

}