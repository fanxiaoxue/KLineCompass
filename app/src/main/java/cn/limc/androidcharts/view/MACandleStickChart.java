package cn.limc.androidcharts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import java.util.List;

import cn.limc.androidcharts.entity.LineEntity;

/**
 * Created by thinking on 2016/6/6.
 */
public class MACandleStickChart extends CandleStickChart{
    /** 是否显示全部 */
    private boolean displayAll = true;

    /** 平均线显示数据 */
    private List<LineEntity> lineData;

    public MACandleStickChart(Context context) {
        super(context);
    }

    public MACandleStickChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MACandleStickChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    ////////////方�?//////////////
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制平均线
        if(null != this.lineData){
            if (0 != this.lineData.size()){
                drawLines(canvas);
            }
        }
    }

    protected void drawLines(Canvas canvas){
        // 点线距离
        float lineLength = ((super.getWidth() - super.getAxisMarginLeft()-super.getAxisMarginRight()) / super.getMaxCandleSticksNum())-1;
        // 起始位置
        float startX;

        //逐条输入MA线
        for (int i = 0; i < lineData.size(); i++) {
            LineEntity line = (LineEntity)lineData.get(i);
            if(line.isDisplay()){
                Paint mPaint = new Paint();
                mPaint.setColor(line.getLineColor());
                mPaint.setAntiAlias(true);
                List<Float> lineData = line.getLineData();
                //输入x起点线
                startX = super.getAxisMarginLeft() + lineLength / 2f;
                //定义起始点
                PointF ptFirst = null;
                if(lineData !=null){
                    for(int j=0 ; j <lineData.size();j++){
                        float value = lineData.get(j).floatValue();
                        //获取终点Y坐标?
                        float valueY = (float) ((1f - (value - super.getMinPrice())
                                / (super.getMaxPrice() - super.getMinPrice()))
                                * (super.getHeight() - super.getAxisMarginBottom()));

                        //绘制线条
                        if (j > 0){
                            canvas.drawLine(ptFirst.x,ptFirst.y,startX,valueY,mPaint);
                        }
                        //重置起始点
                        ptFirst = new PointF(startX , valueY);
                        //X位移
                        startX = startX + 1 + lineLength;
                    }
                }
            }
        }
    }

    ////////////属性GetterSetter方法//////////////
    public boolean isDisplayAll() {
        return displayAll;
    }

    public void setDisplayAll(boolean displayAll) {
        this.displayAll = displayAll;
    }

    public List<LineEntity> getLineData() {
        return lineData;
    }

    //增加平均线
    public void setLineData(List<LineEntity> lineData) {
        this.lineData = lineData;
    }

}
