package com.example.marek.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.marek.myapplication.baza.Miasto;
import com.example.marek.myapplication.baza.RodzajWydarzenia;

public class Miejsce extends AppCompatActivity {

    ListView lvMiejsca;
    Miasto miasto;
    RodzajWydarzenia rodzaj;
    int[] miasto_rodzaj = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miejsce);
        Bundle od = getIntent().getExtras();
        miasto_rodzaj = od.getIntArray("miasto_rodzaj");
        miasto = MainActivity.getInstance().dm.getMiasto(miasto_rodzaj[0]);
        rodzaj = MainActivity.getInstance().dm.getRodzajWydarzenia(miasto_rodzaj[1]);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvMiejsca = (ListView) findViewById(R.id.listView);
        initLvMiejsca();
    }

    public void initLvMiejsca(){
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        for(com.example.marek.myapplication.baza.Miejsce miejsce : MainActivity.getInstance().dm.getWszystkieMiejsca()){
            if(miejsce.getMiasto().getId() == miasto.getId())
                adapter.add(miejsce.getNazwa());
        }
        lvMiejsca.setAdapter(adapter);
    }
}
