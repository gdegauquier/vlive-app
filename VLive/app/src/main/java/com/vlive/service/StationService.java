package com.vlive.service;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;


import com.vlive.adapter.StationAdapter;
import com.vlive.app.R;
import com.vlive.pojo.Station;
import com.vlive.pojo.Town;

import org.json.JSONException;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by GDEGAUQUIER on 27/12/2016.
 */

public class StationService  extends AsyncTask<Void, Void, Void>{ //extends AsyncTask<Void, Void, Void> {


    Activity mActivity;

    List<Station> listS = new ArrayList<>();

    public StationService(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected Void doInBackground(Void... params) {
        try {
            // Connect to the web site
            Document document = Jsoup.connect("http://www.vlille.fr/stations/xml-stations.aspx").get();
            document = Jsoup.parse(document.toString(), "", Parser.xmlParser());

            Log.d("perfDIB__avant", String.valueOf(new Date()));

            for ( Element el : document.getElementsByAttribute("id") ){
                String id = el.attr("id");
                String name = el.attr("name");
                String longitude  = el.attr("lng");
                String latitude  = el.attr("lat");


                if ( null == id || null == name){
                    continue;
                }
                Log.d("ID Station", id );
                Station station = new Station();
                station.setId( id );
                station.setName( name );
                station.setLongitude( longitude );
                station.setLatitude( latitude );

                setTownForStation( station );


                listS.add( station );

            }

            Log.d("perfDIB__before", String.valueOf(new Date()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        ListView list = (ListView) mActivity.findViewById( R.id.listTown );
        StationAdapter townAdapter = new StationAdapter( mActivity.getApplicationContext(), listS);
        list.setAdapter( townAdapter );

    }


    private void setTownForStation( Station station ){

        if (   null == station.getLatitude() || null ==  station.getLongitude()  ){
            Log.w( "StationService","Check Latitude / Longitude : Town not found for the station id ="+station.getId() + ", name = "+station.getName() );
            return ;
        }

        String doc = null ;

        Geocoder geo = new Geocoder( mActivity.getApplicationContext(), Locale.FRANCE );

        try {
            List<Address> test =  geo.getFromLocation(Double.valueOf(station.getLatitude()), Double.valueOf(station.getLongitude()), 1 );

            Town town = new Town();
            town.setName(   test.get(0).getLocality().toString() );

            station.setTown( town );

        } catch (IOException e) {
            e.printStackTrace();
        }



        /*try {



            String townName = "";

            String url ="http://maps.googleapis.com/maps/api/geocode/json?latlng=" + station.getLatitude() +","+ station.getLongitude();
            doc = Jsoup.connect(url).ignoreContentType(true).execute().body();

            //parse + read google res
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(doc);
            JSONObject jb = (JSONObject) obj;

            JSONArray results = (JSONArray) jb.get("results");
            JSONObject adressComp = (JSONObject) results.get(1);
            JSONArray adressCompObj = (JSONArray)adressComp.get("address_components");
            JSONObject townObj      = (JSONObject) adressCompObj.get(1);
            townName = townObj.get("short_name").toString();

            Town town = new Town();
            town.setName( townName );

            station.setTown( town );

            Log.d( "StationService","Call GoogleAPI geocode : Town is "+townName.toString()+" for the station id =c"+station.getId() + ", name = "+station.getName() );

        }catch ( IOException  | IndexOutOfBoundsException | ParseException e){
            Log.w( "StationService","Call GoogleAPI geocode : Town not found for the station id ="+station.getId() + ", name = "+station.getName(), e );
            return ;
        }*/

        //Log.d( "StationService", "content = "+doc );

    }

}
