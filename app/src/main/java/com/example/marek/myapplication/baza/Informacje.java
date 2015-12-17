package com.example.marek.myapplication.baza;

/**
 * Created by SKM on 2015-12-11.
 */
public class Informacje {
    private int id;
    private Miasto miasto;
    private String mpk;
    private String taxi;

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

    public String getMpk() {
        return mpk;
    }

    public void setMpk(String mpk) {
        this.mpk = mpk;
    }

    public String getTaxi() {
        return taxi;
    }

    public void setTaxi(String taxi) {
        this.taxi = taxi;
    }
}
