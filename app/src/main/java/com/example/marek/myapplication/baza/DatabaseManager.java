package com.example.marek.myapplication.baza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SKM on 2015-12-12.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    private static final String createMiasto = "create table miasto(id integer primary key autoincrement, nazwa text not null);";
    private static final String createMiejsce = "create table miejsce(id integer primary key autoincrement, miasto_id integer not null, nazwa text not null, adres text, wspolrzedne text,  foreign key(miasto_id) references miasto(id));";
    private static final String createWydarzenie = "create table wydarzenie(id integer primary key autoincrement, miejsce_id integer not null, rodzaj_id integer not null, nazwa text not null, data date,  foreign key(miejsce_id) references miejsce(id), foreign key(rodzaj_id) references rodzaj_wydarzenia(id));";
    private static final String createRodzaj_wydarzenia = "create table rodzaj_wydarzenia(id integer primary key autoincrement, rodzaj text not null);";
    private static final String createInformacje = "create table informacje(id integer primary key autoincrement, miasto_id integer not null, mpk text, taxi text, foreign key(miasto_id) references miasto(id));";
    private static final String dropMiasto = "drop table miasto;";
    private static final String dropMiejsce = "drop table miejsce;";
    private static final String dropWydarzenie = "drop table wydarzenie;";
    private static final String dropRodzaj_wydarzenia = "drop table rodzaj_wydzarzenia;";
    private static final String dropInformacje = "drop table informacje;";
    private static final String insertLublin = "insert into miasto(nazwa) values('Lublin');";
    private static final String insertWarszawa = "insert into miasto(nazwa) values('Warszawa');";
    private static final String insertKlubowe = "insert into rodzaj_wydarzenia(rodzaj) values('Klubowe');";
    private static final String insertSportowe = "insert into rodzaj_wydarzenia(rodzaj) values('Sportowe');";
    private static final String insertKulturalne =" insert into rodzaj_wydarzenia(rodzaj) values('Kulturalne');" ;
    private static final String insertInne = " insert into rodzaj_wydarzenia(rodzaj) values('Inne');";
    private static final String insertArenaLublin = "insert into miejsce(miasto_id, nazwa, adres, wspolrzedne) values(1,'ArenaLublin','Stadionowa1','51.2323839,22.5575203');";
    public DatabaseManager(Context context) {
        super(context, "imprezy.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createMiasto);
        db.execSQL(createMiejsce);
        db.execSQL(createRodzaj_wydarzenia);
        db.execSQL(createWydarzenie);
        db.execSQL(createInformacje);
        db.execSQL(insertLublin);
        //db.execSQL(insertWarszawa);
        db.execSQL(insertKlubowe);
        db.execSQL(insertSportowe);
        db.execSQL(insertKulturalne);
        db.execSQL(insertInne);
        db.execSQL(insertArenaLublin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropInformacje);
        db.execSQL(dropWydarzenie);
        db.execSQL(dropRodzaj_wydarzenia);
        db.execSQL(dropMiejsce);
        db.execSQL(dropMiasto);
        db.execSQL(createMiasto);
        db.execSQL(createMiejsce);
        db.execSQL(createRodzaj_wydarzenia);
        db.execSQL(createWydarzenie);
        db.execSQL(createInformacje);
        db.execSQL(insertLublin);
        db.execSQL(insertWarszawa);
    }

    public void addMiasto(Miasto miasto){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("nazwa", miasto.getNazwa());
        db.insertOrThrow("miasto", null, wartosci);
    }

    public void addMiejsce(Miejsce miejsce){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("nazwa", miejsce.getNazwa());
        wartosci.put("miasto_id", miejsce.getMiasto().getId());
        wartosci.put("adres", miejsce.getAdres());
        wartosci.put("wspolrzedne", miejsce.getWspolrzedne());
        db.insertOrThrow("miejsce", null, wartosci);
    }

    public void addInformacje(Informacje informacje){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("miasto_id", informacje.getMiasto().getId());
        wartosci.put("mpk", informacje.getMpk());
        wartosci.put("taxi", informacje.getTaxi());
        db.insertOrThrow("informacje", null, wartosci);
    }

    public void addWydarzenie(Wydarzenie wydarzenie){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("nazwa", wydarzenie.getNazwa());
        wartosci.put("miejsce_id", wydarzenie.getMiejsce().getId());
        wartosci.put("rodzaj_id", wydarzenie.getRodzaj().getId());
        wartosci.put("data", wydarzenie.getData().getDate());
        db.insertOrThrow("wydarzenie", null, wartosci);
    }

    public void addRodzaj_wydarzenia(RodzajWydarzenia rodzajWyd){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("rodzaj", rodzajWyd.getRodzaj());
        db.insertOrThrow("rodzaj_wydarzenia", null, wartosci);
    }

    public void updateMiasto(Miasto miasto){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("nazwa", miasto.getNazwa());
        String args[]={miasto.getId()+""};
        db.update("miasto", wartosci, "id=?", args);
    }

    public void updateMiejsce(Miejsce miejsce){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("nazwa", miejsce.getNazwa());
        wartosci.put("miasto_id", miejsce.getMiasto().getId());
        wartosci.put("adres", miejsce.getAdres());
        wartosci.put("wspolrzedne", miejsce.getWspolrzedne());
        String args[]={miejsce.getId()+""};
        db.update("miejsce", wartosci, "id=?", args);
    }

    public void updateInformacje(Informacje informacje){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("miasto_id", informacje.getMiasto().getId());
        wartosci.put("mpk", informacje.getMpk());
        wartosci.put("taxi", informacje.getTaxi());
        String args[]={informacje.getId()+""};
        db.update("informacje", wartosci, "id=?", args);
    }

    public void updateWydarzenie(Wydarzenie wydarzenie){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("nazwa", wydarzenie.getNazwa());
        wartosci.put("miejsce_id", wydarzenie.getMiejsce().getId());
        wartosci.put("rodzaj_id", wydarzenie.getRodzaj().getId());
        wartosci.put("data", wydarzenie.getData().getDate());
        String args[]={wydarzenie.getId()+""};
        db.update("wydarzenie", wartosci, "id=?", args);
    }

    public void updateRodzaj_wydarzenia(RodzajWydarzenia rodzajWyd){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("rodzaj", rodzajWyd.getRodzaj());
        String args[]={rodzajWyd.getId()+""};
        db.update("rodzaj_wydarzenia", wartosci, "id=?", args);
    }

    public void deleteMiasto(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args={id+""};
        db.delete("miasto", "id=?", args);
    }

    public void deleteMiejsce(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args={id+""};
        db.delete("miejsce", "id=?", args);
    }

    public void deleteWydarzenie(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args={id+""};
        db.delete("wydarzenie", "id=?", args);
    }

    public void deleteRodzaj_wydarzenia(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args={id+""};
        db.delete("rodzaj_wydarzenia", "id=?", args);
    }

    public void deleteInformacje(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args={id+""};
        db.delete("informacje", "id=?", args);
    }

    public Set<Miasto> getWszystkieMiasta(){
        Set<Miasto> miasta = new HashSet<Miasto>();
        String[] kolumny={"id","nazwa"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("miasto",kolumny,null,null,null,null,null);
        while(kursor.moveToNext()){
            Miasto miasto = new Miasto();
            miasto.setId(kursor.getInt(0));
            miasto.setNazwa(kursor.getString(1));
            miasta.add(miasto);
        }
        return miasta;
    }

    public Set<Miejsce> getWszystkieMiejsca(){
        Set<Miejsce> miejsca = new HashSet<Miejsce>();
        String[] kolumny={"id","miasto_id","nazwa","adres","wspolrzedne"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("miejsce",kolumny,null,null,null,null,null);
        while(kursor.moveToNext()){
            Miejsce miejsce = new Miejsce();
            miejsce.setId(kursor.getInt(0));
            miejsce.setMiasto(getMiasto(kursor.getInt(1)));
            miejsce.setNazwa(kursor.getString(2));
            miejsce.setAdres(kursor.getString(3));
            miejsce.setWspolrzedne(kursor.getString(4));
            miejsca.add(miejsce);
        }
        return miejsca;
    }

    public Set<Wydarzenie> getWszystkieWydarzenia(){
        Set<Wydarzenie> wydarzenia = new HashSet<Wydarzenie>();
        String[] kolumny={"id","miejsce_id","rodzaj_id","nazwa","data"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("wydarzenie",kolumny,null,null,null,null,null);
        while(kursor.moveToNext()) {
            Wydarzenie wydarzenie = new Wydarzenie();
            wydarzenie.setId(kursor.getInt(0));
            wydarzenie.setMiejsce(getMiejsce(kursor.getInt(1)));
            wydarzenie.setRodzaj(getRodzajWydarzenia(kursor.getInt(2)));
            wydarzenie.setNazwa(kursor.getString(3));
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                wydarzenie.setData(format.parse(kursor.getString(4)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            wydarzenia.add(wydarzenie);
        }
        return wydarzenia;
    }

    public Set<RodzajWydarzenia> getWszystkieRodzajeWydarzen(){
        Set<RodzajWydarzenia> rodzajeWyd = new HashSet<RodzajWydarzenia>();
        String[] kolumny={"id","rodzaj"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("rodzaj_wydarzenia",kolumny,null,null,null,null,null);
        while(kursor.moveToNext()){
            RodzajWydarzenia rodzaj = new RodzajWydarzenia();
            rodzaj.setId(kursor.getInt(0));
            rodzaj.setRodzaj(kursor.getString(1));
            rodzajeWyd.add(rodzaj);
        }
        return rodzajeWyd;
    }

    public Set<Informacje> getWszystkieInformacje(){
        Set<Informacje> informacje = new HashSet<Informacje>();
        String[] kolumny={"id","miasto_id","mpk","taxi"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("informacje",kolumny,null,null,null,null,null);
        while(kursor.moveToNext()){
            Informacje info = new Informacje();
            info.setId(kursor.getInt(0));
            info.setMiasto(getMiasto(kursor.getInt(1)));
            informacje.add(info);
        }
        return informacje;
    }

    public Miasto getMiasto(int id){
        Miasto miasto=new Miasto();
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny={"id","nazwa"};
        String args[]={id+""};
        Cursor kursor=db.query("miasto",kolumny,"id=?",args,null,null,null,null);
        if(kursor!=null){
            kursor.moveToFirst();
            miasto.setId(kursor.getInt(0));
            miasto.setNazwa(kursor.getString(1));
        }
        return miasto;
    }

    public Miejsce getMiejsce(int id){
        Miejsce miejsce=new Miejsce();
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny={"id","miasto_id","nazwa","adres","wspolrzedne"};
        String args[]={id+""};
        Cursor kursor=db.query("wydarzenie",kolumny,"id=?",args,null,null,null,null);
        if(kursor!=null){
            kursor.moveToFirst();
            miejsce.setId(kursor.getInt(0));
            miejsce.setMiasto(getMiasto(kursor.getInt(1)));
            miejsce.setNazwa(kursor.getString(2));
            miejsce.setAdres(kursor.getString(3));
            miejsce.setWspolrzedne(kursor.getString(4));
        }
        return miejsce;
    }

    public Wydarzenie getWydarzenie(int id){
        Wydarzenie wydarzenie=new Wydarzenie();
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny={"id","miejsce_id","rodzaj_id","nazwa","data"};
        String args[]={id+""};
        Cursor kursor=db.query("miasto",kolumny,"id=?",args,null,null,null,null);
        if(kursor!=null){
            kursor.moveToFirst();
            wydarzenie.setId(kursor.getInt(0));
            wydarzenie.setMiejsce(getMiejsce(kursor.getInt(1)));
            wydarzenie.setRodzaj(getRodzajWydarzenia(kursor.getInt(2)));
            wydarzenie.setNazwa(kursor.getString(3));
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                wydarzenie.setData(format.parse(kursor.getString(4)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return wydarzenie;
    }

    public RodzajWydarzenia getRodzajWydarzenia(int id){
        RodzajWydarzenia rodzajWyd=new RodzajWydarzenia();
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny={"id","rodzaj"};
        String args[]={id+""};
        Cursor kursor=db.query("rodzaj_wydarzenia",kolumny,"id=?",args,null,null,null,null);
        if(kursor!=null){
            kursor.moveToFirst();
            rodzajWyd.setId(kursor.getInt(0));
            rodzajWyd.setRodzaj(kursor.getString(1));
        }
        return rodzajWyd;
    }

    public Informacje getInformacje(int id){
        Informacje informacje=new Informacje();
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny={"id","miasto_id","mpk","taxi"};
        String args[]={id+""};
        Cursor kursor=db.query("informacje",kolumny,"id=?",args,null,null,null,null);
        if(kursor!=null){
            kursor.moveToFirst();
            informacje.setId(kursor.getInt(0));
            informacje.setMiasto(getMiasto(kursor.getInt(1)));
            informacje.setMpk(kursor.getString(2));
            informacje.setTaxi(kursor.getString(3));
        }
        return informacje;
    }
}