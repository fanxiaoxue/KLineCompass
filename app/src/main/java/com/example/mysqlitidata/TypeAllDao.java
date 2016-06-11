package com.example.mysqlitidata;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.KLineApp;
import com.example.vo.KTypeAllData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/6/10.
 */
public class TypeAllDao {

    private SQLiteDatabase database;
    /**
     * 一分钟
     */
    public static final String DAY1M = "day1m";
    /**
     * 三分钟
     */
    public static final String DAY3M = "day3m";
    /**
     * 五分钟
     */
    public static final String DAY5M = "day5m";
    /**
     * 十分钟
     */
    public static final String DAY10M = "day10m";
    /**
     * 十五分钟
     */
    public static final String DAY15M = "day15m";
    /**
     * 半小时
     */
    public static final String DAY30M = "day30m";
    /**
     * 一小时
     */
    public static final String DAY1H = "day1h";
    /**
     * 一天
     */
    public static final String DAY1D = "day1d";
    /**
     * 一周
     */
    public static final String DAY1W = "day1w";
    /**
     * 一月
     */
    public static final String DAY1MON = "day1M";

    /**
     * CCI算法参数MD
     */
    public static final String MD = "allMD";

    /**
     * JDK算法参数Kt,Dt
     */
    public static final String K = "Kt";
    public static final String D = "Dt";


    public TypeAllDao(){
        database = KLineApp.getTypeDBHelper();
    }

    /**
     * 添加全部
     */
    public void addAllData(List<KTypeAllData> typeAllDatas,String dbname){
            for (KTypeAllData data:typeAllDatas) {
                database.execSQL("insert into"+dbname+"(date,opening,max,min,closing,turnover)values(?,?,?,?,?,?)", new Object[]{data.getDate(),data.getOpening(),data.getMax(),data.getMin(),data.getClosing(),data.getTurnOver()});
            }
    };

    /**
     * 添加一条
     * @param data
     */
    public void addAllData(KTypeAllData data,String dbname){
        database.execSQL("insert into"+dbname+"(date,opening,max,min,closing,turnover)values(?,?,?,?,?,?)", new Object[]{data.getDate(),data.getOpening(),data.getMax(),data.getMin(),data.getClosing(),data.getTurnOver()});
    };

    /**
     * 获取全部数据
     * @return
     */
    public List<KTypeAllData> getAllData(String dbname){
        List<KTypeAllData> kTypeDatas = new ArrayList<KTypeAllData>();
        Cursor cursor = database.rawQuery("select * from"+dbname, null);
        try{
            while(cursor.moveToNext()){
                KTypeAllData kData = new KTypeAllData();
                kData.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                kData.setDate(cursor.getString(cursor.getColumnIndex("date")));
                kData.setOpening(cursor.getDouble(cursor.getColumnIndex("opening")));
                kData.setMax(cursor.getDouble(cursor.getColumnIndex("max")));
                kData.setMin(cursor.getDouble(cursor.getColumnIndex("min")));
                kData.setClosing(cursor.getDouble(cursor.getColumnIndex("closing")));
                kData.setTurnOver(cursor.getDouble(cursor.getColumnIndex("turnover")));
                kTypeDatas.add(kData);

            }
            return kTypeDatas;
        }catch (Exception e){
            return null;
        }finally {
            if(cursor!=null){
                cursor.close();

            }
        }

    };

    /**
     * 获取一条数据
     * @return
     */
    public KTypeAllData getData(String dbName){
        Cursor cursor = database.rawQuery("select * from"+dbName+"where ", null);
        KTypeAllData kData = new KTypeAllData();
        try{
            while(cursor.moveToNext()){
                kData.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                kData.setDate(cursor.getString(cursor.getColumnIndex("date")));
                kData.setOpening(cursor.getDouble(cursor.getColumnIndex("opening")));
                kData.setMax(cursor.getDouble(cursor.getColumnIndex("max")));
                kData.setMin(cursor.getDouble(cursor.getColumnIndex("min")));
                kData.setClosing(cursor.getDouble(cursor.getColumnIndex("closing")));
                kData.setTurnOver(cursor.getDouble(cursor.getColumnIndex("turnover")));
            }
            return kData;
        }catch (Exception e){
            return null;
        }finally {
            if(cursor!=null){
                cursor.close();

            }
        }

    };





}
