package com.example.marek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.marek.myapplication.baza.Miasto;
import com.example.marek.myapplication.baza.Miejsce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class WybierzMiejsce extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private Miejsce miejsce;
    private Miasto miasto;
    EditText etMiejsceNazwa;
    EditText etMiejsceAdres;
    EditText etMiejsceWspolrzedne;
    private int miasto_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybierz_miejsce);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle od = getIntent().getExtras();
        miasto_id = od.getInt("miasto_id");
        Log.v("miasto_id: ", String.valueOf(miasto_id));
        this.miasto = MainActivity.getInstance().dm.getMiasto(miasto_id);
        Log.v("miasto: ", miasto.getNazwa());

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.eLVM);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                miejsce = MainActivity.getInstance().dm.getMiejsce(childPosition + 1);
                doWydarzenia();
                return false;
            }

        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Pokaż dostępne miejsca");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        Set<Miejsce> miejsca = MainActivity.getInstance().dm.getWszystkieMiejsca();
        for (Miejsce miejsce : miejsca) {
            if(miejsce.getMiasto().getId()==this.miasto_id) {
                top250.add(miejsce.getNazwa());
                Log.v("miejsceId", String.valueOf(miejsce.getId()));
            }
        }
        Collections.sort(top250);

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
    }

    public void dalej(View view){
        doWydarzenia();
    }

    public void doWydarzenia(){
        Intent intent = new Intent(this, NoweWydarzenie.class);
        int[] miasto_miejsce = new int[2];
        miasto_miejsce[0]=this.miasto.getId();
        miasto_miejsce[1]=this.miejsce.getId();
        intent.putExtra("miasto_miejsce",miasto_miejsce);
        startActivity(intent);
    }

    public void dodaj(View view){

        etMiejsceNazwa = (EditText) findViewById(R.id.eMiejsceNazwa);
        etMiejsceAdres = (EditText) findViewById(R.id.eMiejsceAdres);
        etMiejsceWspolrzedne = (EditText) findViewById(R.id.eMiejsceWspolrzedne);

        for (Miejsce i: MainActivity.getInstance().dm.getWszystkieMiejsca()) {
            if(i.getNazwa().equalsIgnoreCase(this.etMiejsceNazwa.getText().toString()))
                this.miejsce = i;
            else
            {
                this.miejsce = new Miejsce();
                this.miejsce.setNazwa(this.etMiejsceNazwa.getText().toString());
                this.miejsce.setAdres(this.etMiejsceAdres.getText().toString());
                this.miejsce.setWspolrzedne((this.etMiejsceWspolrzedne.getText().toString()));
                this.miejsce.setMiasto(this.miasto);
                MainActivity.getInstance().dm.addMiejsce(this.miejsce);
                this.miejsce = MainActivity.getInstance().dm.getMiejsceByName(this.miejsce.getNazwa());
                Toast.makeText(this,"Dodałeś nowe miejsce: " + miejsce.getNazwa(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
