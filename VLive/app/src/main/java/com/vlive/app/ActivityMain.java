package com.vlive.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.vlive.adapter.TownAdapter;
import com.vlive.pojo.Town;
import com.vlive.service.TownService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends Activity {

    public List<Town> listT = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mListView = (ListView) findViewById(R.id.listView);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityMain.this,
                android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération de la liste des personnes
        Title title = new Title( );
        title.execute();

    }

    // Title AsyncTask
    private class Title extends AsyncTask<Void, Void, Void> {
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressDialog = new ProgressDialog(MainActivity.this);
//            mProgressDialog.setTitle("Android Basic JSoup Tutorial");
//            mProgressDialog.setMessage("Loading...");
//            mProgressDialog.setIndeterminate(false);
//            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect("http://www.vlille.fr/stations/xml-stations.aspx").get();
                document = Jsoup.parse(document.toString(), "", Parser.xmlParser());

                for ( Element el : document.getElementsByAttribute("id") ){
                    String id = el.attr("id");
                    String name = el.attr("name");
                    if ( null == id || null == name){
                        continue;
                    }
                    Log.d("ID Station", id );
                    Town town = new Town(id + " - " + name );
                    listT.add( town );

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView
            //TextView txttitle = (TextView) findViewById(R.id.titletxt);
            //txttitle.setText(title);
            //mProgressDialog.dismiss();

            ListView list = (ListView) findViewById( R.id.listTown );
            TownAdapter townAdapter = new TownAdapter( getApplicationContext(), listT);
            list.setAdapter( townAdapter );

        }
    }

}

// http://www.vogella.com/tutorials/Retrofit/article.html#exercise-using-retrofit-to-query-stackoverflow-in-android