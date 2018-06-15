package com.example.manito15.mytype.item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LG on 2018-06-10.
 */

public class ImageDTO {
    public String imageUrl;
    public String edt_review_write;
    public String edt_price;
    public String uid;
    public String userId;

    public String btn_male;
    public String btn_female;
    public String btn_family;
    public String btn_parents;
    public String btn_grandparents;
    public String btn_friends;
    public String btn_lover;
    public String btn_coworker;
    public String btn_teacher;

    public String btn_teenager;
    public String btn_twenty;
    public String btn_thirty;
    public String btn_forty;
    public String btn_fifty;
    public String btn_sixty;
    public String btn_seventy;
    public String btn_eighty;

    public String btn_early;
    public String btn_mid;
    public String btn_late;

    public String seekBar;

    public String onlineURL;

    //좋아요 버튼
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

}
