package com.vlive.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vlive.activity.R;
import com.vlive.pojo.Station;

import java.util.List;

/**
 * Created by Georges on 26/12/2016.
 */

public class TownAdapter extends BaseAdapter{

    // Une liste de villes
    private List<String> mListT;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public TownAdapter(Context context, List<String> listT) {
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
        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (view == null) {
            //Initialisation de notre item à partir du  layout XML - ROW
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.row_town, viewGroup, false);
        } else {
            layoutItem = (LinearLayout) view;
        }

        //(2) : Récupération des TextView de notre layout
        TextView station = (TextView)layoutItem.findViewById(R.id.townName);
        ImageView img = (ImageView)layoutItem.findViewById(R.id.imageTown);

        //(3) : Renseignement des valeurs

        station.setText(mListT.get(i));

        Drawable d = mContext.getResources().getDrawable( R.drawable.maps_and_flags );
        img.setImageDrawable( d );

        //On retourne l'item créé.
        return layoutItem;
    }
}
