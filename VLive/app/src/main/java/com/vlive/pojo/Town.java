package com.vlive.pojo;

import java.util.ArrayList;

/**
 * Created by Georges on 28/11/2016.
 */

public class Town {

    private int id;
    private String name;
    private int countStations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountStations() {
        return countStations;
    }

    public void setCountStations(int countStations) {
        this.countStations = countStations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Initialise une liste de personnes
     *
     * @return une liste de "Personne"
     */
    /*public static ArrayList<Town> get() {
        ArrayList<Town> listTowns = new ArrayList<Town>();

        listTowns.add(new Town("CROIX"));
        listTowns.add(new Town("FACHES-THUMESNIL"));
        listTowns.add(new Town("HELLEMMES"));
        listTowns.add(new Town("LA MADELEINE"));
        listTowns.add(new Town("LAMBERSART"));
        listTowns.add(new Town("LILLE"));
        listTowns.add(new Town("LILLE HELLEMMES"));
        listTowns.add(new Town("LOMME"));
        listTowns.add(new Town("MARCQ EN BAROEUL"));
        listTowns.add(new Town("MONS EN BAROEUL"));
        listTowns.add(new Town("RONCHIN"));
        listTowns.add(new Town("ROUBAIX"));
        listTowns.add(new Town("SAINT ANDRE LEZ LILLE"));
        listTowns.add(new Town("TOURCOING"));
        listTowns.add(new Town("VILLENEUVE D'ASCQ"));
        listTowns.add(new Town("WATTRELOS"));

        return listTowns;
    }*/
}