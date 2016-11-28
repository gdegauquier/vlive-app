package com.vlive.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vlive.pojo.Town;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity implements Callback<ArrayList<Town>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResponse(Call<ArrayList<Town>> call, Response<ArrayList<Town>> response) {
        //ok
    }

    @Override
    public void onFailure(Call<ArrayList<Town>> call, Throwable t) {
        //ko
    }
}

// http://www.vogella.com/tutorials/Retrofit/article.html#exercise-using-retrofit-to-query-stackoverflow-in-android