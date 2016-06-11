package com.example.thinking.klinecompass;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alladapter.mAdatper;
import com.example.categoryinfo.ItemsActivity;
import com.example.vo.DataUrl;
import com.example.vo.MyMainInfo;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AutoLayoutActivity {

    private ImageView left,right;//主页点击侧滑的两个近扭
    private TextView tname,zhangdie,chengjiaoliang;
    private ListView mainLV;
    private List<MyMainInfo> list;
    private String uri = "http://hq.sinajs.cn/list=M1309";
    private RequestQueue mRequestQueue;
    private String[] mM09;
    private String[] RB09;
    private mAdatper mMAdatper;


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mM09 = (String[]) msg.obj;
                    MyMainInfo MO = new MyMainInfo(mM09[0],mM09[8],"-0.26",mM09[14]);
                    if(list.size()==0){
                        list.add(MO);
                    }else {
                        list.set(0,MO);
                        mMAdatper.upData(list);
                       // Log.i("aaa","listM =============="+list.toString());

                    }

                    //mMAdatper.notifyDataSetChanged();

                    break;
                case 2:
                    RB09 = (String[]) msg.obj;
                    MyMainInfo RB = new MyMainInfo(RB09[0],RB09[8],"-0.26",RB09[14]);
                    if (list.size()==2){
                        list.set(1,RB);
                        mMAdatper.upData(list);
                       // Log.i("aaa", "listR ==============" + list.toString());
                    }else if(list.size() ==1){

                        list.add(RB);
                    }
                    //mMAdatper.notifyDataSetChanged();
                    break;
            }

        }
    };
    private Timer mTmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
        //加载控件
        ViewInfo();
        //listView适配和监听
        mainLVInit();
        //获取VolleyQueue
        mRequestQueue = Volley.newRequestQueue(this);
        //获取数据信息
        startTimer();


    }

    private void startTimer() {
        mTmr = new Timer();
        mTmr.schedule(new TimerTask() {
            @Override
            public void run() {

                StringRequest stringRequest = new StringRequest("http://hq.sinajs.cn/list=M1609",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("aaa", response);
                                String M = response.substring(18, response.length() - 3);
                                String[] M_09 = M.split("\\,");
                                Message msg = Message.obtain();
                                msg.obj = M_09;
                                msg.what = 1;
                                mHandler.sendMessage(msg);
                            }


                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("aaa", error.getMessage(), error);
                    }
                });
                StringRequest stringRequest1 = new StringRequest("http://hq.sinajs.cn/list=RB1609",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                 Log.d("aaa", response);
                                String RB = response.substring(19, response.length() - 3);
                                String[] RB_09 = RB.split("\\,");
                                Message msg = Message.obtain();
                                msg.obj = RB_09;
                                msg.what = 2;
                                mHandler.sendMessage(msg);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("aaa", error.getMessage(), error);
                    }
                });
                mRequestQueue.add(stringRequest);
                mRequestQueue.add(stringRequest1);
            }
        }, 0, 3000);
    }


    //加载控件
    private void ViewInfo() {
        tname = (TextView)findViewById(R.id.tname);
        zhangdie = (TextView)findViewById(R.id.zhangdie);
        chengjiaoliang = (TextView)findViewById(R.id.chengjiaoliang);
        left = (ImageView) findViewById(R.id.lift);
        right = (ImageView)findViewById(R.id.right);
        mainLV = (ListView)findViewById(R.id.mainLV);
    }
    //listView适配和监听
    private void mainLVInit() {
        //mainLV.setAdapter(new mAdapter(MainActivity.this,));
       list = new ArrayList<MyMainInfo>();

        Log.i("aaa", list.toString());
        mMAdatper = new mAdatper(MainActivity.this, list);
        mainLV.setAdapter(mMAdatper);//适配
        mainLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
                String str = DataUrl.strUrl[position];
                intent.putExtra("dataUrl",str);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mTmr.cancel();
    }
}















