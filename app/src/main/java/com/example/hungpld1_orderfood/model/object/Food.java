package com.example.hungpld1_orderfood.model.object;

import java.io.Serializable;

public class Food implements Serializable {
    private String mID;
    private String mName;
    private String mDescription;
    private int mPrice;
    private String mImage;
    private int mAmount;

    public Food() {
    }


    public Food(String mID, String mName, String mDescription, int mPrice, String mImage, int mAmount) {
        this.mID = mID;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mPrice = mPrice;
        this.mImage = mImage;
        this.mAmount = mAmount;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public int getmAmount() {
        return mAmount;
    }

    public void setmAmount(int mAmount) {
        this.mAmount = mAmount;
    }
}
