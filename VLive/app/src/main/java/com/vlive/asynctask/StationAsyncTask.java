package com.vlive.asynctask;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.vlive.activity.R;
import com.vlive.adapter.StationAdapter;
import com.vlive.adapter.TownAdapter;
import com.vlive.pojo.Station;
import com.vlive.pojo.Town;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by GDEGAUQUIER on 27/12/2016.
 */

public class StationAsyncTask extends AsyncTask<Integer, Integer, Void>{ //extends AsyncTask<Void, Void, Void> {

    private Dialog pdia;
    Activity mActivity;
    List<Station> listS = new ArrayList<>();
    //Cursor listStations ;
    List<Station> listStations = new ArrayList<>();

    public StationAsyncTask(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pdia = new ProgressDialog(mActivity);
        pdia.setTitle( "Loading...");
        pdia.show();
    }


    protected void onProgressUpdate(Integer...progress) {

        pdia.setTitle("Loading... "+progress[0]+" %" );

    }


    @Override
    protected Void doInBackground(Integer... params) {

        String townId = params[0].toString();

        Document document;
        JSONParser parser = new JSONParser();

        try {
            // Connect to the web site
            document = Jsoup.connect("http://dugauguez.free.fr/index.php/api/v1/towns/"+townId+"/stations").get();

            Object obj = parser.parse(document.body().text());
            JSONArray array = (JSONArray)obj;

            for ( int ind = 0 ; ind < array.size() ; ind ++ ){
                JSONObject objStation = (JSONObject) array.get(ind);

                Station s = new Station();

                s.setId( Integer.valueOf(objStation.get("id").toString()) );
                s.setName( objStation.get("name").toString() );
                s.setAddress( objStation.get("address").toString() );

                Town t = new Town();
                t.setId( Integer.valueOf(objStation.get("town_id").toString()) );
                t.setName( objStation.get("town_name").toString() );

                s.setTown( t );

                listStations.add(s);
            }

        } catch (IOException | ParseException e) {
            Log.d("TownAsyncTask", "Failed to get stations.");
            return null;
        }

        //remplir la liste
        publishProgress(0);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        pdia.hide();

        ListView list = (ListView) mActivity.findViewById( R.id.listStation );
        StationAdapter townAdapter = new StationAdapter( mActivity.getApplicationContext(), listStations);
        list.setAdapter( townAdapter );

    }

}
