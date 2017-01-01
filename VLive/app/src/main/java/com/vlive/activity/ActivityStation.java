package com.vlive.activity;

import android.app.Activity;
import android.os.Bundle;

import com.vlive.asynctask.TownAsyncTask;


public class ActivityStation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération de la liste des stations
        TownAsyncTask stationAsyncTask = new TownAsyncTask(  this  );
        stationAsyncTask.execute();

    }

}

//http://blog.grafixartist.com/create-gmail-style-list-in-android/
// http://www.vogella.com/tutorials/Retrofit/article.html#exercise-using-retrofit-to-query-stackoverflow-in-android