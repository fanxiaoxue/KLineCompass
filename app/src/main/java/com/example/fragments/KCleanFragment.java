package com.example.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thinking.klinecompass.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.OHLCEntity;
import cn.limc.androidcharts.entity.StickEntity;
import cn.limc.androidcharts.view.MACandleStickChart;

/**
 * Created by thinking on 2016/5/25.
 */
public class KCleanFragment extends Fragment {

    private RelativeLayout mCharLine2;
    List<OHLCEntity> mOhlc;
    List<OHLCEntity> mBar;

    MACandleStickChart macandlestickchart;
    MACandleStickChart macandlestickchart3;
    private View mView;
    private String mDataUrl;
    private Timer mMTmr3;
    private RequestQueue mMRequestQueue;
    private List<LineEntity> mLines;
    private RelativeLayout mCharLine3;

    public KCleanFragment(String dataUrl) {
        mDataUrl = dataUrl;
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String[] data = (String[]) msg.obj;
            Double openvalue = Double.parseDouble(data[2]);
            Double hightvalue = Double.parseDouble(data[3]);
            Double lesstvalue = Double.parseDouble(data[4]);
            Double closevalue = Double.parseDouble(data[8]);
            Log.i("aaa", "closevalue=---------======" + closevalue);
            mOhlc.add(new OHLCEntity(openvalue, hightvalue, lesstvalue, closevalue, data[17]));
           //添加均线
            initMACandleStickChart();


            //刷新柱形线
            macandlestickchart.pushData(new OHLCEntity(openvalue, hightvalue,lesstvalue,closevalue,data[17]));
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.kcleanefragment,null);
       // mCharLine2 = (RelativeLayout) mView.findViewById(R.id.charLine2);
        mCharLine3 = (RelativeLayout) mView.findViewById(R.id.charLine3);
        //initVOL();// 价格数据，最高价、最低价和当前价
        initOHLC(); // 盘面数据信息
        initMACandleStickChart();
        Log.i("aaa", "KClineFragment========== " + mDataUrl);
        mMRequestQueue = Volley.newRequestQueue(getActivity());
        startTimer();

        return mView;
    }

    private void initMACandleStickChart() {

        this.macandlestickchart = (MACandleStickChart) mView.findViewById(R.id.macandlestickchart);
        this.macandlestickchart3 = (MACandleStickChart) mView.findViewById(R.id.macandlestickchart3);
        mLines = new ArrayList<LineEntity>();

        // 计算5日均线
        LineEntity MA5 = new LineEntity();
        MA5.setTitle("MA5");
        MA5.setLineColor(Color.WHITE);
        MA5.setLineData(initMA(5));
        mLines.add(MA5);

        // 计算10日均线
        LineEntity MA10 = new LineEntity();
        MA10.setTitle("MA10");
        MA10.setLineColor(Color.RED);
        MA10.setLineData(initMA(10));
        mLines.add(MA10);

        // 计算25日均线
        LineEntity MA25 = new LineEntity();
        MA25.setTitle("MA25");
        MA25.setLineColor(Color.GREEN);
        MA25.setLineData(initMA(25));
        mLines.add(MA25);

        macandlestickchart.setAxisXColor(Color.LTGRAY);
        macandlestickchart.setAxisYColor(Color.LTGRAY);
        macandlestickchart.setLatitudeColor(Color.BLACK);//网格x轴颜色
        macandlestickchart.setLongitudeColor(Color.BLACK);//网格y轴颜色
        macandlestickchart.setBorderColor(Color.BLACK);  //右边框显示颜色
        macandlestickchart.setLongtitudeFontColor(Color.BLACK);//x轴的刻度的颜色
        macandlestickchart.setLatitudeFontColor(Color.BLACK);  //y轴的刻度的颜色
        macandlestickchart.setAxisMarginRight(1);

        // 最大显示足数
        macandlestickchart.setMaxCandleSticksNum(52);
        // 最大纬线数
        macandlestickchart.setLatitudeNum(5);
        // 最大经线数
        macandlestickchart.setLongtitudeNum(3);
        // 最大价格
        macandlestickchart.setMaxPrice(10000);
        // 最小价格
        macandlestickchart.setMinPrice(400);

        macandlestickchart.setDisplayAxisXTitle(true);
        macandlestickchart.setDisplayAxisYTitle(true);
        macandlestickchart.setDisplayLatitude(true);
        macandlestickchart.setDisplayLongitude(true);
        macandlestickchart.setBackgroudColor(Color.BLACK);

        // 为chart2增加均线
        macandlestickchart.setLineData(mLines);

        // 为chart2增加均线
        macandlestickchart.setOHLCData(mOhlc);
        //为进场提示bar
        macandlestickchart3.setOHLCData(mBar);

    }

    private void initOHLC() {
        List<OHLCEntity> ohlc = new ArrayList<OHLCEntity>();
        List<OHLCEntity> ohlc3 = new ArrayList<OHLCEntity>();

        mBar = new ArrayList<OHLCEntity>();
        mOhlc = new ArrayList<OHLCEntity>();
        ohlc.add(new OHLCEntity(2042, 2044, 2040, 2042, "20110808"));
        ohlc.add(new OHLCEntity(2048, 2049, 2047, 2048, "20110805"));
        ohlc.add(new OHLCEntity(2045, 2048, 2045, 2047, "20110804"));
        ohlc.add(new OHLCEntity(2049, 2049, 2045, 2047, "20110803"));
        ohlc.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc.add(new OHLCEntity(2050, 2052, 2048, 2050, "20110801"));
        ohlc.add(new OHLCEntity(2050, 2051, 2048, 2050, "20110729"));
        ohlc.add(new OHLCEntity(2049, 2052, 2048, 2052, "20110728"));
        ohlc.add(new OHLCEntity(2048, 2050, 2047, 2050, "20110727"));
        ohlc.add(new OHLCEntity(2056, 2056, 2048, 2048, "20110726"));
        ohlc.add(new OHLCEntity(2057, 2058, 2056, 2057, "20110725"));
        ohlc.add(new OHLCEntity(2059, 2060, 2056, 2056, "20110722"));
        ohlc.add(new OHLCEntity(2061, 2061, 2057, 2159, "20110721"));
        ohlc.add(new OHLCEntity(2160, 2160, 2159, 2159, "20110720"));
        ohlc.add(new OHLCEntity(2162, 2162, 2160, 2161, "20110719"));
        ohlc.add(new OHLCEntity(2160, 2162, 2159, 2162, "20110718"));
        ohlc.add(new OHLCEntity(2159, 2161, 2158, 2161, "20110715"));
        ohlc.add(new OHLCEntity(2155, 2159, 2155, 2159, "20110714"));
        ohlc.add(new OHLCEntity(2158, 2158, 2155, 2155, "20110713"));
        ohlc.add(new OHLCEntity(2158, 2260, 2258, 2260, "20110712"));
        ohlc.add(new OHLCEntity(2259, 2260, 2258, 2259, "20110711"));
        ohlc.add(new OHLCEntity(2161, 2162, 2159, 2159, "20110708"));
        ohlc.add(new OHLCEntity(2161, 2161, 2158, 2161, "20110707"));
        ohlc.add(new OHLCEntity(2161, 2161, 2159, 2161, "20110706"));
        ohlc.add(new OHLCEntity(2157, 2161, 2157, 2161, "20110705"));
        ohlc.add(new OHLCEntity(2156, 2157, 2155, 2155, "20110704"));
        ohlc.add(new OHLCEntity(2153, 2157, 2153, 2156, "20110701"));
        ohlc.add(new OHLCEntity(2155, 2155, 2152, 2152, "20110630"));
        ohlc.add(new OHLCEntity(2156, 2156, 2153, 2155, "20110629"));
        ohlc.add(new OHLCEntity(2154, 2156, 2154, 2155, "20110628"));
        ohlc.add(new OHLCEntity(2147, 2156, 2147, 2154, "20110627"));
        ohlc.add(new OHLCEntity(2144, 2149, 2143, 2148, "20110624"));
        ohlc.add(new OHLCEntity(2244, 2245, 2243, 2244, "20110623"));
        ohlc.add(new OHLCEntity(2242, 2244, 2241, 2244, "20110622"));
        ohlc.add(new OHLCEntity(2243, 2243, 2241, 2242, "20110621"));
        ohlc.add(new OHLCEntity(2246, 2247, 2244, 2244, "20110620"));
        ohlc.add(new OHLCEntity(2248, 2249, 2246, 2246, "20110617"));
        ohlc.add(new OHLCEntity(2251, 2253, 2250, 2250, "20110616"));
        ohlc.add(new OHLCEntity(2249, 2253, 2249, 2253, "20110615"));
        ohlc.add(new OHLCEntity(2248, 2250, 2246, 2250, "20110614"));
        ohlc.add(new OHLCEntity(2249, 2250, 2247, 2250, "20110613"));
        ohlc.add(new OHLCEntity(2254, 2254, 2150, 22150, "20110610"));
        ohlc.add(new OHLCEntity(2154, 2155, 2251, 2255, "20110609"));
        ohlc.add(new OHLCEntity(2252, 2154, 2151, 2254, "20110608"));
        ohlc.add(new OHLCEntity(2250, 2153, 2150, 2252, "20110607"));
        ohlc.add(new OHLCEntity(2251, 2152, 2147, 2150, "20110603"));
        ohlc.add(new OHLCEntity(2153, 2154, 2152, 2154, "20110602"));
        ohlc.add(new OHLCEntity(2250, 2254, 2250, 2254, "20110601"));
        ohlc.add(new OHLCEntity(2250, 2252, 2248, 2250, "20110531"));
        ohlc.add(new OHLCEntity(2153, 2154, 2150, 2151, "20110530"));
        ohlc.add(new OHLCEntity(2155, 2256, 2153, 2153, "20110527"));
        ohlc.add(new OHLCEntity(2156, 2157, 2153, 2154, "20110526"));
        ohlc.add(new OHLCEntity(2256, 2257, 2254, 2156, "20110525"));
        ohlc.add(new OHLCEntity(2165, 2165, 2157, 2157, "20110524"));
        ohlc.add(new OHLCEntity(2165, 2166, 2165, 2265, "20110523"));
        ohlc.add(new OHLCEntity(2267, 2268, 2265, 2266, "20110520"));
        ohlc.add(new OHLCEntity(2164, 2167, 2164, 2167, "20110519"));
        ohlc.add(new OHLCEntity(2164, 2266, 2262, 2265, "20110518"));
        ohlc.add(new OHLCEntity(2166, 2167, 2164, 2164, "20110517"));
        ohlc.add(new OHLCEntity(2264, 2267, 2263, 2167, "20110516"));
        ohlc.add(new OHLCEntity(2166, 2167, 2264, 2264, "20110513"));
        ohlc.add(new OHLCEntity(2169, 2269, 2266, 2268, "20110512"));
        ohlc.add(new OHLCEntity(2167, 2269, 2266, 2269, "20110511"));
        ohlc.add(new OHLCEntity(2166, 2168, 2266, 2267, "20110510"));
        ohlc.add(new OHLCEntity(2164, 2168, 2163, 2166, "20110509"));
        ohlc.add(new OHLCEntity(2165, 2168, 2165, 2167, "20110506"));
        ohlc.add(new OHLCEntity(2171, 2171, 2166, 2166, "20110505"));
        ohlc.add(new OHLCEntity(2171, 2173, 2169, 2173, "20110504"));
        ohlc.add(new OHLCEntity(2168, 2171, 2267, 2271, "20110503"));

        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2050, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2020, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2020, "20110802"));
        ohlc3.add(new OHLCEntity(2049, 2051, 2048, 2020, "20110802"));






        for (int i = ohlc3.size(); i > 0; i--) {
            mBar.add(ohlc3.get(i - 1));
        }
        for (int i = ohlc.size(); i > 0; i--) {
            mOhlc.add(ohlc.get(i - 1));
        }
    }

    private List<Float> initMA(int days) {

        if (days < 2) {
            return null;
        }

        List<Float> MA5Values = new ArrayList<Float>();

        float sum = 0;
        float avg = 0;
        for (int i = 0; i < mOhlc.size(); i++) {
            float close = (float) mOhlc.get(i).getClose();
            if (i < days) {
                sum = sum + close;
                avg = sum / (i + 1f);
            } else {
                sum = sum + close - (float) mOhlc.get(i - days).getClose();
                avg = sum / days;
            }
            MA5Values.add(avg);
        }

        return MA5Values;
    }

    private void startTimer() {

        mMTmr3 = new Timer();
        mMTmr3.schedule(new TimerTask() {
            @Override
            public void run() {

                StringRequest stringRequest = new StringRequest(mDataUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("aaa", "kCleanFragment======= " + response);
                                String M = response.substring(18, response.length() - 3);
                                String[] data = M.split("\\,");
                                Message msg = Message.obtain();
                                msg.obj = data;
                                msg.what = 1;
                                mHandler.sendMessage(msg);
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("aaa", error.getMessage(), error);
                    }
                });
                mMRequestQueue.add(stringRequest);

            }
        }, 0, 30*1000);

    }

}
