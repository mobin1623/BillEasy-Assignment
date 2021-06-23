package com.example.billeasy_assignment;

import android.util.Log;

import com.example.billeasy_assignment.API.API;
import com.example.billeasy_assignment.API.ApiClient;
import com.example.billeasy_assignment.DB.MovieDatabase;
import com.example.billeasy_assignment.DB.Movies;
import com.example.billeasy_assignment.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MovieListModel implements MovieContract.Model {
    private API api;
    private Call<String> call;
    private List<Movies> moviesList = new ArrayList<>();
    private Movies movies;

    @Override
    public void getMovies(OnFinishListerner listerner) {



        api = ApiClient.getApiClient().create(API.class);
        call = api.getMovies(Constants.API_KEY);
       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {

               if (response.isSuccessful() && response.body() != null){
                   Log.d("TAG","MOvies : " + response.body());
                   try{
                       JSONObject jsonObject = new JSONObject(response.body());
                       JSONArray jsonArray = jsonObject.getJSONArray("results");
                       for (int i = 0; i < jsonArray.length(); i++){
                           JSONObject results = jsonArray.getJSONObject(i);
                           movies = new Movies();
                           movies.setTitle(results.getString("title"));
                           movies.setPosterPath(results.getString("poster_path"));
                           moviesList.add(movies);
                           listerner.onFinish(movies);


                       }
                       listerner.onFinish(moviesList);


                   }catch (Exception e){
                       listerner.onFailure(e);
                       Log.d("TAG","JSON error : " + e.getMessage());
                   }
               }
           }

           @Override
           public void onFailure(Call<String> call, Throwable t) {
               listerner.onFailure(t);

           }
       });


    }
}
