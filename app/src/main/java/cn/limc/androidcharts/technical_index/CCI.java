package cn.limc.androidcharts.technical_index;

/**
 * Created by thinking on 2016/6/10.
 */
public class CCI {

    private double MA;
    private double high;
    private double low;
    private double close;
    private double closeNub;
    private double num;
    private double allNum = high+low+close;
    private int n;
    private double TYP = (high+low+close)/3;;

    public double getTYP(double high,double low,double close){
        return (high+low+close)/3;
    }
/**
 * 第一种计算过程如下：
 　　CCI(N日)=(TP-MA)÷MD÷0.015
 　　其中，TP=(最高价+最低价+收盘价)÷3
 　　MA=最近N日收盘价的累计之和÷N
 　　MD=最近N日(MA-收盘价)的累计之和÷N
 　　0.015为计算系数，N为计算周期
 */

  /*  MA14天,n=14
    }*/
    public double getMA(double TYP,int n){
        return TYP/n;
    }

    public double getCCI(int n,double num,double allNum,double close){
        //14天累计和=allNum
        return (200*n*num-600*allNum)/(9-9*n*close*allNum);
    }



}
