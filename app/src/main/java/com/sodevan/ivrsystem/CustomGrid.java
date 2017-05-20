package com.sodevan.ivrsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ronaksakhuja on 21/05/17.
 */

public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private final String[] gridViewString;

    public CustomGrid(Context context, String[] gridViewString) {
        mContext = context;
        this.gridViewString = gridViewString;
    }
    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View gridviewAndroid;
        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null){
            gridviewAndroid=new View(mContext);
            gridviewAndroid=inflater.inflate(R.layout.gridsample,null);
            TextView tv= (TextView) gridviewAndroid.findViewById(R.id.text);
            tv.setText(gridViewString[i]);
        }
        else
        {
            gridviewAndroid=(View)view;
        }
        return gridviewAndroid;
    }
}
