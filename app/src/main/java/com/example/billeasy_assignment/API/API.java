package com.example.billeasy_assignment.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("now_playing")
    Call<String>  getMovies(@Query("api_key")String api_key);
}
