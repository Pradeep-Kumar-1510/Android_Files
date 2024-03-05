package com.example.firstapp.POJOClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostApiService {

    @POST("users")
    Call<PostDataClass> createPost(@Body PostDataClass postDataClass);
}
