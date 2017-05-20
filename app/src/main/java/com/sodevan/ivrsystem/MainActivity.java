package com.sodevan.ivrsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String[] gridViewString = {
            "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "0","Call","#"
    } ;
    TextView tv;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView= (GridView) findViewById(R.id.grid);
        lv= (ListView) findViewById(R.id.mylist);
        ArrayList<String> list=new ArrayList<String>();


        tv= (TextView) findViewById(R.id.number);
        CustomGrid adapterViewAndroid= new CustomGrid(MainActivity.this,gridViewString);
        gridView.setAdapter(adapterViewAndroid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (gridViewString[i].equals("Call")) {
                        Intent intent=new Intent(MainActivity.this,Call.class);
                    startActivity(intent);
                } else {
                    tv.append(gridViewString[i]);
                }
            }
        });

    }
}
