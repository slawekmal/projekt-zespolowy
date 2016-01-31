package com.example.marek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marek.myapplication.baza.Miasto;
import com.example.marek.myapplication.baza.RodzajWydarzenia;
import com.example.marek.myapplication.baza.Wydarzenie;

public class ShowWydarzenia extends AppCompatActivity {

    ListView lvWydarzenia;
    Miasto miasto;
    RodzajWydarzenia rodzaj;
    int[] miasto_rodzaj = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wydarzenie);
        Bundle od = getIntent().getExtras();
        miasto_rodzaj = od.getIntArray("miasto_rodzaj");
        miasto = MainActivity.getInstance().dm.getMiasto(miasto_rodzaj[0]);
        rodzaj = MainActivity.getInstance().dm.getRodzajWydarzenia(miasto_rodzaj[1]);
        Log.v("wybrane miasto: ",miasto.getNazwa());
        Log.v("rodzaj wydarzenia: ", rodzaj.getRodzaj());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvWydarzenia = (ListView) findViewById(R.id.listView);
        initLvWydarzenia();
    }

    public void initLvWydarzenia(){
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        for(com.example.marek.myapplication.baza.Wydarzenie wydarzenie : MainActivity.getInstance().dm.getWszystkieWydarzenia()){
            if(wydarzenie.getRodzaj().getId() == rodzaj.getId() && wydarzenie.getMiejsce().getMiasto().getId() == miasto.getId()) {
                adapter.add(wydarzenie);
                Log.v("Pasujące wydarzenia: ", wydarzenie.getNazwa());
            }
            Log.v("wydarzenia: ",wydarzenie.getNazwa());
        }
        lvWydarzenia.setAdapter(adapter);
        lvWydarzenia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object o = lvWydarzenia.getAdapter().getItem(position);
                Wydarzenie wydarzenie=(Wydarzenie)o;//As you are using Default String Adapter
                //Toast.makeText(getBaseContext(), wydarzenie.getData().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowWydarzenia.this, WydarzenieSzczegoly.class);
                intent.putExtra("wyd_id", wydarzenie.getId());
                startActivity(intent);
            }
        });
    }


}
