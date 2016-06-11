package com.example.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thinking.klinecompass.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by thinking on 2016/5/25.
 */
public class HandicapFragment extends Fragment {

    private View mView;
    private TextView mSellprice;
    private TextView mSellnum;
    private TextView mBuying;
    private TextView mBuynum;
    private TextView mNewprice;
    private TextView mZDie;
    private TextView mKaipan;
    private TextView mCjliang;
    private TextView mZuiHeight;
    private TextView mChicangNum;
    private TextView mZuiLow;
    private TextView mDayZengCang;
    private TextView mAveragePrice;
    private TextView mOutPan;
    private TextView mSettleAccounts;
    private TextView mInnerDisc;
    private TextView mZuoJie;
    private TextView mZhangTing;
    private TextView mZuoshou;
    private TextView mDieTing;
    private String mDataUrl;
    private RequestQueue mMRequestQueue;
    private Timer mTmr2;

    public HandicapFragment(String dataUrl) {
        mDataUrl = dataUrl;
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String[] data = (String[]) msg.obj;
            mSellprice.setText(data[7]);
            mSellnum.setText(data[12]);
            mBuying.setText(data[6]);
            mBuynum.setText(data[11]);
            mNewprice.setText(data[8]);
            mKaipan.setText(data[2]);
            mCjliang.setText(data[14]);
            mZuiHeight.setText(data[3]);
            mChicangNum.setText(data[13]);
            mZuiLow.setText(data[4]);
            mZuoJie.setText(data[10]);
            mZuoshou.setText(data[5]);

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = View.inflate(getActivity(), R.layout.handicap_fragment, null);

        //加载控件
        ViewInfo();

        mMRequestQueue = Volley.newRequestQueue(getActivity());

        Log.i("aaa", "Handicap========== " + mDataUrl);
        //获取数据信息
        startTimer();
        return mView;
    }

    private void ViewInfo() {
        mSellprice = (TextView) mView.findViewById(R.id.sellprice);
        mSellnum = (TextView) mView.findViewById(R.id.sellnum);
        mBuying = (TextView) mView.findViewById(R.id.buying);
        mBuynum = (TextView) mView.findViewById(R.id.buynum);
        mNewprice = (TextView) mView.findViewById(R.id.newprice);
        mZDie = (TextView) mView.findViewById(R.id.zDie);
        mKaipan = (TextView) mView.findViewById(R.id.kaipan);
        mCjliang = (TextView) mView.findViewById(R.id.cjliang);
        mZuiHeight = (TextView) mView.findViewById(R.id.zuiHeight);
        mChicangNum = (TextView) mView.findViewById(R.id.chicangNum);
        mZuiLow = (TextView) mView.findViewById(R.id.zuiLow);
        mDayZengCang = (TextView) mView.findViewById(R.id.dayZengCang);
        mAveragePrice = (TextView) mView.findViewById(R.id.averagePrice);
        mOutPan = (TextView) mView.findViewById(R.id.outPan);
        mSettleAccounts = (TextView) mView.findViewById(R.id.settleAccounts);
        mInnerDisc = (TextView) mView.findViewById(R.id.innerDisc);
        mZuoJie = (TextView) mView.findViewById(R.id.zuoJie);
        mZhangTing = (TextView) mView.findViewById(R.id.zhangTing);
        mZuoshou = (TextView) mView.findViewById(R.id.zuoshou);
        mDieTing = (TextView) mView.findViewById(R.id.dieTing);
    }

    private void startTimer() {

        mTmr2 = new Timer();
        mTmr2.schedule(new TimerTask() {
            @Override
            public void run() {

                StringRequest stringRequest = new StringRequest(mDataUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("aaa", "fragment======= " + response);
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
        }, 0, 3000);

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("aaa", "tmr===onPause=====");
        mTmr2.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("aaa", "tmr===onStop=====");
        mTmr2.cancel();
    }
}
