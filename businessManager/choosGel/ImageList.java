package com.example.thefinalproject.businessManager.choosGel;

import android.widget.ImageView;

import java.util.ArrayList;

public class ImageList {

    public static ArrayList<ImageView> images = new ArrayList<>();

    public static void addImageView (ImageView imageView){
        images.add(imageView);
    }

    public static ArrayList<ImageView> getImageViewList (){
        return images;
    }

}
