package com.example.paul.q;

import android.widget.ImageView;

/**
 * Created by PAUL on 09-05-2018.
 */

public class ModelClass
{
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public ModelClass(String imageUrl,String creator, int likes){
        mImageUrl=imageUrl;
        mCreator=creator;
        mLikes=likes;
    }
    public String getmImageUrl(){
        return mImageUrl;
    }
    public String getmCreator(){
        return mCreator;
    }

    public int getmLikes(){
        return mLikes;

    }

}
