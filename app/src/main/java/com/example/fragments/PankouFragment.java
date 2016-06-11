package com.example.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinking.klinecompass.R;

/**
 * Created by thinking on 2016/5/25.
 */
public class PankouFragment extends Fragment implements View.OnClickListener{

    private ImageView mNavImgRight;
    private ImageView mNavImgLeft;
    private TextView mHandicap;
    private TextView mKnockdown;
    private String mDataUrl;

    public PankouFragment(String dataUrl) {
        mDataUrl = dataUrl;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pankoufragment, null);
        mNavImgRight = (ImageView) view.findViewById(R.id.navImgRight);
        mNavImgLeft = (ImageView) view.findViewById(R.id.navImgLeft);
        mHandicap = (TextView) view.findViewById(R.id.Handicap);
        mKnockdown = (TextView) view.findViewById(R.id.knockdown);

        Log.i("aaa","PanKou========== "+mDataUrl);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.FL,new HandicapFragment(mDataUrl)).commit();
        mHandicap.setOnClickListener(this);
        mKnockdown.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Handicap:
                BackGroundColors();
                mHandicap.setTextColor(Color.RED);
                mNavImgLeft.setBackgroundColor(Color.RED);
                FragmentTransaction ft01 = getFragmentManager().beginTransaction();
                ft01.replace(R.id.FL,new HandicapFragment(mDataUrl)).commit();
                break;
            case R.id.knockdown:
                BackGroundColors();
                mKnockdown.setTextColor(Color.RED);
                mNavImgRight.setBackgroundColor(Color.RED);
                FragmentTransaction ft02 = getFragmentManager().beginTransaction();
                ft02.replace(R.id.FL,new KnockdownFragment()).commit();
                break;
        }
    }

    private void BackGroundColors() {
        mHandicap.setTextColor(Color.WHITE);
        mKnockdown.setTextColor(Color.WHITE);
        mNavImgLeft.setBackgroundColor(Color.BLACK);
        mNavImgRight.setBackgroundColor(Color.BLACK);
    }


}
