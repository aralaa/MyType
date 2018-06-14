package com.example.manito15.mytype.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manito15.mytype.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "HomeFragment";
    Context context;
    View v;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    //ReviewListAdapter reviewListAdapter;
    ListAdapter listAdapter;

    private List<ImageDTO> imageDTOs = new ArrayList<>(); //ImageDTO
    private List<String> uidLists = new ArrayList<>();
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
//        memberId = ((MyApp)this.getActivity().getApplicationContext()).getMemberId();

        v =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerv_view); //리싸이클러뷰 가져옴

        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        //reviewListAdapter = new ReviewListAdapter(context, R.layout.row_review_list, new ArrayList<ReviewItem>());
        //recyclerView.setAdapter(reviewListAdapter);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);

        setupToolbar(); //툴바 셋팅


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
                ImageDTO post = dataSnapshot.getValue(ImageDTO.class);
                //imageDTOs.add(post);
                imageDTOs.clear();
                uidLists.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ImageDTO imageDTO = snapshot.getValue(ImageDTO.class);//ImageDTO
                    String uidKey = snapshot.getKey();
                    imageDTOs.add(imageDTO);
                    uidLists.add(uidKey);
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

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {


    }
    class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((CustomViewHolder)holder).txt_age.setText(imageDTOs.get(position).edt_review_write);
            Glide.with(holder.itemView.getContext()).load(imageDTOs.get(position).imageUrl).into(((CustomViewHolder)holder).imageView);
            ((CustomViewHolder)holder).txt_name.setText(imageDTOs.get(position).userId);
            ((CustomViewHolder)holder).starButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStarClicked(database.getReference().child("images").child(uidLists.get(position)));
                }
            });

            if (imageDTOs.get(position).stars.containsKey(auth.getCurrentUser().getUid())) {
                ((CustomViewHolder)holder).starButton.setImageResource(R.drawable.ic_favorite_on_black_24dp);

            }else {
                ((CustomViewHolder)holder).starButton.setImageResource(R.drawable.ic_favorite_off_black_24dp);
            }
        }

        @Override
        public int getItemCount() {
            return imageDTOs.size();
        }
        private void onStarClicked(DatabaseReference postRef) {
            postRef.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    ImageDTO imageDTO = mutableData.getValue(ImageDTO.class);
                    if (imageDTO == null) {
                        return Transaction.success(mutableData);
                    }

                    if (imageDTO.stars.containsKey(auth.getCurrentUser().getUid())) {
                        // Unstar the post and remove self from stars
                        imageDTO.starCount = imageDTO.starCount - 1;
                        imageDTO.stars.remove(auth.getCurrentUser().getUid());
                    } else {
                        // Star the post and add self to stars
                        imageDTO.starCount = imageDTO.starCount + 1;
                        imageDTO.stars.put(auth.getCurrentUser().getUid(), true);
                    }

                    // Set value and report transaction success
                    mutableData.setValue(imageDTO);
                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean b,
                                       DataSnapshot dataSnapshot) {
                    // Transaction completed

                }
            });
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView txt_name;
            TextView txt_age;
            ImageView starButton;

            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.img_review);
                txt_name = (TextView) view.findViewById(R.id.txt_name);
                txt_age = (TextView) view.findViewById(R.id.txt_age);
                starButton = (ImageView)view.findViewById(R.id.item_starButton_imageView);
            }
        }
    }

    /**
     * Toolbar Setup
     */
    private void setupToolbar(){
        Log.d(TAG, "setupToolbar: Toolbar 셋팅");

        final Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        TextView title = (TextView) v.findViewById(R.id.toolbar_title);
        title.setText("취향저격");
    }
}