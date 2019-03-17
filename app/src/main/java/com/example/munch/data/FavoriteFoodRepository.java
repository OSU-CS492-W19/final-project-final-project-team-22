package com.example.munch.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FavoriteFoodRepository {
    private FavoriteFoodDao mFavoriteFoodDao;

    public FavoriteFoodRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mFavoriteFoodDao = db.favoriteFoodDao();
    }

    public void insertFavoriteFood(FavoriteFood favoriteFood) {
        new InsertFavoriteFoodAsyncTask(mFavoriteFoodDao).execute(favoriteFood);
    }

    public void deleteFavoriteFood(FavoriteFood favoriteFood) {
        new DeleteFavoriteFoodAsyncTask(mFavoriteFoodDao).execute(favoriteFood);
    }

    public LiveData<List<FavoriteFood>> getAllFavoriteFoods() {
        return mFavoriteFoodDao.getAllFavoriteFoods();
    }

    private static class InsertFavoriteFoodAsyncTask extends AsyncTask<FavoriteFood, Void, Void> {
        private FavoriteFoodDao mAsyncTaskDao;

        InsertFavoriteFoodAsyncTask(FavoriteFoodDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(FavoriteFood... favoriteFoods) {
            mAsyncTaskDao.insert(favoriteFoods[0]);
            return null;
        }
    }

    private static class DeleteFavoriteFoodAsyncTask extends AsyncTask<FavoriteFood, Void, Void> {
        private FavoriteFoodDao mAsyncTaskDao;

        DeleteFavoriteFoodAsyncTask(FavoriteFoodDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(FavoriteFood... favoriteFoods) {
            mAsyncTaskDao.delete(favoriteFoods[0]);
            return null;
        }
    }
}
