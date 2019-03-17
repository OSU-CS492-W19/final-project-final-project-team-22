package com.example.munch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.munch.data.FavoriteFood;

import java.util.List;

public class FavoriteFoodAdapter extends RecyclerView.Adapter<FavoriteFoodAdapter.FavoriteFoodItemViewHolder> {

    private List<FavoriteFood> mFavoriteFoods;
    private OnFavoriteFoodItemClickListener mFavoriteFoodItemClickListener;

    public interface OnFavoriteFoodItemClickListener {
        void onFavoriteFoodItemClick(FavoriteFood favoriteFood);
    }

    public FavoriteFoodAdapter(OnFavoriteFoodItemClickListener clickListener) {
        mFavoriteFoodItemClickListener = clickListener;
    }

    public void updateFavoriteFoods(List<FavoriteFood> favoriteFoods) {
        mFavoriteFoods = favoriteFoods;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mFavoriteFoods != null)
            return mFavoriteFoods.size();

        return 0;
    }

    @Override
    public FavoriteFoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.favorite_food_list_item, parent, false);
        return new FavoriteFoodItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteFoodItemViewHolder holder, int position) {
        holder.bind(mFavoriteFoods.get(position));
    }

    class FavoriteFoodItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mFavoriteFoodTV;

        public FavoriteFoodItemViewHolder(View itemView) {
            super(itemView);
            mFavoriteFoodTV = itemView.findViewById(R.id.tv_favorite_food_item);
            itemView.setOnClickListener(this);
        }

        public void bind(FavoriteFood favoriteFood) {
            mFavoriteFoodTV.setText(favoriteFood.title);
        }

        @Override
        public void onClick(View v) {
            FavoriteFood favoriteFood = mFavoriteFoods.get(getAdapterPosition());
            mFavoriteFoodItemClickListener.onFavoriteFoodItemClick(favoriteFood);
        }
    }
}
