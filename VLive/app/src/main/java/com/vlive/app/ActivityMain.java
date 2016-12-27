package com.vlive.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.vlive.adapter.TownAdapter;
import com.vlive.pojo.Town;

import java.util.ArrayList;

public class ActivityMain extends Activity {

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
        ArrayList<Town> listT = Town.get();

        //Création et initialisation de l'Adapter pour les personnes
        TownAdapter adapter = new TownAdapter(this, listT);

        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.listTown);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

    }
}

// http://www.vogella.com/tutorials/Retrofit/article.html#exercise-using-retrofit-to-query-stackoverflow-in-android