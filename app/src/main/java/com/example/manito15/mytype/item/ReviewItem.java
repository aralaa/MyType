package com.example.manito15.mytype.item;

//@SerializedName : JSON의 키 이름과 다른 경우에 매칭해주는 기능
//GSON 라이브러리에서 제공하는 어노테이션 p.277

import java.io.Serializable;

/**
 * 리뷰를 저장하는 객체
 */
public class ReviewItem implements Serializable {

    public int reviewId;  // index
    public int memberId; // 글쓴이

    public String receverRelation;
    public String receverGender;
    public String receiverAge;
    public String receiverAgeRage;
    public String receiverSatisfaction;
    public String giftPrice;
    public String buyplace;
    public String onlineURL;
    public String offlineAddress;
    public double latitude;
    public double longitude;
    public String reviewText;
    public long regDate;
    public boolean isLike;
    public String reviewImageFilename;
    public String hashtag;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getReceverRelation() {
        return receverRelation;
    }

    public void setReceverRelation(String receverRelation) {
        this.receverRelation = receverRelation;
    }

    public String getReceverGender() {
        return receverGender;
    }

    public void setReceverGender(String receverGender) {
        this.receverGender = receverGender;
    }

    public String getReceiverAge() {
        return receiverAge;
    }

    public void setReceiverAge(String receiverAge) {
        this.receiverAge = receiverAge;
    }

    public String getReceiverAgeRage() {
        return receiverAgeRage;
    }

    public void setReceiverAgeRage(String receiverAgeRage) {
        this.receiverAgeRage = receiverAgeRage;
    }

    public String getReceiverSatisfaction() {
        return receiverSatisfaction;
    }

    public void setReceiverSatisfaction(String receiverSatisfaction) {
        this.receiverSatisfaction = receiverSatisfaction;
    }

    public String getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(String giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getBuyplace() {
        return buyplace;
    }

    public void setBuyplace(String buyplace) {
        this.buyplace = buyplace;
    }

    public String getOnlineURL() {
        return onlineURL;
    }

    public void setOnlineURL(String onlineURL) {
        this.onlineURL = onlineURL;
    }

    public String getOfflineAddress() {
        return offlineAddress;
    }

    public void setOfflineAddress(String offlineAddress) {
        this.offlineAddress = offlineAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public long getRegDate() {
        return regDate;
    }

    public void setRegDate(long regDate) {
        this.regDate = regDate;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getReviewImageFilename() {
        return reviewImageFilename;
    }

    public void setReviewImageFilename(String reviewImageFilename) {
        this.reviewImageFilename = reviewImageFilename;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
