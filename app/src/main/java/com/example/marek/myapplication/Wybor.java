package com.example.marek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Wybor extends AppCompatActivity {

    public void reakcja1(){
        Intent i = new Intent(this,Klubowe.class);
        startActivity(i);
    }
    public void reakcja2(){
        Intent i = new Intent(this,Kulturalne.class);
        startActivity(i);
    }
    public void reakcja3(){
        Intent i = new Intent(this,Inne.class);
        startActivity(i);
    }
    public void reakcja4(){
        Intent i = new Intent(this,Sportowe.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button b1 = (Button) findViewById(R.id.button2);
        View.OnClickListener l1 = new View.OnClickListener() {
            public void onClick(View v) {
                reakcja1();
            }
        };
        b1.setOnClickListener(l1);

        Button b2 = (Button) findViewById(R.id.button3);
        View.OnClickListener l2 = new View.OnClickListener() {
            public void onClick(View v) {
                reakcja2();
            }
        };
        b2.setOnClickListener(l2);

        Button b3 = (Button) findViewById(R.id.button5);
        View.OnClickListener l3 = new View.OnClickListener() {
            public void onClick(View v) {
                reakcja3();
            }
        };
        b3.setOnClickListener(l3);

        Button b4 = (Button) findViewById(R.id.button4);
        View.OnClickListener l4 = new View.OnClickListener() {
            public void onClick(View v) {
                reakcja4();
            }
        };
        b4.setOnClickListener(l4);

        Button b5 = (Button) findViewById(R.id.button4);
        View.OnClickListener l5 = new View.OnClickListener() {
            public void onClick(View v) {
                reakcja4();
            }
        };
        b5.setOnClickListener(l5);

    }

}
