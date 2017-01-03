package com.vlive.activity;

import android.app.Activity;
import android.os.Bundle;

import com.vlive.asynctask.StationAsyncTask;
import com.vlive.asynctask.TownAsyncTask;


public class ActivityStation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String idTown = extras.getString("TOWN_ID");
            StationAsyncTask stationAsyncTask = new StationAsyncTask(  this  );
            stationAsyncTask.execute( Integer.valueOf(idTown) );
        }

    }

}
