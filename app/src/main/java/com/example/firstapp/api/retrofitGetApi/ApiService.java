package com.example.firstapp.api.retrofitGetApi;
import com.example.firstapp.api.retrofitGetApi.DataClass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("jokes/random")
    Call<DataClass> getJoke();
}