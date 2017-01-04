package com.vlive.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vlive.activity.R;
import com.vlive.pojo.Station;
import com.vlive.pojo.Town;

import java.util.List;

/**
 * Created by Georges on 26/12/2016.
 */

public class StationAdapter extends BaseAdapter{

    // Une liste de villes
    private List<Station> mListS;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public StationAdapter(Context context, List<Station> listS) {
        mContext = context;
        mListS = listS;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListS.size();
    }

    @Override
    public Object getItem(int i) {
        return mListS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout relativeLayout;
        //(1) : Réutilisation des layouts
        if (view == null) {
            //Initialisation de notre item à partir du  layout XML - ROW
            relativeLayout = (RelativeLayout) mInflater.inflate(R.layout.row_station, viewGroup, false);
        } else {
            relativeLayout = (RelativeLayout) view;
        }

        //(2) : Récupération des TextView de notre layout
        TextView station = (TextView)relativeLayout.findViewById(R.id.stationName);
        TextView stationAddress = (TextView) relativeLayout.findViewById(R.id.stationAddress);


        ImageView imgVlille = (ImageView) relativeLayout.findViewById(R.id.imgVlille );
        Drawable dVlille = mContext.getResources().getDrawable( R.drawable.logo_vlive );

        ImageView imgBike = (ImageView) relativeLayout.findViewById(R.id.imgBike);
        Drawable  dBike   = mContext.getResources().getDrawable(R.drawable.bicycle);

        //(3) : Renseignement des valeurs
        station.setText(mListS.get(i).getName());
        stationAddress.setText( mListS.get(i).getAddress() );
        imgVlille.setImageDrawable( dVlille );
        imgBike.setImageDrawable(dBike);


        //On retourne l'item créé.

        return relativeLayout;
    }
}
