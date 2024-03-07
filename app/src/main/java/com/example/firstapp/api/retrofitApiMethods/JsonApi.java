package com.example.firstapp.api.retrofitApiMethods;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonApi {

    @GET("posts")
    Call<List<JsonDataClass>> getPosts();

    @POST("posts")
    Call<JsonDataClass> createPost(@Body JsonDataClass post);

    @FormUrlEncoded
    @POST("posts")
    Call<JsonDataClass> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @PUT("posts/{id}")
    Call<JsonDataClass> putPost(@Path("id") int id, @Body JsonDataClass post);

    @PATCH("posts/{id}")
    Call<JsonDataClass> patchPost(@Path("id") int id, @Body JsonDataClass post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
