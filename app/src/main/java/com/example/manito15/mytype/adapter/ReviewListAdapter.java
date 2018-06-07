package com.example.manito15.mytype.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manito15.mytype.MyApp;
import com.example.manito15.mytype.R;
import com.example.manito15.mytype.item.MemberInfoItem;
import com.example.manito15.mytype.item.ReviewItem;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 리뷰 리스트의 아이템을 처리하는 어댑터
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {

    // 파이어베이스 주소 : public static final String FIREBASE_POST_URL = "https://jacaseminar0613.firebaseio.com/Posts";
    private final String TAG = this.getClass(). getSimpleName();
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

        // 사용자 정보 얻어오기
        memberInfoItem = ((MyApp) context.getApplicationContext()).getMemberInfoItem();
    }



    /**
     * 특정 아이템의 변경사항을 적용하기 위해 기본 아이템을 새로운 아이템으로 변경한다.
     * @param newItem
     */
    public void setItem(ReviewItem newItem) {
        for(int i=0; i<itemList.size(); i++){
            ReviewItem item = itemList.get(i);

            if(item.reviewId == newItem.reviewId){
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
            if(itemList.get(i).reviewId == seq){
                itemList.get(i).isLike = keep;
                notifyItemChanged(i);
                break;
            }
        }
    }

    /**
     * 뷰홀더(ViewHolder)를 생성하기 위해 자동으로 호출
     * 항목을 구성하기 위한 Layout xml 파일 inflate
     * view 를 findViewById 할 viewHolder 를 리턴
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
     * 뷰홀더(ViewHolder)와 아이템을 리스트 위치에 따라 연동
     * @param holder 뷰홀더 객체
     * @param position 리스트 위치
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ReviewItem item = itemList.get(position);
        Log.d(TAG, "getView " + item);

        // 리뷰 이미지 연결
        Glide.with(context).load(item.getReviewImageFilename()).centerCrop().into(holder.imgReview);
        //좋아요
        if(item.isLike) {
            holder.imgLike.setImageResource(R.drawable.ic_favorite_on_black_24dp);
        } else {
            holder.imgLike.setImageResource(R.drawable.ic_favorite_off_black_24dp);
        }

        //글쓴이 정보 가져오기
        holder.name.setText(memberInfoItem.getName());
        // ************** birthday에서 연령대 구하는 방법
        holder.age.setText(memberInfoItem.getBirth());
        //글쓴이 프로필 사진
        Glide.with(context).load(memberInfoItem.getProfileImageFilename()).centerCrop().into(holder.imgProfie);
        //등록일
        holder.regDate.setText(getDiffTimeText(item.getRegDate()));
    }

    /**
     * 아이템 크기를 반환한다
     * @return 아이템 크기
     */
    public int getItemCount(){
        return this.itemList.size();
    }

    /**
     * 뷰홀더 클래스
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgProfie;
        public TextView name;
        public TextView age;
        public TextView ageRage;
        public TextView regDate;
        public ImageView imgLike;
        public ImageView imgReview;

        public ViewHolder(View itemView) {
            super(itemView);

            imgProfie = (ImageView) itemView.findViewById(R.id.img_profie);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            age = (TextView) itemView.findViewById(R.id.txt_age);
            ageRage = (TextView) itemView.findViewById(R.id.txt_age_range);
            regDate = (TextView) itemView.findViewById(R.id.txt_regdate);
            imgLike = (ImageView) itemView.findViewById(R.id.img_like);
            imgReview = (ImageView) itemView.findViewById(R.id.img_review);
        }
    }

    public String getDiffTimeText(long targetTime) {
        DateTime curDateTime = new DateTime();
        DateTime targetDateTime = new DateTime().withMillis(targetTime);

        int diffDay = Days.daysBetween(curDateTime, targetDateTime).getDays();
        int diffHours = Hours.hoursBetween(targetDateTime, curDateTime).getHours();
        int diffMinutes = Minutes.minutesBetween(targetDateTime, curDateTime).getMinutes();
        if (diffDay == 0) {
            if(diffHours == 0 && diffMinutes == 0){
                return "방금전";
            }
            if(diffHours > 0){
                return "" + diffHours + "시간 전";
            }
            return "" + diffMinutes + "분 전";

        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.format(new Date(targetTime));
        }
    }
}

