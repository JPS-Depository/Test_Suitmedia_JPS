package com.jps.test_suitmedia_jps;

public class GuestItem {
    private int mId;
    private String mImageUrl;
    private String mFirst;
    private String mLast;
    private String mEmail;

    public GuestItem(int id, String email, String first, String last, String imageUrl)
    {
        mId = id;
        mEmail = email;
        mFirst = first;
        mLast = last;
        mImageUrl = imageUrl;
    }

    public int getId(){
        return mId;
    }
    public String getEmail(){
        return mEmail;
    }
    public String getFirst(){
        return mFirst;
    }
    public String getLast(){
        return mLast;
    }
    public String getImageUrl(){
        return mImageUrl;
    }
}
