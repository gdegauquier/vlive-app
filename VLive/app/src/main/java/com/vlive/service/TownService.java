package com.vlive.service;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.vlive.adapter.TownAdapter;
import com.vlive.app.ActivityMain;
import com.vlive.app.R;
import com.vlive.pojo.Town;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by GDEGAUQUIER on 27/12/2016.
 */

public class TownService  extends AsyncTask<Void, Void, Void>{ //extends AsyncTask<Void, Void, Void> {


    Activity mActivity;

    List<Town> listT;

    public TownService(Activity activity) {
        mActivity = activity;
    }

    public List<Town> getAll(){

        List<Town> listTowns = new ArrayList<>();

        String urlStations = "" ;

        try {
            Document doc = Jsoup.connect("http://www.vlille.fr/stations/xml-stations.aspx").get();
            doc = Jsoup.parse(doc.toString(), "", Parser.xmlParser());
        }catch( IOException e){
            Log.e( "TownService","xml-stations could not be loaded."+e);
        }

        return listTowns;

    }

    @Override
    protected Void doInBackground(Void... params) {
        List<Town> listT =  getAll();
        this.listT = listT;

        return null;
    }

    protected void onPostExecute(Void unused)
    {

        TownAdapter adapter = new TownAdapter(mActivity.getApplicationContext(), listT);

        //Récupération du composant ListView
        ListView list = (ListView) mActivity.findViewById(R.id.listTown);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);


    }

//    @Override
//    protected Void doInBackground(Void... params) {
//        activity.listT = getAll();
//        return null;
//    }

    //http://www.survivingwithandroid.com/2014/04/parsing-html-in-android-with-jsoup-2.html

}
