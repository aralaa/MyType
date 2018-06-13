//package com.example.manito15.mytype.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.manito15.mytype.fragment.ImageDTO;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by LG on 2018-06-14.
// */
//
//public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.VH> {
//    public List<ImageDTO> parkingList;
//
//    public Context context;
//    ArrayList<ImageDTO> mCountryModel;
//
//    public SearchAdapter(List<ImageDTO> parkingList, Context context) {
//        this.parkingList = parkingList;
//        this.context = context;
//    }
//
//
//    @Override
//    public SearchAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new SearchAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(SearchAdapter.VH holder, int position) {
//        holder.t1.setText(parkingList.get(position).getPostTitle());
//        holder.t2.setText(parkingList.get(position).getPostSubTitle());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return parkingList.size();
//    }
//
//    public class VH extends RecyclerView.ViewHolder {
//        TextView t1, t2;
//
//        public VH(View itemView) {
//            super(itemView);
//            t1 = (TextView) itemView.findViewById(R.id.txt_name);
//            t2 = (TextView) itemView.findViewById(R.id.txt_age);
//        }
//    }
//
//    public void setFilter(List<Post> countryModels) {
//        mCountryModel = new ArrayList<>();
//        mCountryModel.addAll(countryModels);
//        notifyDataSetChanged();
//    }
//}