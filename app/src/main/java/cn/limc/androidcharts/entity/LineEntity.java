package cn.limc.androidcharts.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinking on 2016/6/6.
 */
public class LineEntity {
    /** 线的数据 */
    private List<Float> lineData;

    /** 平均线名称*/
    private String title;

    /** 线表示的颜色 */
    private int lineColor;

    /** 是否显示线 */
    private boolean display = true;

    public List<Float> getLineData() {
        return lineData;
    }

    public void setLineData(List<Float> lineData) {
        this.lineData = lineData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public LineEntity() {
        super();
    }

    public LineEntity(List<Float> lineData, String title, int lineColor) {
        super();
        this.lineData = lineData;
        this.title = title;
        this.lineColor = lineColor;
    }

    public void put(float value){
        if (null == lineData){
            lineData = new ArrayList<Float>();
        }
        lineData.add(value);
    }


}
