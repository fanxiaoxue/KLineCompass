package cn.limc.androidcharts.view;

/**
 * Created by thinking on 2016/6/6.
 */
public interface ITouchEventNotify {
    public void notifyEventAll(GridChart chart);

    public void addNotify(ITouchEventResponse notify);

    public void removeNotify(int i);

    public void removeAllNotify();
}
