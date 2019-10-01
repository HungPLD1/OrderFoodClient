package com.example.hungpld1_orderfood.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hungpld1_orderfood.model.object.Account;
import com.example.hungpld1_orderfood.model.respository.Reponsitory;

public class ViewmodelLogin extends AndroidViewModel  {
    private MutableLiveData<Account> mAccount ;
    private MutableLiveData<Boolean> isLoginSuccess;
    private MutableLiveData<Boolean> isRegisterSuccess;
    private Reponsitory mReponsitory;

    public ViewmodelLogin(@NonNull Application application) {
        super(application);
        mAccount = new MutableLiveData<>();
        isLoginSuccess = new MutableLiveData<>();
        isRegisterSuccess = new MutableLiveData<>();
        mReponsitory = Reponsitory.getInstance();
    }


    public void handleLogin(String email, String password) {
        Reponsitory.getInstance().loginAccount(email,password);
    }

    public void handleRegister(String email, String password) {
        Reponsitory.getInstance().registerAccount(email,password);
    }

    public MutableLiveData<Account> getmAccount() {
        return mAccount;
    }

    public MutableLiveData<Boolean> getIsLoginSuccess() {
        return isLoginSuccess;
    }

    public MutableLiveData<Boolean> getIsRegisterSuccess() {
        return isRegisterSuccess;
    }

}
