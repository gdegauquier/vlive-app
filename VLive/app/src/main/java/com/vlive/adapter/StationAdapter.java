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

import com.vlive.app.R;
import com.vlive.pojo.Station;
import com.vlive.pojo.Town;

import java.util.List;

/**
 * Created by Georges on 26/12/2016.
 */

public class StationAdapter extends BaseAdapter{

    // Une liste de personnes
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

        String townName = null == mListS.get(i).getTown() ? "?" : mListS.get(i).getTown().getName() ;

        station.setText(mListS.get(i).getId() + " - " + mListS.get(i).getName() + " - " + townName);

        Drawable d = mContext.getResources().getDrawable( R.drawable.maps_and_flags );
        img.setImageDrawable( d );

        //(4) Changement de la couleur du fond de notre item
        //if (mListP.get(position).genre == Personne.MASCULIN) {
        //    layoutItem.setBackgroundColor(Color.BLUE);
        //} else {
        //    layoutItem.setBackgroundColor(Color.MAGENTA);
        //}

        //On retourne l'item créé.
        return layoutItem;
    }
}
