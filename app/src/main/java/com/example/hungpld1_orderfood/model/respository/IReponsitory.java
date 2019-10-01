package com.example.hungpld1_orderfood.model.respository;

import com.example.hungpld1_orderfood.model.object.Account;
import com.example.hungpld1_orderfood.model.object.Food;

import java.util.ArrayList;

public interface IReponsitory {
   void loginAccount(String email,String password);
   void registerAccount(String email,String password);
   ArrayList<Food> getListFood();
}
