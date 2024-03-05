package com.example.firstapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firstapp.POJOClass.PostApiService;
import com.example.firstapp.POJOClass.PostDataClass;
import com.example.firstapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApiFragment extends Fragment {

    EditText name, email;
    TextView data;
    Button btnSend;

    ProgressBar loadingBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_api, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        data = view.findViewById(R.id.viewData);
        btnSend = view.findViewById(R.id.btn_sendData);
        loadingBar = view.findViewById(R.id.loadingPB);
        data = view.findViewById(R.id.viewData);

        btnSend.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                loadingBar.setVisibility(View.VISIBLE);
                postData(name.getText().toString(), email.getText().toString());
                name.setText("");
                email.setText("");
            }
        });
        return view;
    }

    private void postData(String name, String email) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build();

        PostApiService postApiService = retrofit.create(PostApiService.class);
        PostDataClass postDataClass = new PostDataClass(name, email);

        Call<PostDataClass> call = postApiService.createPost(postDataClass);
        call.enqueue(new Callback<PostDataClass>() {

            @Override
            public void onResponse(@NonNull Call<PostDataClass> call, @NonNull Response<PostDataClass> response) {
                Toast.makeText(requireContext(), "Data added to API", Toast.LENGTH_SHORT).show();
                loadingBar.setVisibility(View.GONE);

                PostDataClass responseFromAPI = response.body();
                Log.d("ThreadFragment", "Response from API: " + responseFromAPI);

                if (responseFromAPI != null) {
                    String responseString = getString(R.string.response_code) + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Email : " + responseFromAPI.getEmail();
                    Log.d("ThreadFragment", "Response string: " + responseString);

                    if (data != null) {
                        data.setText(responseString);
                        data.setVisibility(View.VISIBLE);
                    } else {
                        Log.e("ThreadFragment", "TextView data is null");
                    }
                } else {
                    Log.e("ThreadFragment", "Response from API is null");
                }
            }


            @Override
            public void onFailure(@NonNull Call<PostDataClass> call, @NonNull Throwable t) {
                // setting error text we get error response from API
                data.setText(R.string.no_data_available);
            }
        });
    }

}

