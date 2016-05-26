package com.example.marek.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.marek.myapplication.baza.Miasto;
import com.example.marek.myapplication.baza.Miejsce;
import com.example.marek.myapplication.baza.RodzajWydarzenia;
import com.example.marek.myapplication.baza.Wydarzenie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoweWydarzenie extends AppCompatActivity {

    private Miasto miasto;
    private Miejsce miejsce;
    private RodzajWydarzenia rodzaj;
    private Wydarzenie wydarzenie;
    private RadioButton radioRodzaj;
    private EditText etNazwa;
    private DatePicker dpData;
    private RadioGroup radioRodzajGroup;
    private Button btnDisplay;
    private TimePicker tpGodzina;

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

        radioRodzajGroup = (RadioGroup) findViewById(R.id.radioRodzaj);
        etNazwa = (EditText) findViewById(R.id.editNazwa);
        dpData = (DatePicker) findViewById(R.id.dpData);
        tpGodzina = (TimePicker) findViewById(R.id.tpGodzina);
        // get selected radio button from radioGroup
        int selectedId = radioRodzajGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioRodzaj = (RadioButton) findViewById(selectedId);

        for (RodzajWydarzenia i: MainActivity.getInstance().dm.getWszystkieRodzajeWydarzen()) {
            if(i.getRodzaj().equalsIgnoreCase(this.radioRodzaj.getText().toString()))
                this.rodzaj = i;
        }
        this.wydarzenie = new Wydarzenie();
        this.wydarzenie.setRodzaj(this.rodzaj);
        this.wydarzenie.setNazwa(this.etNazwa.getText().toString());
        try {
            this.wydarzenie.setData(getDateFromDatePicker(dpData, tpGodzina));
        } catch (Exception e) {
            Toast.makeText(this,"Błąd ustawienia daty", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        this.wydarzenie.setMiejsce(this.miejsce);
        try{
            MainActivity.getInstance().dm.addWydarzenie(this.wydarzenie);
        } catch(Exception e){
            Toast.makeText(this,"Błąd", Toast.LENGTH_SHORT).show();
        }

    }

    public static Calendar getDateFromDatePicker(DatePicker datePicker, TimePicker tpGodzina){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();
        int hour = tpGodzina.getCurrentHour();
        int min = tpGodzina.getCurrentMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, min);

        return calendar;
    }

}
