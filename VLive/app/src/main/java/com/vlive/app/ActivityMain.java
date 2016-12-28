package com.vlive.app;

import android.app.Activity;
import android.os.Bundle;

import com.vlive.service.StationService;


public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération de la liste des stations
        StationService stationService = new StationService(  this  );
        stationService.execute();

    }

}

// http://www.vogella.com/tutorials/Retrofit/article.html#exercise-using-retrofit-to-query-stackoverflow-in-android