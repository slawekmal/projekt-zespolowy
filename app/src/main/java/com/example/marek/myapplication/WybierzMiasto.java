package com.example.marek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.marek.myapplication.baza.Miasto;
import com.example.marek.myapplication.baza.Miejsce;
import com.example.marek.myapplication.baza.RodzajWydarzenia;
import com.example.marek.myapplication.baza.Wydarzenie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class WybierzMiasto extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private Miasto miasto;
    EditText etMiasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybierz_miasto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.eLV);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

               miasto = MainActivity.getInstance().dm.getMiasto(childPosition+1);
               doMiejsca();
                return false;
            }

        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Pokaż dostępne miasta");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        Set<Miasto> miasta = MainActivity.getInstance().dm.getWszystkieMiasta();
        for (Miasto miasto : miasta) {
            top250.add(miasto.getNazwa());
            Log.v("miastoId", String.valueOf(miasto.getId()));
        }
        Collections.sort(top250);

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
    }

    public void dalej(View view){
        doMiejsca();
    }

    public void doMiejsca(){
        Intent intent = new Intent(this, WybierzMiejsce.class);
        intent.putExtra("miasto_id", this.miasto.getId());
        startActivity(intent);
    }

    public void dodaj(View view){

        etMiasto = (EditText) findViewById(R.id.eMiasto);

        for (Miasto i: MainActivity.getInstance().dm.getWszystkieMiasta()) {
            if(i.getNazwa().equalsIgnoreCase(this.etMiasto.getText().toString()))
                this.miasto = i;
            else
            {
                this.miasto = new Miasto();
                this.miasto.setNazwa(this.etMiasto.getText().toString());
                MainActivity.getInstance().dm.addMiasto(this.miasto);
                this.miasto = MainActivity.getInstance().dm.getMiastoByName(this.miasto.getNazwa());
                Toast.makeText(this,"Dodałeś nowe miasto: " + miasto.getNazwa(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
