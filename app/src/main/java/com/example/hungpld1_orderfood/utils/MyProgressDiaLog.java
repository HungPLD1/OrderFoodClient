package com.example.hungpld1_orderfood.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.hungpld1_orderfood.R;

public class MyProgressDiaLog {
    public static MyProgressDiaLog mInstance;
    public static ProgressDialog mDialog;
    public static Context context;
    public static MyProgressDiaLog getInstance(Context context){
       synchronized (MyProgressDiaLog.class) {
           if (mInstance == null){
               mInstance = new MyProgressDiaLog(context);
               mDialog = new ProgressDialog(context);
           }
           return mInstance;
       }
    };

    private MyProgressDiaLog(Context context){}

    public void showProgressDialog(){
        mDialog.setMessage(context.getResources().getString(R.string.loading_login));
        mDialog.show();
    }

    public void closeProgressDialog(){
        mDialog.cancel();
    }

}
