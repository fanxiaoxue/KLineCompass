package com.example.fragments;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.thinking.klinecompass.R;
import com.zhy.autolayout.AutoFrameLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by thinking on 2016/5/25.
 */
public class FenshiFragment extends Fragment {
    //布局
    private RelativeLayout mCharLine;
    // 用于存放每条折线的点数据
    private XYSeries line1;
    // 用于存放所有需要绘制的XYSeries
    private XYMultipleSeriesDataset mDataset;
    // 用于存放每条折线的风格
    private XYSeriesRenderer renderer1;
    // 用于存放所有需要绘制的折线的风格
    private XYMultipleSeriesRenderer mXYMultipleSeriesRenderer;
    private GraphicalView chart;

    // 以下属性用于initLine(XYSeries series)方法中更新数据
    private int count;
    private double xTemp, yTemp;
    private Timer mTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),R.layout.fenshifragment,null);
        mCharLine = (RelativeLayout) view.findViewById(R.id.charLine);
        initChart();
        refreshChart();
        return view;
    }

    private void initChart() {
        // 初始化，必须保证XYMultipleSeriesDataset对象中的XYSeries数量和
        // XYMultipleSeriesRenderer对象中的XYSeriesRenderer数量一样多
        line1 = new XYSeries("");
        renderer1 = new XYSeriesRenderer();
        mDataset = new XYMultipleSeriesDataset();
        mXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer();

        initLine(line1);
        initRenderer(renderer1, Color.GREEN, PointStyle.CIRCLE, true);

        // 将XYSeries对象和XYSeriesRenderer对象分别添加到XYMultipleSeriesDataset对象和XYMultipleSeriesRenderer对象中。
        mDataset.addSeries(line1);
        mXYMultipleSeriesRenderer.addSeriesRenderer(renderer1);

        // 配置chart参数,背景风格线设置成红色
        setChartSettings(mXYMultipleSeriesRenderer, "X", "Y", 0, 10, 0, 100, Color.BLACK,//Y轴上的数据距离1--10，长度0--100
                Color.BLACK);

        // 通过该函数获取到一个View 对象
        chart = ChartFactory.getLineChartView(getActivity(), mDataset, mXYMultipleSeriesRenderer);

        // 将该View 对象添加到layout中。
        mCharLine.addView(chart, new AutoFrameLayout.LayoutParams(AutoFrameLayout.LayoutParams.MATCH_PARENT,
                AutoFrameLayout.LayoutParams.MATCH_PARENT));
    }

    private XYSeriesRenderer initRenderer(XYSeriesRenderer renderer, int color,
                                          PointStyle style, boolean fill) {
        // 设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
        renderer.setColor(color);
        renderer.setPointStyle(style);
        renderer.setFillPoints(fill);
        renderer.setLineWidth(1);
        return renderer;
    }

    protected void setChartSettings(XYMultipleSeriesRenderer mXYMultipleSeriesRenderer,
                                    String xTitle, String yTitle, double xMin, double xMax,
                                    double yMin, double yMax, int axesColor, int labelsColor) {
        // 有关对图表的渲染可参看api文档
        mXYMultipleSeriesRenderer.setXTitle(xTitle);
        mXYMultipleSeriesRenderer.setYTitle(yTitle);
        mXYMultipleSeriesRenderer.setXAxisMin(xMin);//设置 x 轴最小值
        mXYMultipleSeriesRenderer.setAxisTitleTextSize(1);//设置 xy 轴标题字体大小
        //mXYMultipleSeriesRenderer.setChartTitleTextSize(50);//设置表格标题大小
        mXYMultipleSeriesRenderer.setLabelsTextSize(15);//设置x轴与Y轴标签文字大小
        mXYMultipleSeriesRenderer.setXAxisMax(xMax);
        mXYMultipleSeriesRenderer.setYAxisMin(yMin);
        mXYMultipleSeriesRenderer.setYAxisMax(yMax);
        mXYMultipleSeriesRenderer.setAxesColor(axesColor);
        mXYMultipleSeriesRenderer.setLabelsColor(labelsColor);
        mXYMultipleSeriesRenderer.setBackgroundColor(Color.BLACK);
        mXYMultipleSeriesRenderer.setApplyBackgroundColor(true);
        mXYMultipleSeriesRenderer.setShowGrid(true);
        mXYMultipleSeriesRenderer.setGridColor(Color.RED);
        mXYMultipleSeriesRenderer.setXLabels(0);
        mXYMultipleSeriesRenderer.setYLabels(10);
        mXYMultipleSeriesRenderer.setXTitle("");
        mXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mXYMultipleSeriesRenderer.setPointSize((float) 1);//设置点的大小---------------------
        mXYMultipleSeriesRenderer.setShowLegend(true);
        mXYMultipleSeriesRenderer.setLegendTextSize(20);//设置说明文字大小
    }

    class RefreshSeriesTask extends TimerTask {
        public void run() {
            initLine(line1);
            chart.postInvalidate();
        }
    }

    private void initLine(XYSeries series) {

        count = series.getItemCount();

        Random r = new Random();
        if (count != 0) {
            xTemp = series.getX(count - 1) + 1;
        }
        else {
            xTemp = 0;
        }
        yTemp = r.nextInt(100);
        series.add(xTemp, yTemp);

    }

    private void refreshChart() {
        mTimer = new Timer();
        mTimer.schedule(new RefreshSeriesTask(), 0, 1 * 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("aaa","fengshi===========");
        mTimer.cancel();
    }
}
