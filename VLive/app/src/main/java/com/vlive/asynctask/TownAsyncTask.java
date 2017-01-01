package com.vlive.asynctask;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;


import com.vlive.activity.R;
import com.vlive.adapter.TownAdapter;
import com.vlive.database.StationDB;
import com.vlive.pojo.Station;
import com.vlive.pojo.Town;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by GDEGAUQUIER on 27/12/2016.
 */

public class TownAsyncTask extends AsyncTask<Void, Integer, Void>{ //extends AsyncTask<Void, Void, Void> {

    private Dialog pdia;
    Activity mActivity;
    List<Station> listS = new ArrayList<>();
    //Cursor listTowns ;
    List<Town> listTowns = new ArrayList<>();

    public TownAsyncTask(Activity activity) {
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
    protected Void doInBackground(Void... params) {

        //StationDB stationDB = new StationDB(mActivity.getApplicationContext());
        //stationDB.open();

        //listTowns = stationDB.getCountByTown();

//        if ( listTowns.getCount() != 0 && 1==2 ){
//            Log.d("TownAsyncTask", "Stations already loaded.");
//            stationDB.close();
//            return null;
//        }

        Document document;
        JSONParser parser = new JSONParser();

        try {
            // Connect to the web site
            //document = Jsoup.connect("http://www.vlille.fr/stations/xml-stations.aspx").get();

            document = Jsoup.connect("http://192.168.0.15/vlive-api/index.php/api/v1/towns").get();

            Object obj = parser.parse(document.body().text());
            JSONArray array = (JSONArray)obj;

            for ( int ind = 0 ; ind < array.size() ; ind ++ ){
                JSONObject objTown = (JSONObject) array.get(ind);

                Town t = new Town();
                t.setName(objTown.get("name").toString());
                t.setId(Integer.valueOf(objTown.get("id").toString()));
                t.setCountStations(Integer.valueOf(objTown.get("count").toString()));
                listTowns.add(t);
            }

        } catch (IOException | ParseException e) {
            Log.d("TownAsyncTask", "Failed to get stations.");
            return null;
        }

        //remplir la liste
        publishProgress(0);
       // setStationsIntoList( document );

        //remplir liste dans la DB
//        if ( listS != null ){
//
//            int ind = 0;
//            for ( Station station : listS ){
//                ind++;
//
//                if( station.getTown() == null){
//                    Log.d("TownAsyncTask","station "+station.getName() +" - no town found.");
//                    continue;
//                }
//                stationDB.insert( station );
//            }
//
//            Log.d("countDB",stationDB.getCount() +" records into BDD");
//            listTowns = stationDB.getCountByTown();
//
//            stationDB.close();
//
//        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        pdia.hide();

        ListView list = (ListView) mActivity.findViewById( R.id.listTown );
        TownAdapter townAdapter = new TownAdapter( mActivity.getApplicationContext(), listTowns);
        list.setAdapter( townAdapter );
    }


//    private void setStationsIntoList( Document document ){
//
//        int ind = 0;
//        int total = document.getElementsByAttribute("id").size();
//
//        for ( Element el : document.getElementsByAttribute("id") ){
//            ind ++;
//            publishProgress((int) ((ind / (float) total) * 100));
//            String id = el.attr("id");
//            String name = el.attr("name");
//            String longitude  = el.attr("lng");
//            String latitude  = el.attr("lat");
//
//            if ( null == id || null == name){
//                continue;
//            }
//            Log.d("TownAsyncTask", "ID Station = "+ id );
//            Station station = new Station();
//            station.setId( id );
//            station.setName( name );
//            station.setLongitude( longitude );
//            station.setLatitude( latitude );
//
//            setTownForStation( station );
//
//            listS.add( station );
//
//        }
//
//    }


//    private void setTownForStation( Station station ){
//
//        if (   null == station.getLatitude() || null ==  station.getLongitude()  ){
//            Log.w( "TownAsyncTask","Check Latitude / Longitude : Town not found for the station id ="+station.getId() + ", name = "+station.getName() );
//            return ;
//        }
//
//        String doc = null ;
//
//        Geocoder geo = new Geocoder( mActivity.getApplicationContext(), Locale.FRANCE );
//
//        try {
//            List<Address> adressFromLatLng =  geo.getFromLocation(Double.valueOf(station.getLatitude()), Double.valueOf(station.getLongitude()), 1 );
//
//            Town town = new Town();
//            town.setName(   adressFromLatLng.get(0).getLocality().toString() );
//            station.setTown( town );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}
