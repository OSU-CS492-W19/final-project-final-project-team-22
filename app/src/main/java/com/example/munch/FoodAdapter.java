package com.example.munch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.munch.data.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    private List<Food> mFoods;

    public FoodAdapter(){

    }

    public void updateFood(List<Food> food){
        mFoods = food;
        notifyDataSetChanged();
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

    class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mFoodNameTV;

        FoodViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mFoodNameTV = itemView.findViewById(R.id.tv_food_title);
        }

        public void bind(Food f){
            String name = f.title;
            mFoodNameTV.setText(name);
        }

        @Override
        public void onClick(View view){
            //Something happens
        }
    }


}
