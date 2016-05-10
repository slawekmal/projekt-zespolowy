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
    private EditText etRodzaj;
    private EditText etNazwa;
    private EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowe_wydarzenie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle od = getIntent().getExtras();
        miasto = MainActivity.getInstance().dm.getMiasto(od.getIntArray("miasto_miejsce")[0]);
        miejsce = MainActivity.getInstance().dm.getMiejsce(od.getIntArray("miasto_miejsce")[1]);
    }

    public void dodaj(View view){

        etRodzaj = (EditText) findViewById(R.id.editRodzaj);
        etNazwa = (EditText) findViewById(R.id.editNazwa);
        etData = (EditText) findViewById(R.id.editData);

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
            Toast.makeText(this,"Błąd parsowania daty", Toast.LENGTH_SHORT).show();
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
