package com.example.marek.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marek.myapplication.baza.Miasto;
import com.example.marek.myapplication.baza.Miejsce;
import com.example.marek.myapplication.baza.RodzajWydarzenia;
import com.example.marek.myapplication.baza.Wydarzenie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoweWydarzenie extends AppCompatActivity {

    private Miasto miasto;
    private Miejsce miejsce;
    private RodzajWydarzenia rodzaj;
    private Wydarzenie wydarzenie;
    private EditText etMiasto;
    private EditText etMiejsce;
    private EditText etRodzaj;
    private EditText etNazwa;
    private EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowe_wydarzenie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void dodaj(View view){

        etMiasto = (EditText) findViewById(R.id.editMiasto);
        etMiejsce = (EditText) findViewById(R.id.editMiejsce);
        etRodzaj = (EditText) findViewById(R.id.editRodzaj);
        etNazwa = (EditText) findViewById(R.id.editNazwa);
        etData = (EditText) findViewById(R.id.editData);

        for (Miasto i: MainActivity.getInstance().dm.getWszystkieMiasta()) {
            if(i.getNazwa().equalsIgnoreCase(this.etMiasto.getText().toString()))
                this.miasto = i;
            else
            {
                this.miasto = new Miasto();
                this.miasto.setNazwa(this.etMiasto.getText().toString());
                MainActivity.getInstance().dm.addMiasto(this.miasto);
            }
        }
        for (Miejsce i: MainActivity.getInstance().dm.getWszystkieMiejsca()) {
            if(i.getNazwa().equalsIgnoreCase(this.etMiejsce.getText().toString()) && i.getMiasto().equals(this.miasto))
                this.miejsce = i;
            else
            {
                this.miejsce = new Miejsce();
                this.miejsce.setNazwa(this.etMiejsce.getText().toString());
                this.miejsce.setMiasto(this.miasto);
                MainActivity.getInstance().dm.addMiejsce(this.miejsce);
            }
        }
        for (RodzajWydarzenia i: MainActivity.getInstance().dm.getWszystkieRodzajeWydarzen()) {
            if(i.getRodzaj().equalsIgnoreCase(this.etRodzaj.getText().toString()))
                this.rodzaj = i;
        }
        this.wydarzenie = new Wydarzenie();
        this.wydarzenie.setRodzaj(this.rodzaj);
        this.wydarzenie.setNazwa(this.etNazwa.getText().toString());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.wydarzenie.setData(format.parse(this.etData.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.wydarzenie.setMiejsce(this.miejsce);
        try{
            MainActivity.getInstance().dm.addWydarzenie(this.wydarzenie);
        } catch(Exception e){
            Toast.makeText(this,"Błąd", Toast.LENGTH_SHORT).show();
        }
    }

}
