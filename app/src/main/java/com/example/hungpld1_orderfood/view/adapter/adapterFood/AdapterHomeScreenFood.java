package com.example.hungpld1_orderfood.view.adapter.adapterFood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungpld1_orderfood.R;
import com.example.hungpld1_orderfood.model.object.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterHomeScreenFood extends RecyclerView.Adapter<AdapterHomeScreenFood.ViewHolder> {
    private Context mContext;
    private ArrayList<Food> mListFood;
    private IOnClickFoodItem mCallBack;

    public AdapterHomeScreenFood(Context mContext, ArrayList<Food> mListFood, IOnClickFoodItem mCallBack) {
        this.mContext = mContext;
        this.mListFood = mListFood;
        this.mCallBack = mCallBack;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_food_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.with(mContext).load(mListFood.get(position).getmImage())
                .placeholder(R.drawable.loading).error(R.drawable.default_image).into(holder.mImgFood);
        holder.mTxtNameFood.setText(mListFood.get(position).getmName());
        holder.mTxtPriceFood.setText(mListFood.get(position).getmPrice() + " đ");
        holder.mTxtIndexFood.setText("Kho còn: " + mListFood.get(position).getmAmount());


        if (mCallBack != null) {
            /*Handle on long click to item in recycleview list food*/
            holder.mItemFood.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mCallBack.onLongClick(position);
                    return false;
                }
            });
            /*Handle on click to item in recycleview list food*/
            holder.mItemFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.onClick(position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mListFood == null ? 0 : mListFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgFood;
        TextView mTxtNameFood, mTxtPriceFood, mTxtIndexFood;
        LinearLayout mItemFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgFood = itemView.findViewById(R.id.imgFood);
            mTxtNameFood = itemView.findViewById(R.id.txtNameFood);
            mTxtPriceFood = itemView.findViewById(R.id.txtPrice);
            mTxtIndexFood = itemView.findViewById(R.id.txtIndex);
            mItemFood = itemView.findViewById(R.id.itemFood);
        }
    }
}
