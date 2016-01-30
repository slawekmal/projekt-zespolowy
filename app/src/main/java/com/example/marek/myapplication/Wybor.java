package com.example.marek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.marek.myapplication.baza.DatabaseManager;
import com.example.marek.myapplication.baza.Miasto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Wybor extends AppCompatActivity {

    DatabaseManager dm = MainActivity.getInstance().dm;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public void reakcja() {
        Intent i = new Intent(this, Wybor.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                reakcja();
                                break;
                        }
                }
                return false;
            }

        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Miasto");
        listDataHeader.add("Relacje");
        listDataHeader.add("Pomoc");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        Set<Miasto> miasta = dm.getWszystkieMiasta();
        for (Miasto miasto : miasta) {
            top250.add(miasto.getNazwa());
        }


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Zdjęcie");
        nowShowing.add("Wideo");
        nowShowing.add("Dyktafon");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Mpk");
        comingSoon.add("Taxi");
        comingSoon.add("costam");


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

}

