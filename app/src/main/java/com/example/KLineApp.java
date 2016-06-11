package com.example;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.mysqlitidata.TypeAllHelper;

/**
 * Created by admin on 2016/6/10.
 */
public class KLineApp extends Application {

    private static TypeAllHelper typeAllHelper = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库
        typeAllHelper = new TypeAllHelper(getApplicationContext());
    }

    public static SQLiteDatabase getTypeDBHelper(){

        return typeAllHelper.getWritableDatabase();
    }

}
