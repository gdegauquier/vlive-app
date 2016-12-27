package com.vlive.service;

import android.os.AsyncTask;
import android.util.Log;

import com.vlive.app.ActivityMain;
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

public class TownService extends AsyncTask<Void, Void, Void> {

    private ActivityMain activity;

    public TownService(  ActivityMain activity   ){
        this.activity = activity ;
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
        activity.listT = getAll();
        return null;
    }

    //http://www.survivingwithandroid.com/2014/04/parsing-html-in-android-with-jsoup-2.html

}
