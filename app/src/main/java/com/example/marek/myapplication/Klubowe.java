package com.example.marek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Klubowe extends AppCompatActivity {

    public void reakcja(){
        Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);
    }


    public void reakcja1(){
        Intent i = new Intent(this,KlubKazik.class);
        startActivity(i);
    }
    public void reakcja2(){
        Intent i = new Intent(this,KlubShine.class);
        startActivity(i);
    }
    public void reakcja3(){
        Intent i = new Intent(this,KlubCzekolada.class);
        startActivity(i);
    }
    public void reakcja4(){
        Intent i = new Intent(this,KlubSilence.class);
        startActivity(i);
    }


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klubowe);

        Button b = (Button) findViewById(R.id.button10);
        View.OnClickListener l = new View.OnClickListener() {
            public void onClick(View v) {
                reakcja();
            }
        };
        b.setOnClickListener(l);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (groupPosition)
                {
                    case 0:
                        switch (childPosition)
                        {
                            case 0:
                                reakcja1();
                                break;
                            case 1:
                                reakcja2();
                                break;
                            case 2:
                                reakcja3();
                                break;
                            case 3:
                                reakcja4();
                                break;
                        }
                }
                return false;
            }

        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Klub");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Kazik");
        top250.add("Shine");
        top250.add("Czekolada");
        top250.add("Silence");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data

    }
}


