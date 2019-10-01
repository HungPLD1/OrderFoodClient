package com.example.hungpld1_orderfood.model.respository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.hungpld1_orderfood.model.object.Food;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Reponsitory  implements IReponsitory {
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFireBase;
    private FirebaseAuth mAut ;
    public static Reponsitory mInstance;
    private final String CHILD_FOOD = "Food";

    private static final String TAG = "ReponsitoryHungPLD1";

    public static Reponsitory getInstance(){
        synchronized (Reponsitory.class) {
            if (mInstance == null) {
                mInstance = new Reponsitory();
            }
            return mInstance;
        }
    }



    private Reponsitory() {
    }

    @Override
    public void loginAccount(String email, String password) {
        mAut = FirebaseAuth.getInstance();
        mAut.signInWithEmailAndPassword(email,password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                }else {

                }
            }
        });

    }

    @Override
    public void registerAccount(String email, String password){
            mAut = FirebaseAuth.getInstance();
            mAut.createUserWithEmailAndPassword(email,password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                    }else {

                    }
                }
            });
    }

    @Override
    public ArrayList<Food> getListFood() {
        mFireBase = FirebaseDatabase.getInstance();
        mDatabase = mFireBase.getReference(CHILD_FOOD);
        final ArrayList<Food> mListFood = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Food food = snapshot.getValue(Food.class);
                    mListFood.add(food);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d(TAG, "getListFood: " + mListFood.size());
        return mListFood;
    }


}
