package com.example.marek.myapplication.baza;

import java.util.Set;

/**
 * Created by SKM on 2015-12-11.
 */
public class Miasto {
    private int id;
    private String nazwa;
    private Set<Miejsce> miejsca;
    private Set<Informacje> informacje;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Set<Miejsce> getMiejsca() {
        return miejsca;
    }

    public void setMiejsca(Set<Miejsce> miejsca) {
        this.miejsca = miejsca;
    }

    public Set<Informacje> getInformacje() {
        return informacje;
    }

    public void setInformacje(Set<Informacje> informacje) {
        this.informacje = informacje;
    }
}
