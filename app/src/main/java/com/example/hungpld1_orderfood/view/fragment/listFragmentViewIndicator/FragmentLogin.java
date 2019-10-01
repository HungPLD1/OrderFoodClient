package com.example.hungpld1_orderfood.view.fragment.listFragmentViewIndicator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hungpld1_orderfood.R;
import com.example.hungpld1_orderfood.viewmodel.ViewmodelLogin;

public class FragmentLogin extends Fragment implements View.OnClickListener {
    private EditText mEdtEmail,mEdtPassword;
    private Button mBtnLogin;
    private TextView mTxtForgotPassword;
    private ViewmodelLogin mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        mBtnLogin.setOnClickListener(this);
        liveDataListener();
    }

    private void liveDataListener() {
        if (mBtnLogin.isClickable()) {
            mViewModel.getIsLoginSuccess().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {

                    if (aBoolean != null) {
                        if (aBoolean) {
                            showDialogSuccess();
                        } else {
                            showDialogFalse();
                        }
                    }
                }
            });
        }
    }

    private void initView(View view) {
        mViewModel = ViewModelProviders.of(this).get(ViewmodelLogin.class);

        mEdtEmail = view.findViewById(R.id.edt_username);
        mEdtPassword = view.findViewById(R.id.edt_password);
        mBtnLogin = view.findViewById(R.id.btn_go);
        mTxtForgotPassword = view.findViewById(R.id.txt_forgot);
    }

    @Override
    public void onClick(View view) {
        if (view != null){
            switch (view.getId()){
                case R.id.btn_go:
                    handleLogin();
                    break;
            }
        }
    }

    private void handleLogin() {
        if (mEdtEmail.length() > 6 && mEdtPassword.length() >8 ){
            mViewModel.handleLogin(mEdtEmail.getText().toString().trim()
                    ,mEdtPassword.getText().toString().trim());
        }else {
            showDialogFalse();
        }
    }

    private void showDialogSuccess(){
        Toast.makeText(getContext(), "Login Success !!!", Toast.LENGTH_SHORT).show();
    }

    private void showDialogFalse(){
        Toast.makeText(getContext(), "Login False", Toast.LENGTH_SHORT).show();
    }
}
