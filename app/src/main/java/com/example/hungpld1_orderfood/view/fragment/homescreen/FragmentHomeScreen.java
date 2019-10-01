package com.example.hungpld1_orderfood.view.fragment.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungpld1_orderfood.R;
import com.example.hungpld1_orderfood.model.object.Food;
import com.example.hungpld1_orderfood.model.respository.Reponsitory;
import com.example.hungpld1_orderfood.view.adapter.adapterFood.AdapterHomeScreenFood;
import com.example.hungpld1_orderfood.view.adapter.adapterFood.IOnClickFoodItem;

import java.util.ArrayList;

public class FragmentHomeScreen extends Fragment implements IOnClickFoodItem {
    private RecyclerView mRcvFood;
    private AdapterHomeScreenFood mAdapterHomeScreenFood;
    private ArrayList<Food> mListFood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homescreen,container,false);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        displayListFood();
    }

    private void displayListFood() {

        mListFood = Reponsitory.getInstance().getListFood();
        mAdapterHomeScreenFood = new AdapterHomeScreenFood(getActivity()
                , mListFood,this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),2);
        mRcvFood.setLayoutManager(manager);
        mRcvFood.setAdapter(mAdapterHomeScreenFood);
        Toast.makeText(getContext(), "" +  mListFood.size(), Toast.LENGTH_SHORT).show();
    }

    private void initView(View view) {
        mRcvFood = view.findViewById(R.id.rcvFood);
    }

    @Override
    public void onLongClick(int position) {

    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getContext(), mListFood.size() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickItemSelected(MenuItem menuItem, int position) {

    }
}
