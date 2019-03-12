package com.example.munch.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.munch.utils.NetworkUtils;
import com.example.munch.utils.SpoonacularUtils;

import java.io.IOException;
import java.util.List;

import javax.security.auth.callback.Callback;

public class FoodAsyncTask extends AsyncTask<Void, Void, String> {
    private String mURL;
    private Callback mCallback;

    public interface Callback{
        void onLoadFinished(List<Food> foods);
    }

    public FoodAsyncTask(String url, Callback callback){
        mURL = url;
        mCallback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (mURL != null) {
            String results = null;
            try {
                results = NetworkUtils.doHTTPGet(mURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s){
        List<Food> foods = null;
        Log.d(FoodAsyncTask.class.getSimpleName(), "in onPostExecute, s is: " + s);
        if(s != null){
            foods = SpoonacularUtils.parseSpoonacularResultsJSON(s);
        }
        mCallback.onLoadFinished(foods);
    }



}
