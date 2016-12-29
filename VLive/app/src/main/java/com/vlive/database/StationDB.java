package com.vlive.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vlive.pojo.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Georges on 29/12/2016.
 */

public class StationDB {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public StationDB(Context context) {
        databaseHelper = new DatabaseHelper(context, "vlive.db", null, 1);
    }

    /**
     * Ouvre la BDD en écriture
     */
    public void open() {
        db = databaseHelper.getWritableDatabase();
    }

    /**
     * Ferme l'accès à la BDD
     */
    public void close() {
        db.close();
    }

    public SQLiteDatabase getBDD() {
        return db;
    }

    /**
     * Insère un contact en base de données
     *
     * @param station
     *            station à insérer
     * @return l'identifiant de la ligne insérée
     */
    public long insert(Station station) {
        ContentValues values = new ContentValues();

        // On insère les valeurs dans le ContentValues : on n'ajoute pas
        // l'identifiant car il est créé automatiquement
        values.put("NAME", station.getName());
        values.put("TOWN", station.getTown().getName());

        return db.insert("STATION", null, values);
    }

    public Integer getCount() {
        Cursor curCount= db.rawQuery("SELECT count(*) FROM STATION", null);
        curCount.moveToFirst();
        int count= curCount.getInt(0);
        curCount.close();
        return count;
    }

    public List<String> getDistinctTown(){

        List<String> towns = new ArrayList<>();

        Cursor curTown= db.rawQuery("SELECT DISTINCT TOWN FROM STATION ORDER BY TOWN", null);

        try {
            while (curTown.moveToNext()) {
                towns.add(curTown.getString(0));
            }
        } finally {
            curTown.close();
        }

        return towns;

    }




}
