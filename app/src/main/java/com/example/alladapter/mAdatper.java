package com.example.alladapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.thinking.klinecompass.R;
import com.example.vo.MyMainInfo;

import java.util.List;

/**
 * Created by thinking on 2016/5/25.
 */
public class mAdatper extends BaseAdapter {

    Context context;
    List<MyMainInfo> list;

    public mAdatper(Context context, List<MyMainInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.mainitem,null);
            vh.name = (TextView)convertView.findViewById(R.id.mainHName);
            vh.price = (TextView)convertView.findViewById(R.id.mainHprice);
            vh.zf = (TextView)convertView.findViewById(R.id.mainHzhangfu);
            vh.cjl = (TextView)convertView.findViewById(R.id.mainHchengjiao);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder)convertView.getTag();
        }
        vh.name.setText(list.get(position).getName());
        vh.price.setText(list.get(position).getPrice());
        vh.zf.setText(list.get(position).getZhangf());
        vh.cjl.setText(list.get(position).getMturnover());
        return convertView;
    }
    class ViewHolder{
        TextView name,price,zf,cjl;
    }
    public void upData(List list){
        this.list = list;
        this.notifyDataSetChanged();
    }

}
