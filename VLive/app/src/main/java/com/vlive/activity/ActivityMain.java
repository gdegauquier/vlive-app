package com.vlive.activity;

import android.app.Activity;
import android.os.Bundle;

import com.vlive.asynctask.StationAsyncTask;


public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération de la liste des stations
        StationAsyncTask stationAsyncTask = new StationAsyncTask(  this  );
        stationAsyncTask.execute();

    }

}

// http://www.vogella.com/tutorials/Retrofit/article.html#exercise-using-retrofit-to-query-stackoverflow-in-android