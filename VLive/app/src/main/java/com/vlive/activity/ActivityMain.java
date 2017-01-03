package com.vlive.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vlive.asynctask.TownAsyncTask;


public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération de la liste des stations
        TownAsyncTask stationAsyncTask = new TownAsyncTask(  this  );
        stationAsyncTask.execute();

        ListView lv = (ListView)findViewById(R.id.listTown);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                TextView idTown = (TextView)view.findViewById( R.id.town_id );
                Log.d("listTowns", "item = id ="+id+ ", position = "+position + ", real id = "+idTown.getText());
                // Toast.makeText( ActivityMain.this, "test "+id, Toast.LENGTH_LONG);

                Intent intent = new Intent (ActivityMain.this, ActivityStation.class);
                intent.putExtra( "TOWN_ID", idTown.getText() );

                startActivity(intent);

                //Intent intent = new Intent(MainActivity.this, SendMessage.class);
                //intent.putExtra(EXTRA_MESSAGE, members.get(position).getMessage());
               // startActivity(intent);
            }
        });




    }








}

//http://blog.grafixartist.com/create-gmail-style-list-in-android/
// http://www.vogella.com/tutorials/Retrofit/article.html#exercise-using-retrofit-to-query-stackoverflow-in-android