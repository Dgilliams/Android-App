package com.theygiveafck.theygiveafck;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.lang.reflect.Field;

public class Item {
    private String title, desc, img;

    public Item(String title, String desc, String img) {
        this.title = title;
        this.desc = desc;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getDesc() {
        return desc;
    }

//    public void setDesc(String desc) {
//        this.desc = desc;
//    }

    public int getImg() {
        Log.d("onDataChange", " " + getResId(img, Drawable.class));
        return getResId(img, Drawable.class);
    }

//    public void setImg(String img) {
//        this.img = img;
//    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

