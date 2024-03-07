package com.example.firstapp.api.retrofitApiMethods;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMethodsFragment extends Fragment {

    /***
     * Api used is JsonPlaceHolder API.
     * In this fragment, I have implemented all the api methods(GET,PUT,POST,PATCH,DELETE), but only one method gets called at a time.
     * For example, if you want to implement Get method, uncomment the getPosts() while commenting other methods. This
     * retrieves the data from the Json Placeholder Api.
     * Similarly, if you want to perform POST method uncomment the createPost() method while commenting other methods.
     * For PUT or PATCH method uncomment the updatePosts() while commenting others.
     * For DELETE , uncomment the deletePost() while commenting others.
     * */

    private TextView textView;
    private JsonApi jsonApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_api_methods, container, false);

        textView = view.findViewById(R.id.textResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonApi = retrofit.create(JsonApi.class);
        // getPosts();
         updatePosts();
        // createPost();
       // deletePost();
        return view;

    }

    public void getPosts() {


        Call<List<JsonDataClass>> call = jsonApi.getPosts();

        call.enqueue(new Callback<List<JsonDataClass>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<JsonDataClass>> call, @NonNull Response<List<JsonDataClass>> response) {
                if (!response.isSuccessful()) {
                    textView.setText(getString(R.string.code) + response.code());
                    return;
                }
                List<JsonDataClass> posts = response.body();
                assert posts != null;
                for (JsonDataClass post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserid() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Body: " + post.getText() + "\n\n";

                    textView.append(content);
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<JsonDataClass>> call, @NonNull Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }

    /***
     *  In updatePosts, If you are going to implement PATCH , ensure you call patchPost instead of putPost (ie)
     *  Call<JsonDataClass> call = jsonApi.patchPost(5, json) should be used instead of Call<JsonDataClass> call = jsonApi.putPost(5, json);
     * Here 5, represents the id , you can open <a href="https://jsonplaceholder.typicode.com/posts">.Json PlaceHolder Api..</a>
     * and check with the corresponding id value to know if it is getting updated or not.
     * ***/
    private void updatePosts() {
        JsonDataClass json = new JsonDataClass(23, null, "New ");

        Call<JsonDataClass> call = jsonApi.putPost(5, json);

        call.enqueue(new Callback<JsonDataClass>() {
            @Override
            public void onResponse(@NonNull Call<JsonDataClass> call, @NonNull Response<JsonDataClass> response) {
                JsonDataClass postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                assert postResponse != null;
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserid() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Body: " + postResponse.getText() + "\n\n";

                textView.setText(content);

            }

            @Override
            public void onFailure(@NonNull Call<JsonDataClass> call, @NonNull Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        // JsonDataClass post = new JsonDataClass(23,"New title","New text");

        /*
           Pass the necessary value inside the parenthesis,
           As it is a test Api, the value wont get saved.
         */

        Call<JsonDataClass> call = jsonApi.createPost(23, "New title", "This is the changed text");
        call.enqueue(new Callback<JsonDataClass>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<JsonDataClass> call, @NonNull Response<JsonDataClass> response) {
                if (!response.isSuccessful()) {
                    textView.setText(getString(R.string.code) + response.code());
                    return;
                }

                JsonDataClass postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                assert postResponse != null;
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserid() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Body: " + postResponse.getText() + "\n\n";

                textView.setText(content);


            }

            @Override
            public void onFailure(@NonNull Call<JsonDataClass> call, @NonNull Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void deletePost() {

        Call<Void> call = jsonApi.deletePost(3);
        call.enqueue(new Callback<Void>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                textView.setText(getString(R.string.code) + response.code());
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}