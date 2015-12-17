package com.example.marek.myapplication.baza;

import java.util.Date;

/**
 * Created by SKM on 2015-12-11.
 */
public class Wydarzenie {
    private int id;
    private Miejsce miejsce;
    private RodzajWydarzenia rodzaj;
    private String nazwa;
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Miejsce getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(Miejsce miejsce) {
        this.miejsce = miejsce;
    }

    public RodzajWydarzenia getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(RodzajWydarzenia rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
