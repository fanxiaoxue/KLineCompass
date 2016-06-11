package com.example.vo;

import java.io.Serializable;

/**
 * Created by admin on 2016/6/10.
 * 由于MyMainInfo属性不满足需求特创此类已做测试
 * 用于做TypeAllHelper测试表中不同时间数据
 */
public class KTypeAllData implements Serializable{
    private int Id;
    //日期
    private String Date;
    //开盘
    private double Opening;
    //最高
    private double Max;
    //最低
    private double Min;
    //收盘
    private double Closing;
    //成交量
    private double TurnOver;

    public KTypeAllData() {
    }

    public KTypeAllData(int id, String date, double opening, double max, double min, double closing, double turnOver) {
        Id = id;
        Date = date;
        Opening = opening;
        Max = max;
        Min = min;
        Closing = closing;
        TurnOver = turnOver;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getOpening() {
        return Opening;
    }

    public void setOpening(double opening) {
        Opening = opening;
    }

    public double getMax() {
        return Max;
    }

    public void setMax(double max) {
        Max = max;
    }

    public double getMin() {
        return Min;
    }

    public void setMin(double min) {
        Min = min;
    }

    public double getClosing() {
        return Closing;
    }

    public void setClosing(double closing) {
        Closing = closing;
    }

    public double getTurnOver() {
        return TurnOver;
    }

    public void setTurnOver(double turnOver) {
        TurnOver = turnOver;
    }

    @Override
    public String toString() {
        return "KTypeAllData{" +
                "Id=" + Id +
                ", Date='" + Date + '\'' +
                ", Opening=" + Opening +
                ", Max=" + Max +
                ", Min=" + Min +
                ", Closing=" + Closing +
                ", TurnOver=" + TurnOver +
                '}';
    }
}
