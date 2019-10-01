package com.example.hungpld1_orderfood.view.fragment.listFragmentViewIndicator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.hungpld1_orderfood.view.activity.MainActivity;
import com.example.hungpld1_orderfood.view.adapter.adapterViewPagerLogin.AdapterViewPagerLogin;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.hungpld1_orderfood.R.*;

public class FragmentIndicator_End extends Fragment implements View.OnClickListener {
    private LoginButton mBtnFaceBook;
    private SignInButton mBtnGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "FragmentIndicator_LoginHungPLD1";
    private ProgressDialog mDialog =new ProgressDialog(getContext());
    private CallbackManager mCallbackManager;
    private CircleImageView mImgCheat;
    private ViewPager mViewPagerLoginAndRegister;
    private AdapterViewPagerLogin mAdapterViewPagerLogin;
    private TabLayout mTabLayout;
    final int RC_SIGN_IN = 111;



    public static final String ACCOUNT_GOOGLE = "google";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(layout.fragment_indicator_3, container, false);

        initView(mView);
        initEvent();
        return mView;
    }

    private void initEvent() {
        mBtnGoogle.setOnClickListener(this);
        mBtnFaceBook.setOnClickListener(this);
        mImgCheat.setOnClickListener(this);
        setupFormLoginAndRegister();
    }

    private void setupFormLoginAndRegister() {
        mAdapterViewPagerLogin = new AdapterViewPagerLogin(getChildFragmentManager());
        mViewPagerLoginAndRegister.setAdapter(mAdapterViewPagerLogin);
        mAdapterViewPagerLogin.notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPagerLoginAndRegister);
    }

    private void initView(View mView) {
        mImgCheat = mView.findViewById(id.imgCheat);

        mBtnFaceBook = mView.findViewById(id.btnLoginFacebook);
        mBtnFaceBook.setFragment(this);
        mBtnGoogle = mView.findViewById(id.btnLoginGoogle);
        mCallbackManager = CallbackManager.Factory.create();
        mViewPagerLoginAndRegister = mView.findViewById(id.viewPager_login);
        mTabLayout = mView.findViewById(id.tabLayout);

    }


    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case id.btnLoginFacebook:
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                    handleLoginFacebook();
                    break;
                case id.btnLoginGoogle:
                    showProgressDialog();
                    handleLoginGoogle();
                    break;
                case id.imgCheat:
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                    break;
            }
        }
    }

    //handle click button login with google
    private void handleLoginGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, RC_SIGN_IN   );
    }

    //handle click button login with facebook
    private void handleLoginFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "Can't to login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), ""+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //show progressdialog
    private void showProgressDialog() {
        mDialog.setMessage(getResources().getString(string.loading_login));
        mDialog.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RC_SIGN_IN:
                    try {
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        if (task != null){
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                        }else {
                            //handle
                            Toast.makeText(getContext(), "false", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
        } else {
            mDialog.cancel();
        }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(ACCOUNT_GOOGLE, googleSignInAccount);
        Toast.makeText(getContext(), "Success !!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
