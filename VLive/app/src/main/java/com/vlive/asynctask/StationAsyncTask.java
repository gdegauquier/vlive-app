package com.vlive.asynctask;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;


import com.vlive.adapter.StationAdapter;
import com.vlive.activity.R;
import com.vlive.adapter.TownAdapter;
import com.vlive.database.StationDB;
import com.vlive.pojo.Station;
import com.vlive.pojo.Town;


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

public class StationAsyncTask extends AsyncTask<Void, Void, Void>{ //extends AsyncTask<Void, Void, Void> {

    Activity mActivity;
    List<Station> listS = new ArrayList<>();
    List<String> listTowns = new ArrayList<>();
    public StationAsyncTask(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(Void... params) {

        Document document;
        try {
            // Connect to the web site
            document = Jsoup.connect("http://www.vlille.fr/stations/xml-stations.aspx").get();
            document = Jsoup.parse(document.toString(), "", Parser.xmlParser());
        } catch (IOException e) {
            Log.d("StationAsyncTask", "Failed to get stations.");
            return null;
        }

        //remplir la liste
        setStationsIntoList( document );

        //remplir liste dans la DB
        if ( listS != null ){

            StationDB stationDB = new StationDB(mActivity.getApplicationContext());
            stationDB.open();

            for ( Station station : listS ){
                if( station.getTown() == null){
                    Log.d("StationAsyncTask","station "+station.getName() +" - no town found.");
                    continue;
                }
                stationDB.insert( station );
            }

            Log.d("countDB",stationDB.getCount() +" records into BDD");


            listTowns = stationDB.getDistinctTown();

            stationDB.close();




        }


        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        ListView list = (ListView) mActivity.findViewById( R.id.listTown );
        TownAdapter townAdapter = new TownAdapter( mActivity.getApplicationContext(), listTowns);
        list.setAdapter( townAdapter );

    }


    private void setStationsIntoList( Document document ){

        int ind = 0;

        for ( Element el : document.getElementsByAttribute("id") ){
            ind ++;
            String id = el.attr("id");
            String name = el.attr("name");
            String longitude  = el.attr("lng");
            String latitude  = el.attr("lat");

            if ( null == id || null == name){
                continue;
            }
            Log.d("StationAsyncTask", "ID Station = "+ id );
            Station station = new Station();
            station.setId( id );
            station.setName( name );
            station.setLongitude( longitude );
            station.setLatitude( latitude );

            setTownForStation( station );

            listS.add( station );

            //if ( ind == 10){
            //    break;
            //}

        }

    }


    private void setTownForStation( Station station ){

        if (   null == station.getLatitude() || null ==  station.getLongitude()  ){
            Log.w( "StationAsyncTask","Check Latitude / Longitude : Town not found for the station id ="+station.getId() + ", name = "+station.getName() );
            return ;
        }

        String doc = null ;

        Geocoder geo = new Geocoder( mActivity.getApplicationContext(), Locale.FRANCE );

        try {
            List<Address> adressFromLatLng =  geo.getFromLocation(Double.valueOf(station.getLatitude()), Double.valueOf(station.getLongitude()), 1 );

            Town town = new Town();
            town.setName(   adressFromLatLng.get(0).getLocality().toString() );
            station.setTown( town );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
