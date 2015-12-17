package com.example.marek.myapplication.baza;

import java.util.Set;

/**
 * Created by SKM on 2015-12-11.
 */
public class RodzajWydarzenia {
    private int id;
    private String rodzaj;
    private Set<Wydarzenie> wydarzenia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public Set<Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    public void setWydarzenia(Set<Wydarzenie> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }
}
