package com.xy9860.iwara.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy9860.iwara.R;

import java.util.LinkedList;

public class MyAdapter extends BaseAdapter {

    private LinkedList<Data> mData;
    private Context mContext;

    public MyAdapter(LinkedList<Data> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext,R.layout.content_item,null);
        ImageView mthumbnail = convertView.findViewById(R.id.thumbnail);
        TextView mitem_title = convertView.findViewById(R.id.item_title);
        TextView mitem_author = convertView.findViewById(R.id.item_author);
        mthumbnail.setBackgroundResource(mData.get(position).getaThumbnail());
        mitem_title.setText(mData.get(position).getaTitle());
        mitem_author.setText(mData.get(position).getaAuther());
        return convertView;
    }
}
