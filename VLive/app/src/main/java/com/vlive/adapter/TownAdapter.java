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
import com.vlive.pojo.Town;

import java.util.List;

/**
 * Created by Georges on 26/12/2016.
 */

public class TownAdapter extends BaseAdapter{

    // Une liste de villes
    private List<Town> mListT;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public TownAdapter(Context context, List<Town> listT) {
        mContext = context;
        mListT = listT;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListT.size();
    }

    @Override
    public Object getItem(int i) {
        return mListT.get(i);
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
            relativeLayout = (RelativeLayout) mInflater.inflate(R.layout.row_town, viewGroup, false);
        } else {
            relativeLayout = (RelativeLayout) view;
        }

        //(2) : Récupération des TextView de notre layout
        TextView station = (TextView)relativeLayout.findViewById(R.id.townName);
        TextView count = (TextView)relativeLayout.findViewById(R.id.countBikes);
        TextView idTown = (TextView)relativeLayout.findViewById(R.id.town_id);


        ImageView imgPosition = (ImageView) relativeLayout.findViewById(R.id.imgLocation );
        ImageView imgBike = (ImageView) relativeLayout.findViewById(R.id.imgBike );

        Drawable dPosition = mContext.getResources().getDrawable( R.drawable.maps_and_flags );
        Drawable dbike = mContext.getResources().getDrawable( R.drawable.logo_vlive);


        //(3) : Renseignement des valeurs



        station.setText(mListT.get(i).getName());
        count.setText( mListT.get(i).getCountStations() +"" );
        idTown.setText( mListT.get(i).getId()+"" );
        imgPosition.setImageDrawable( dPosition );
        imgBike.setImageDrawable( dbike );
        //On retourne l'item créé.

        return relativeLayout;
    }
}
