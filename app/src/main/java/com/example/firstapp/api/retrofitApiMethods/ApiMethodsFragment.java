package com.example.firstapp.api.retrofitApiMethods;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/***Api used in json placeholder api
 *  <a href="https://jsonplaceholder.typicode.com/posts">Json PlaceHolder Api..</a>***/
public class ApiMethodsFragment extends Fragment {

    Button btnGetData, btnPostData, btnUpdateData, btnDeleteData, btnSubmit;
    EditText editTextUserId, editTextTitle, editTextBody, editTextId;
    private TextView textView;
    private JsonApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_api_methods, container, false);

        textView = view.findViewById(R.id.textResult);
        editTextId = view.findViewById(R.id.editTextId);
        editTextUserId = view.findViewById(R.id.editTextUserId);
        editTextTitle = view.findViewById(R.id.edittextTitle);
        editTextBody = view.findViewById(R.id.editTextBody);
        btnPostData = view.findViewById(R.id.btnPostData);
        btnGetData = view.findViewById(R.id.btnGetData);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnUpdateData = view.findViewById(R.id.btnUpdateData);
        btnDeleteData = view.findViewById(R.id.btnDeleteData);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.apiLink))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(JsonApiService.class);


        btnGetData.setOnClickListener(v -> getPosts());
        btnPostData.setOnClickListener(v -> postData());
        btnDeleteData.setOnClickListener(v -> deleteData());
        btnUpdateData.setOnClickListener(v -> updateData());
        return view;
    }

    public void getPosts() {
        Call<List<JsonDataClass>> call = apiService.getPosts();

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
                    content += "User ID: " + post.getUserId() + "\n";
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

    public void postData() {
        textView.setText("");
        editTextBody.setText("");
        editTextUserId.setText("");
        editTextTitle.setText("");
        //editTextId.setVisibility(View.VISIBLE);
        editTextUserId.setVisibility(View.VISIBLE);
        editTextTitle.setVisibility(View.VISIBLE);
        editTextBody.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnSubmit.setOnClickListener(v -> submitData());
    }

    public void submitData() {
        //Integer id = editTextId.getId();
        String userIdText = editTextUserId.getText().toString();
        int userId = Integer.parseInt(userIdText);
        Log.d("SubmitData", "UserID: " + userId);
        String title = editTextTitle.getText().toString();
        Log.d("SubmitData", "Title: " + title);
        String text = editTextBody.getText().toString();
        Log.d("SubmitData", "Text: " + text);
        putData(userId, title, text);
    }

    public void putData(int userId, String title, String text) {

        //editTextId.setVisibility(View.GONE);
        editTextUserId.setVisibility(View.GONE);
        editTextTitle.setVisibility(View.GONE);
        editTextBody.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);

        Call<JsonDataClass> call = apiService.createPost(userId, title, text);
        call.enqueue(new Callback<JsonDataClass>() {
            @Override
            public void onResponse(@NonNull Call<JsonDataClass> call, @NonNull Response<JsonDataClass> response) {
                if (response.isSuccessful()) {
                    //editTextId.setVisibility(View.GONE);
                    editTextUserId.setVisibility(View.GONE);
                    editTextTitle.setVisibility(View.GONE);
                    editTextBody.setVisibility(View.GONE);
                    btnSubmit.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Data posted successfully", Toast.LENGTH_SHORT).show();
                    JsonDataClass postResponse = response.body();
                    String content = "";
                    content += "Code: " + response.code() + "\n";
                    assert postResponse != null;
                    content += "ID: " + postResponse.getId() + "\n";
                    content += "User ID: " + postResponse.getUserId() + "\n";
                    content += "Title: " + postResponse.getTitle() + "\n";
                    content += "Body: " + postResponse.getText() + "\n\n";
                    textView.setText(content);
                    textView.append("\n\n");

                    getPosts();
                } else {
                    Toast.makeText(getActivity(), "Failed to post data.", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Error:" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonDataClass> call, @NonNull Throwable t) {
                Log.d("TAG", "Error in getting response " + t.getMessage());
                Toast.makeText(getActivity(), "Failed to post data. Error: ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteData() {
        textView.setText("");
        editTextId.setText("");
        editTextId.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        int id = editTextId.getId();
        btnSubmit.setOnClickListener(v -> deletePost(id));
    }

    private void deletePost(int id) {
        btnSubmit.setVisibility(View.GONE);
        editTextId.setVisibility(View.GONE);
        Call<Void> call = apiService.deletePost(id);
        call.enqueue(new Callback<Void>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                textView.setText(getString(R.string.code) + response.code());
                textView.append("\n");
                textView.setText("Data deleted successfully!!\n\n");
                getPosts();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    public void updateData() {

        textView.setText("");
        editTextBody.setText("");
        editTextUserId.setText("");
        editTextTitle.setText("");
        editTextId.setText("");
        editTextId.setVisibility(View.VISIBLE);
        editTextUserId.setVisibility(View.VISIBLE);
        editTextTitle.setVisibility(View.VISIBLE);
        editTextBody.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnSubmit.setOnClickListener(v -> {

            int id = Integer.parseInt(editTextId.getText().toString());
            Log.d("SubmitData", "ID: " + id);
            String userIdText = editTextUserId.getText().toString();
            int userId = Integer.parseInt(userIdText);
            Log.d("SubmitData", "UserID: " + userId);
            String title = editTextTitle.getText().toString();
            Log.d("SubmitData", "Title: " + title);
            String text = editTextBody.getText().toString();
            Log.d("SubmitData", "Text: " + text);
            updatePost(id, userId, title, text);

        });
    }

    public void updatePost(int id, int userId, String title, String text) {
        JsonDataClass updatedData = new JsonDataClass(userId, title, text);

        Call<JsonDataClass> call = apiService.patchPost(id, updatedData);
        call.enqueue(new Callback<JsonDataClass>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<JsonDataClass> call, @NonNull Response<JsonDataClass> response) {
                if (response.isSuccessful()) {
                    editTextId.setVisibility(View.GONE);
                    editTextUserId.setVisibility(View.GONE);
                    editTextTitle.setVisibility(View.GONE);
                    editTextBody.setVisibility(View.GONE);
                    btnSubmit.setVisibility(View.GONE);
                    textView.setText(getString(R.string.code) + response.code());
                    Toast.makeText(getActivity(), "Data updated successfully", Toast.LENGTH_SHORT).show();

                    JsonDataClass postResponse = response.body();
                    String content = "";
                    content += "Code: " + response.code() + "\n";
                    assert postResponse != null;
                    content += "ID: " + postResponse.getId() + "\n";
                    content += "User ID: " + postResponse.getUserId() + "\n";
                    content += "Title: " + postResponse.getTitle() + "\n";
                    content += "Body: " + postResponse.getText() + "\n\n";
                    textView.setText(content);
                    textView.append("\n\n");
                    getPosts();

                } else {

                    Toast.makeText(getActivity(), "Failed to update data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonDataClass> call, @NonNull Throwable t) {

                Log.e("UpdateData", "Failed to update data: " + t.getMessage());
                Toast.makeText(getActivity(), "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




/*** Below Code is an older version of implementation of api calls
 * Api used is JsonPlaceHolder API.
 * In this fragment, I have implemented all the api methods(GET,PUT,POST,PATCH,DELETE), but only one method gets called at a time.
 * For example, if you want to implement Get method, uncomment the getPosts() while commenting other methods. This
 * retrieves the data from the Json Placeholder Api.
 * Similarly, if you want to perform POST method uncomment the createPost() method while commenting other methods.
 * For PUT or PATCH method uncomment the updatePosts() while commenting others.
 * For DELETE , uncomment the deletePost() while commenting others.
 * */


//    private TextView textView;
//    private JsonApiService jsonApi;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_api_methods, container, false);
//
//        textView = view.findViewById(R.id.textResult);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        jsonApi = retrofit.create(JsonApiService.class);
//         //getPosts();
//        //updatePosts();
//         createPost();
//        // deletePost();
//        return view;
//
//    }
//
//    public void getPosts() {
//
//        Call<List<JsonDataClass>> call = jsonApi.getPosts();
//
//        call.enqueue(new Callback<List<JsonDataClass>>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(@NonNull Call<List<JsonDataClass>> call, @NonNull Response<List<JsonDataClass>> response) {
//                if (!response.isSuccessful()) {
//                    textView.setText(getString(R.string.code) + response.code());
//                    return;
//                }
//                List<JsonDataClass> posts = response.body();
//                assert posts != null;
//                for (JsonDataClass post : posts) {
//                    String content = "";
//                    content += "ID: " + post.getId() + "\n";
//                    content += "User ID: " + post.getUserid() + "\n";
//                    content += "Title: " + post.getTitle() + "\n";
//                    content += "Body: " + post.getText() + "\n\n";
//
//                    textView.append(content);
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<JsonDataClass>> call, @NonNull Throwable t) {
//                textView.setText(t.getMessage());
//
//            }
//        });
//    }
//
//    /***
//     *  In updatePosts, If you are going to implement PATCH , ensure you call patchPost instead of putPost (ie)
//     *  Call<JsonDataClass> call = jsonApi.patchPost(5, json) should be used instead of Call<JsonDataClass> call = jsonApi.putPost(5, json);
//     * Here 5, represents the id , you can open <a href="https://jsonplaceholder.typicode.com/posts">.Json PlaceHolder Api..</a>
//     * and check with the corresponding id value to know if it is getting updated or not.
//     * ***/
//    private void updatePosts() {
//        JsonDataClass json = new JsonDataClass(23, null, "New ");
//
//        Call<JsonDataClass> call = jsonApi.putPost(5, json);
//
//        call.enqueue(new Callback<JsonDataClass>() {
//            @Override
//            public void onResponse(@NonNull Call<JsonDataClass> call, @NonNull Response<JsonDataClass> response) {
//                JsonDataClass postResponse = response.body();
//                String content = "";
//                content += "Code: " + response.code() + "\n";
//                assert postResponse != null;
//                content += "ID: " + postResponse.getId() + "\n";
//                content += "User ID: " + postResponse.getUserid() + "\n";
//                content += "Title: " + postResponse.getTitle() + "\n";
//                content += "Body: " + postResponse.getText() + "\n\n";
//
//                textView.setText(content);
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<JsonDataClass> call, @NonNull Throwable t) {
//                textView.setText(t.getMessage());
//            }
//        });
//    }
//
//    private void createPost() {
//        // JsonDataClass post = new JsonDataClass(23,"New title","New text");
//
//        /*
//           Pass the necessary value inside the parenthesis,
//           As it is a test Api, the value wont get saved.
//         */
//
//        Call<JsonDataClass> call = jsonApi.createPost(23, "New title", "This is changed ");
//        call.enqueue(new Callback<JsonDataClass>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(@NonNull Call<JsonDataClass> call, @NonNull Response<JsonDataClass> response) {
//                if (!response.isSuccessful()) {
//                    textView.setText(getString(R.string.code) + response.code());
//                    return;
//                }
//
//                JsonDataClass postResponse = response.body();
//                String content = "";
//                content += "Code: " + response.code() + "\n";
//                assert postResponse != null;
//                content += "ID: " + postResponse.getId() + "\n";
//                content += "User ID: " + postResponse.getUserid() + "\n";
//                content += "Title: " + postResponse.getTitle() + "\n";
//                content += "Body: " + postResponse.getText() + "\n\n";
//
//                textView.setText(content);
//
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<JsonDataClass> call, @NonNull Throwable t) {
//                textView.setText(t.getMessage());
//            }
//        });
//    }
//
//    private void deletePost() {
//
//        Call<Void> call = jsonApi.deletePost(3);
//        call.enqueue(new Callback<Void>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
//                textView.setText(getString(R.string.code) + response.code());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
//                textView.setText(t.getMessage());
//            }
//        });
//    }
