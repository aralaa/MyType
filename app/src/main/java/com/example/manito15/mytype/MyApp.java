package com.example.manito15.mytype;

import android.app.Application;
import android.os.StrictMode;

import com.example.manito15.mytype.item.MemberInfoItem;
import com.example.manito15.mytype.item.ReviewItem;

/**
 * 앱 전역에서 사용 할 수 있는 클래스
 * Application 클래스는 애플리케이션이 시작할 때 가장 먼저 초기회되며 Manifest의 Application 태그에 name 속성에 MyApp 지정해줘야함
 */
public class MyApp extends Application{
    private MemberInfoItem memberInfoItem;
    private ReviewItem reviewItem;

    @Override
    public void onCreate() {
        super.onCreate();

        // FileUriExposedException 문제를 해결하기 위한 코드
        // 관련 설명은 책의 [부록]355페이지 참고
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public MemberInfoItem getMemberInfoItem() {
        if(memberInfoItem == null) memberInfoItem = new MemberInfoItem();

        return memberInfoItem;
    }

    public void setMemberInfoItem(MemberInfoItem item) {
        this.memberInfoItem = item;
    }

    public int getMemberId(){
        return memberInfoItem.memberId;
    }

    public ReviewItem getReviewItem() {
        return reviewItem;
    }

    public void setReviewItem(ReviewItem reviewItem) {
        this.reviewItem = reviewItem;
    }
}
