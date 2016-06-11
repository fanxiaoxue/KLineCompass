package cn.limc.androidcharts.technical_index;

/**
 * Created by thinking on 2016/6/10.
 */
public class KDJ {

    private double L9;//L9-------9天内最低价
    private double H9; //H9-------9天内最高价
    private double Ct;  //Ct-------当日收盘价
    private double RSVt=(Ct-L9)/(H9-L9)*100;//未成熟随机值即RSV值
    private double Kt=50;//若无前一日K值与D值，则可以分别用50代替。
    private double Jt;
    private double Dt=50;
    private boolean isKt = true;
    private boolean isDt = true;

    public double getKt(){
        if(isKt){
            Kt = 50;
            isKt = false;
        }else {
            Kt = RSVt/3+2*Kt-1/3;
        }

        return Kt;
    }

    private double getJt(){

        return 3*Dt-2*Kt;
    }

    private double getDt(){
        if(isDt){
            Dt = 50;
            isDt = false;
        }else {
            Dt = 3*Dt-2*Kt;
        }
        return Dt;
    }



}
