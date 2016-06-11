package cn.limc.androidcharts.technical_index;

/**
 * Created by thinking on 2016/6/10.
 * 其中，α为平滑指数，一般取作2/(N+1)。在计算MACD指标时，EMA计算中的N一般选取12
 * 和26天，因此α相应为2/13和2/27。①12日EMA的计算：EMA12=(前一日EMA12×11/13+今日收盘价×2/13)。
 */
public class MACD {
    private double DIFF;
    private double DEA;
    private double MACD_data;
    private double closePrice;//当前价格
    private double yesterdayPrice; //昨天收盘价
    private double yesterdayEMA12;
    private double yesterdayEMA26;
    private double EMA;
    private double a;
    private int n;//天数

    //EMA12算法,首次计算EMA12算法
    public double getEMADay(int day){
        a=2/(day+1);
        //EMA = qianTianPrice+(closePrice-qianTianPrice)*a;
        EMA = closePrice*a+yesterdayPrice*(day-1)/(day+1);
        return EMA;
    }

    //EAM算法，第三天以后的算法
    public double getEMAData(int day,double yesterdayEMA){
        a=2/(day+1);
        EMA = closePrice*a+yesterdayEMA*11/13;
        return EMA;
    }

    //DIFF算法
    public double getDIFF(){
        return getEMADay(12)-getEMADay(26);
    }

    //DEA算法
    public double getDEA(){
        return DEA=DIFF*2/10 + 0*8/10;
    }

    //BAR算法
    public double getBAR(){
        return (DIFF-DEA)*2;
    }


}
