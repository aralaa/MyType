package com.example.manito15.mytype.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
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

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;


    //ReviewListAdapter reviewListAdapter;
    ListAdapter listAdapter;
    //List<ReviewItem> lstReview;

    private List<ImageDTO> imageDTOs = new ArrayList<>(); //ImageDTO
    private List<String> uidLists = new ArrayList<>();


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

        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        //reviewListAdapter = new ReviewListAdapter(context, R.layout.row_review_list, new ArrayList<ReviewItem>());
        //recyclerView.setAdapter(reviewListAdapter);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);


        //database.getReference().child("images").addValueEventListener(new ValueEventListener() {
        //    @Override
        //    public void onDataChange(DataSnapshot dataSnapshot) {

        //        imageDTOs.clear();
        //        HomeDTO imageDTO = dataSnapshot.getValue(HomeDTO.class);
                //for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                //     HomeDTO imageDTO = snapshot.getValue(HomeDTO.class);//ImageDTO
                //     imageDTOs.add(imageDTO);
                //}

        //        listAdapter.notifyDataSetChanged();
        //        String value = dataSnapshot.getValue(String.class);
        //        // Log.d(TAG, "Value is: " + value);

        //    }

        //   @Override
        //   public void onCancelled(DatabaseError databaseError) {
                // Log.w(TAG, "Failed to read value.", databaseError.toException());
        //    }
        //});
        //FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
        //   @Override
        //    public void onDataChange(DataSnapshot dataSnapshot) {
        //        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
        //            Log.d("MainActivity", "Single ValueEventListener : " + snapshot.getValue());
         //       }
         //   }

        //    @Override
        //    public void onCancelled(DatabaseError databaseError) {

        //    }
        //});


        DatabaseReference ref = database.getReference("images");

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HomeDTO post = dataSnapshot.getValue(HomeDTO.class);
                //imageDTOs.add(post);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ImageDTO imageDTO = snapshot.getValue(ImageDTO.class);//ImageDTO
                    imageDTOs.add(imageDTO);
                }
                listAdapter.notifyDataSetChanged();
                System.out.println(post);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //img_like=(CheckBox) v.findViewById(R.id.img_like);
        //img_like.setOnClickListener(this);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {

        //switch (v.getId()) {
            //case R.id.img_like:
                //if (img_like.isChecked()) {
                    // TODO : CheckBox is checked.
                    //img_like.setButtonDrawable(R.drawable.ic_favorite_on_black_24dp);
                    //like="good";
                //} else {
                    // TODO : CheckBox is unchecked.
                    //img_like.setButtonDrawable(R.drawable.ic_favorite_off_black_24dp);
                    //like=null;
                //}
                //break;
        //}
    }
    class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((CustomViewHolder)holder).txt_age.setText(imageDTOs.get(position).edt_review_write);
            Glide.with(holder.itemView.getContext()).load(imageDTOs.get(position).imageUrl).into(((CustomViewHolder)holder).imageView);
            ((CustomViewHolder)holder).txt_name.setText(imageDTOs.get(position).userId);

        }

        @Override
        public int getItemCount() {
            return imageDTOs.size();
        }


        private class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView txt_name;
            TextView txt_age;


            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.img_review);
                txt_name = (TextView) view.findViewById(R.id.txt_name);
                txt_age = (TextView) view.findViewById(R.id.txt_age);

            }
        }
    }

}