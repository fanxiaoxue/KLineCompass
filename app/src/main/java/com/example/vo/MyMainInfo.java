package com.example.vo;

import java.io.Serializable;

/**
 * Created by thinking on 2016/5/25.
 */
public class MyMainInfo implements Serializable{
    private String mName;
    private String mPrice;
    private String mZhangf;
    private String mturnover;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getZhangf() {
        return mZhangf;
    }

    public void setZhangf(String zhangf) {
        mZhangf = zhangf;
    }

    public String getMturnover() {
        return mturnover;
    }

    public void setMturnover(String mturnover) {
        this.mturnover = mturnover;
    }

    public MyMainInfo(String name, String price, String zhangf, String mturnover) {
        mName = name;
        mPrice = price;
        mZhangf = zhangf;
        this.mturnover = mturnover;
    }

    public MyMainInfo() {
        super();
    }

    @Override
    public String toString() {
        return "MyMainInfo{" +
                "mName='" + mName + '\'' +
                ", mPrice='" + mPrice + '\'' +
                ", mZhangf='" + mZhangf + '\'' +
                ", mturnover='" + mturnover + '\'' +
                '}';
    }
}
