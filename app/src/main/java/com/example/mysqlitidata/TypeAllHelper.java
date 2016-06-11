package com.example.mysqlitidata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thinking on 2016/6/8.
 */
public class TypeAllHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "typealldata.db";
    private static final int DBVERSION = 1;

    public TypeAllHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table day1m(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day3m(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day5m(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day10m(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day15m(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day30m(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day1h(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day1d(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day1w(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table day1M(_id integer primary key autoincrement,date varchar(20),opening double,max double,min double,closing double,turnover double)");
        db.execSQL("create table allMD(_id integer primary key autoincrement,date varchar(20),MDdata double,k double,d double)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
