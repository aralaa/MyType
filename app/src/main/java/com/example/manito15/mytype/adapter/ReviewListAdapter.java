package com.example.manito15.mytype.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manito15.mytype.MyApp;
import com.example.manito15.mytype.R;
import com.example.manito15.mytype.item.MemberInfoItem;
import com.example.manito15.mytype.item.ReviewItem;

import java.util.ArrayList;

/**
 * 리뷰 리스트의 아이테을 처리하는 어댑터
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private int resource;
    /** 리뷰 아이템 */
    private ArrayList<ReviewItem> itemList;
    /** 사용자정보 아이템 */
    private MemberInfoItem memberInfoItem;

    /**
     * 어댑터 생성자
     * @param context 컨텍스트 객체
     * @param resource 아이템을 보여주기 위해 사용할 리소스 아이디
     * @param itemList 아이템 리스트
     */
    public ReviewListAdapter(Context context, int resource, ArrayList<ReviewItem> itemList){

        this.context = context;
        this.resource = resource;
        this.itemList = itemList;

        memberInfoItem = ((MyApp) context.getApplicationContext()).getMemberInfoItem();

    }

    /**
     * 특정 아이템의 변경사항을 적용하기 위해 기본 아이템을 새로운 아이템으로 변경한다.
     * @param newItem
     */
    public void setItem(ReviewItem newItem) {
        for(int i=0; i<itemList.size(); i++){
            ReviewItem item = itemList.get(i);

            if(item.seq == newItem.seq){
                itemList.set(i, newItem);
                notifyItemChanged(i);
                break;
            }
        }
    }

    /**
     * 현재 아이템 리스트에 새로운 아이템 리스트를 추가한다.
     * @param itemList 새로운 아이템 리스트
     */
    public void addItemList(ArrayList<ReviewItem> itemList){
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    /**
     * 좋아요 상태를 변경한다.
     * @param seq 리뷰 시퀀스
     * @param keep 좋아요 유무 여부
     */
    private void changeItemKeep(int seq, boolean keep) {
        for(int i=0; i<itemList.size(); i++){
            if(itemList.get(i).seq == seq){
                itemList.get(i).isKeep = keep;
                notifyItemChanged(i);
                break;
            }
        }
    }

    /**
     * 뷰홀더(ViewHolder)를 생성하기 위해 자동으로 호출된다.
     * @param parent 부모 뷰그룹
     * @param viewType 새로운 뷰의 뷰타입
     * @return 뷰홀더 객체
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ViewHolder(v);
    }

    /**
     * 뷰홀더(ViewHolder)와 아이템을 리스트 위치에 따라 연동한다.
     * @param holder 뷰홀더 객체
     * @param position 리스트 위치
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ReviewItem item = itemList.get(position);
        Log.d(TAG, "getView " + item);

        if(item.isKeep) {
            //holder.review_img_like.setImageResource(R.drawable.ic_favorite_on_black_24dp);
        }else{
            //holder.review_img_like.setImageResource(R.drawable.ic_favorite_off_black_24dp);
        }
    }

    /**
     * 아이템 크기를 반환한다
     * @return 아이템 크기
     */
    public int getItemCount(){
        return this.itemList.size();
    }


    /**
     * 아이템을 보여주기 위한 뷰홀더 클래스
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
