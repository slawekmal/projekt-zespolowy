package com.example.marek.myapplication.baza;

import java.util.Set;

/**
 * Created by SKM on 2015-12-11.
 */
public class Miejsce {
    private int id;
    private Miasto miasto;
    private String nazwa;
    private String adres;
    private String wspolrzedne;
    private Set<Wydarzenie> wydarzenia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Miasto getMiasto() {
        return miasto;
    }

    public void setMiasto(Miasto miasto) {
        this.miasto = miasto;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getWspolrzedne() {
        return wspolrzedne;
    }

    public void setWspolrzedne(String wspolrzedne) {
        this.wspolrzedne = wspolrzedne;
    }

    public Set<Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    public void setWydarzenia(Set<Wydarzenie> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }
}
