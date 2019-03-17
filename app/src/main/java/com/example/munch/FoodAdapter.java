package com.example.munch;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.munch.data.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    private List<Food> mFoods;

    OnFoodItemClickListener mFoodItemClickListener;

    public interface OnFoodItemClickListener {
        void onFoodItemClick(Food f);
    }


    public FoodAdapter(OnFoodItemClickListener l){
        mFoodItemClickListener = l;
    }

    public void updateFood(List<Food> food){
        mFoods = food;
        notifyDataSetChanged();
    }

    public void goToNextFood(int position){
        notify();
        //mFoods.remove(position);
        //getListView().smoothScrollToPosition(21);
        //notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(mFoods != null){
            return mFoods.size();
        }
        else{
            return 0;
        }
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.food_card, viewGroup, false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foods, int i){
        foods.bind(mFoods.get(i));
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{
        private TextView mFoodNameTV;
        private ImageView mFoodImageIV;

        FoodViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Food f = mFoods.get(getAdapterPosition());
                    mFoodItemClickListener.onFoodItemClick(f);
                }
            });

            mFoodNameTV = itemView.findViewById(R.id.tv_food_title);
            mFoodImageIV = itemView.findViewById(R.id.iv_food_photo);
        }

        public void bind(Food f){
            String name = f.title;
            mFoodNameTV.setText(name);

            Glide.with(mFoodImageIV)
                    .load(f.image)
                    .into(mFoodImageIV);
        }

    }


}
