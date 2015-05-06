package com.guang.textrobot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class MyListAdapter extends BaseAdapter {

    private List<ListData> lists;
    private RelativeLayout layout;
    private Context context;

    public MyListAdapter(List<ListData> lists, Context context){
        this.lists = lists;
        this.context = context;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (lists.get(position).getFlag() == ListData.SEND){
            layout = (RelativeLayout) inflater.inflate(R.layout.rightitem,null);
        }
        if (lists.get(position).getFlag() == ListData.RECEIVE){
            layout = (RelativeLayout) inflater.inflate(R.layout.leftitem,null);
        }
        TextView tv = (TextView) layout.findViewById(R.id.sendString);
        TextView time = (TextView) layout.findViewById(R.id.timeText);
        tv.setText(lists.get(position).getContentStr());
        time.setText(lists.get(position).getTime());

        return layout;
    }
}
