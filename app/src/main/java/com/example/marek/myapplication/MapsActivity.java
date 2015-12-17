package com.example.marek.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng Kazik = new LatLng(51.2351792, 22.5469426);
        map.addMarker(new MarkerOptions().position(Kazik).title("Klub Kazik"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Kazik));

        LatLng Silence = new LatLng(51.2464403, 22.5415827);
        map.addMarker(new MarkerOptions().position(Silence).title("Klub Silence"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Silence));

        LatLng Shine = new LatLng(51.2491831, 22.5507555);
        map.addMarker(new MarkerOptions().position(Shine).title("Klub Shine"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Shine));

        LatLng Czekolada = new LatLng(51.2470281, 22.5610082);
        map.addMarker(new MarkerOptions().position(Czekolada).title("Klub Czekolada"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Czekolada));
    }

}