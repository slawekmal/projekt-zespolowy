package com.example.marek.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.marek.myapplication.baza.DatabaseManager;
import com.example.marek.myapplication.baza.Miasto;


public class MainActivity extends Activity {

    private static MainActivity instancja = null;

    public static synchronized MainActivity getInstance() {
        if (instancja == null) {
            instancja = new MainActivity();
            return instancja;
        }
        return instancja;
    }


    public void reakcja() {
        Intent i = new Intent(this, Wybor.class);
        startActivity(i);
    }

    DatabaseManager dm;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancja = new MainActivity();
        initDatabase();
        Button b = (Button) findViewById(R.id.button1);
        OnClickListener l = new OnClickListener() {
            public void onClick(View v) {
                reakcja();
            }
        };
        b.setOnClickListener(l);

        // get the listview
        getInstance().expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        getInstance().prepareListData();

        getInstance().listAdapter = new ExpandableListAdapter(this, getInstance().listDataHeader, getInstance().listDataChild);

        // setting list adapter
        getInstance().expListView.setAdapter(getInstance().listAdapter);

        getInstance().expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

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
        getInstance().listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        getInstance().listDataHeader.add("Miasto");
        getInstance().listDataHeader.add("Relacje");
        getInstance().listDataHeader.add("Pomoc");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        Set<Miasto> miasta = getInstance().dm.getWszystkieMiasta();
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


        getInstance().listDataChild.put(getInstance().listDataHeader.get(0), top250); // Header, Child data
        getInstance().listDataChild.put(getInstance().listDataHeader.get(1), nowShowing);
        getInstance().listDataChild.put(getInstance().listDataHeader.get(2), comingSoon);
    }

    public void initDatabase(){
        getInstance().dm = new DatabaseManager(this);
    }


}