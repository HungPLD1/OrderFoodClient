package com.example.hungpld1_orderfood.view.fragment.listFragmentViewIndicator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hungpld1_orderfood.R;
import com.example.hungpld1_orderfood.model.respository.Reponsitory;
import com.example.hungpld1_orderfood.viewmodel.ViewmodelLogin;

public class FragmentRegister extends Fragment implements View.OnClickListener {
    private EditText mEdtRegisUserName,mEdtRegisPassword,mEdtRegisRepeatPassWord;
    private Button mBtnRegister;
    private ViewmodelLogin mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        mBtnRegister.setOnClickListener(this);
        liveDataListener();
        handleComparedPassword();
    }

    private void liveDataListener() {
    }

    private void handleComparedPassword() {
        if (mBtnRegister.isClickable()) {
            mViewModel.getIsRegisterSuccess().observe(this, new Observer<Boolean>() {
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
        mEdtRegisUserName = view.findViewById(R.id.edt_regis_email);
        mEdtRegisPassword = view.findViewById(R.id.edt_regis_password);
        mEdtRegisRepeatPassWord = view.findViewById(R.id.edt_regis_repeatpassword);
        mBtnRegister =view.findViewById(R.id.btn_register);
    }


    @Override
    public void onClick(View view) {
        if (view != null){
            switch (view.getId()){
                case R.id.btn_register:
                    handleRegister();
                    break;
            }
        }
    }

    private void handleRegister() {
        if (mEdtRegisUserName.getText().length() > 5 && mEdtRegisPassword.getText().length() > 6 &&
                mEdtRegisRepeatPassWord.getText().length() == mEdtRegisPassword.getText().length()){
            mViewModel.handleRegister(mEdtRegisUserName.getText().toString().trim()
                    ,mEdtRegisPassword.getText().toString().trim());
        }else {
            Toast.makeText(getContext(), "Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDialogSuccess(){
        Toast.makeText(getContext(), "Register Success !!!", Toast.LENGTH_SHORT).show();
    }

    private void showDialogFalse(){
        Toast.makeText(getContext(), "Register False", Toast.LENGTH_SHORT).show();
    }
}
