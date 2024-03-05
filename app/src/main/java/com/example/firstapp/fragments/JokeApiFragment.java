package com.example.firstapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firstapp.POJOClass.ApiService;
import com.example.firstapp.POJOClass.DataClass;
import com.example.firstapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeApiFragment extends Fragment {

    private TextView jokeTextView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joke_api, container, false);

        jokeTextView = view.findViewById(R.id.tv_joke);
        Button getJokeButton = view.findViewById(R.id.btn_joke);
        progressBar = view.findViewById(R.id.LoadingPB);
        getJokeButton.setOnClickListener(v ->{
                progressBar.setVisibility(View.VISIBLE);
                fetchJoke();
    });
        return view;
    }

    private void fetchJoke() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.Get_api_link))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<DataClass> call = service.getJoke();

        call.enqueue(new Callback<DataClass>() {
            @Override
            public void onResponse(@NonNull Call<DataClass> call, @NonNull Response<DataClass> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    DataClass jokeResponse = response.body();
                    jokeTextView.setText(jokeResponse.getValue());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataClass> call, @NonNull Throwable t) {
                jokeTextView.setText(R.string.failed_to_fetch_joke);
            }
        });
    }
}