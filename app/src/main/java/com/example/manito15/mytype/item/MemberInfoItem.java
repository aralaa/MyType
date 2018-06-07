package com.example.manito15.mytype.item;

import org.w3c.dom.Comment;

import java.util.Map;

/**
 * 사용자 정보를 저장하는 객체
 */
public class MemberInfoItem {

    public int memberId; //index

    public String email;
    public String pw;
    public String name;
    public String birth;
    public String gender;
    public String profileImageFilename;
    public Map<String, Comment> commentMap;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImageFilename() {
        return profileImageFilename;
    }

    public void setProfileImageFilename(String profileImageFilename) {
        this.profileImageFilename = profileImageFilename;
    }

    public Map<String, Comment> getCommentMap() {
        return commentMap;
    }

    public void setCommentMap(Map<String, Comment> commentMap) {
        this.commentMap = commentMap;
    }
}
