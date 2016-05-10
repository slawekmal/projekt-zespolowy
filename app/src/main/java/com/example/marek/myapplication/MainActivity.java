package com.example.marek.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.marek.myapplication.baza.DatabaseManager;
import com.example.marek.myapplication.baza.Miasto;
import com.example.marek.myapplication.baza.RodzajWydarzenia;


public class MainActivity extends Activity {

    private static MainActivity instancja = null;

    public static synchronized MainActivity getInstance() {
        if (instancja == null) {
            instancja = new MainActivity();
            return instancja;
        }
        return instancja;
    }


    DatabaseManager dm;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Dialog listDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancja = new MainActivity();
        initDatabase();

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
                                wyswietlRodzaje(childPosition+1);
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
        getInstance().listDataHeader.add("Pokaż dostępne miasta");
        getInstance().listDataHeader.add("Dodaj relację z wydarzenia");
        getInstance().listDataHeader.add("Przydatne informacje");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        Set<Miasto> miasta = getInstance().dm.getWszystkieMiasta();
        for (Miasto miasto : miasta) {
            top250.add(miasto.getNazwa());
            Log.v("miastoId",String.valueOf(miasto.getId()));
        }


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Dodaj Zdjęcie");
        nowShowing.add("Nagraj Wideo");
        nowShowing.add("Nagraj Dźwięk");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("MPK");
        comingSoon.add("Taxi");


        getInstance().listDataChild.put(getInstance().listDataHeader.get(0), top250); // Header, Child data
        getInstance().listDataChild.put(getInstance().listDataHeader.get(1), nowShowing);
        getInstance().listDataChild.put(getInstance().listDataHeader.get(2), comingSoon);
    }

    public void initDatabase(){
        getInstance().dm = new DatabaseManager(this);
    }

    public Dialog createDialog(final int miastoID) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        String[] options = new String[getInstance().dm.getWszystkieRodzajeWydarzen().size()];
        for (RodzajWydarzenia rw : getInstance().dm.getWszystkieRodzajeWydarzen()) {
            if (rw != null) {
                Log.v("RodzajeWydarzenwBazie",rw.getRodzaj());
                options[rw.getId() - 1] = rw.getRodzaj();
            }
        }
        dialogBuilder.setTitle("Rodzaj Wydarzenia");
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                Intent intent = new Intent(MainActivity.this, ShowWydarzenia.class);
                int[] miasto_rodzaj = new int[2];
                miasto_rodzaj[0]=miastoID;
                miasto_rodzaj[1]=position+1;
                intent.putExtra("miasto_rodzaj", miasto_rodzaj);
                startActivity(intent);
            }
        });
        return dialogBuilder.create();
    }
    public void wyswietlRodzaje(int miastoID) {
        if (getInstance().dm.getWszystkieRodzajeWydarzen().isEmpty()) {
            Toast.makeText(this, "Brak", Toast.LENGTH_LONG).show();
        } else {
            getInstance().listDialog = createDialog(miastoID);
            getInstance().listDialog.show();

        }
    }

    public void dodajWydarzenie(View view){
        Intent intent = new Intent(MainActivity.this, WybierzMiasto.class);
        startActivity(intent);
    }
}