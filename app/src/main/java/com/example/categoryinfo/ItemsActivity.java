package com.example.categoryinfo;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.fragments.FenshiFragment;
import com.example.fragments.KCleanFragment;
import com.example.fragments.PankouFragment;
import com.example.thinking.klinecompass.R;
import com.example.vo.MyMainInfo;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AutoLayoutActivity implements View.OnClickListener{
    private ViewPager vp;
    private ImageView left02,right02;//左右滑动按扭
    private TextView title02,name02;
    private LinearLayout mnews,mpankou,mfenshi,mxiadan,mKclean;//这是最下面的导航
    private List<Fragment> mFragmentList;
    private String mDataUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_items);


        Intent in = getIntent();
        mDataUrl = in.getStringExtra("dataUrl");
        Log.i("aaa","aaaaaaaaaaaaaaaaaaaaaaaa"+ mDataUrl);

        //加载控件
        ViewInfo();
        //添加fragment页面到mFragmentList
        addFragment();
        Log.i("aaa", mFragmentList.size() + "");

       vpAdapter(); //适配
        vp.setCurrentItem(0);
        navClickListener();
    }

    private void vpAdapter() {
        //vp适配
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });

        //vp监听
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {

                    case 0:
                        backgroundColors();
                        mpankou.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        backgroundColors();
                        mfenshi.setBackgroundColor(Color.RED);
                        break;
                    case 2:
                        backgroundColors();
                        mKclean.setBackgroundColor(Color.RED);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void ViewInfo() {
                vp = (ViewPager)findViewById(R.id.vp);
        left02 =(ImageView)findViewById(R.id.left02);
        title02 = (TextView)findViewById(R.id.title02);
        name02 = (TextView)findViewById(R.id.name02);
        right02 = (ImageView)findViewById(R.id.right02);
        mnews = (LinearLayout)findViewById(R.id.mnews);
        mpankou = (LinearLayout)findViewById(R.id.mpankou);
        mfenshi = (LinearLayout)findViewById(R.id.mfenshi);
        mKclean = (LinearLayout)findViewById(R.id.mKclean);
        mxiadan = (LinearLayout)findViewById(R.id.mxiadan);
    }
    //添加fragment页面到mFragmentList
    private void addFragment() {
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new PankouFragment(mDataUrl));
        mFragmentList.add(new FenshiFragment());
        mFragmentList.add(new KCleanFragment(mDataUrl));
    }

    private void navClickListener() {
        mnews.setOnClickListener(ItemsActivity.this);
        mfenshi.setOnClickListener(ItemsActivity.this);
        mpankou.setOnClickListener(ItemsActivity.this);
        mKclean.setOnClickListener(ItemsActivity.this);
        mxiadan.setOnClickListener(ItemsActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mnews:
                backgroundColors();
                mnews.setBackgroundColor(Color.RED);
                Intent intent = new Intent(ItemsActivity.this,NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.mpankou:
                backgroundColors();
                mpankou.setBackgroundColor(Color.RED);
                vp.setCurrentItem(0);
                break;
            case R.id.mfenshi:
                backgroundColors();
                mfenshi.setBackgroundColor(Color.RED);
                vp.setCurrentItem(1);
                break;
            case R.id.mKclean:
                backgroundColors();
                mKclean.setBackgroundColor(Color.RED);
                vp.setCurrentItem(2);
                break;

            case R.id.mxiadan:
                backgroundColors();
                mxiadan.setBackgroundColor(Color.RED);
                Intent in = new Intent(ItemsActivity.this,XiaDanActivity.class);
                startActivity(in);
                break;
        }
    }

    private void backgroundColors() {
        mnews.setBackgroundColor(Color.BLACK);
        mpankou.setBackgroundColor(Color.BLACK);
        mfenshi.setBackgroundColor(Color.BLACK);
        mKclean.setBackgroundColor(Color.BLACK);
        mxiadan.setBackgroundColor(Color.BLACK);
    }
}















