package com.example.hungpld1_orderfood.model.object;

import java.io.Serializable;

public class Account implements Serializable {
    private String mID;
    private String mEmail;
    private String mPassword;
    private User mUser;

    public Account(String mID, String mEmail, String mPassword, User mUser) {
        this.mID = mID;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mUser = mUser;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
