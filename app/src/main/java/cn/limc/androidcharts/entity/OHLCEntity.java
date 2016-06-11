package cn.limc.androidcharts.entity;

/**
 * Created by thinking on 2016/6/6.
 */
public class OHLCEntity {
    /** Open Session Value 开盘价*/
    private double open;

    /** Highest Value */
    private double high;

    /** Lowest Value */
    private double low;

    /** Close Session Value 封盘价*/
    private double close;

    /** Date 日期*/
    private String date;

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OHLCEntity(double open, double high, double low, double close,
                      String date) {
        super();
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.date = date;
    }

    public OHLCEntity() {
        super();
    }
}
