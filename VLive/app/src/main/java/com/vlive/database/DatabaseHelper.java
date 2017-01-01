package com.vlive.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by Georges on 28/12/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //private static final String TABLE_CONTACTS = "table_contacts";
    //private static final String COL_ID = "ID";
    //private static final String COL_NOM = "NOM";
    //private static final String COL_PRENOM = "PRENOM";
    //private static final String COL_NUM_TEL = "NUM_TEL";

    private static final String CREATE_TABLE_STATION =
            "CREATE TABLE STATION ( ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "                     NAME TEXT NOT NULL,  "
            +"                      TOWN NOT NULL);";

    public DatabaseHelper(Context context, String name, CursorFactory factory,
                          int version)
    {
        super(context, name, factory, version);
    }

    //http://www.yoannzimero.com/blog/utiliser-sqlite-avec-android/

    /**
     * Cette méthode est appelée lors de la toute première création de la base
     * de données. Ici, on doit créer les tables et éventuellement les populer.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on crée la table table_contacts dans la BDD
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS STATION ( ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "                     NAME TEXT NOT NULL,  TOWN NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on supprime la table table_contacts de la BDD et on recrée la BDD
        //db.execSQL("DROP TABLE STATION ;");
        //onCreate(db);
    }
}