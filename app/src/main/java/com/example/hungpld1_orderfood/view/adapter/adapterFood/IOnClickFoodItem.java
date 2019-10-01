package com.example.hungpld1_orderfood.view.adapter.adapterFood;

import android.view.MenuItem;

public interface IOnClickFoodItem {
    void onLongClick(int position);
    void onClick(int position);
    void onClickItemSelected(MenuItem menuItem, int position);
}
