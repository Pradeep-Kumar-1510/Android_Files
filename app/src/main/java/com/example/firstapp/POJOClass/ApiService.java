package com.example.firstapp.POJOClass;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("jokes/random")
    Call<DataClass> getJoke();
}