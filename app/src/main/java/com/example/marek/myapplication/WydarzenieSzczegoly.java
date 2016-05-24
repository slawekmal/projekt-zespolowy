package com.example.marek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.marek.myapplication.baza.Wydarzenie;

public class WydarzenieSzczegoly extends AppCompatActivity {

    private int wyd_id;
    private Wydarzenie wydarzenie;
    TextView nazwa;
    TextView miejsce;
    TextView data;
    Button mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wydarzenie_szczegoly);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle od = getIntent().getExtras();
        wyd_id = od.getInt("wyd_id");
        wydarzenie = MainActivity.getInstance().dm.getWydarzenie(wyd_id);
        nazwa = (TextView) findViewById(R.id.textView);
        nazwa.setText(wydarzenie.getNazwa());
        miejsce = (TextView) findViewById(R.id.textView7);
        miejsce.setText(wydarzenie.getMiejsce().getNazwa());
        data = (TextView) findViewById(R.id.textView8);
        data.setText(wydarzenie.getData().getTime().toString());
        mapa = (Button) findViewById(R.id.button);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WydarzenieSzczegoly.this, MapsActivity.class);
                intent.putExtra("wyd_id",wyd_id);
                startActivity(intent);
            }
        });
    }

}
