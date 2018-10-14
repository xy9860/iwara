package com.xy9860.iwara.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy9860.iwara.R;

import java.util.List;

public class Myad extends RecyclerView.Adapter<Myad.ViewHolder> {

    private List<Data> data;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView author,title;
        ViewHolder(View view) {
            super(view);
            author = view.findViewById(R.id.item_author);
            title = view.findViewById(R.id.item_title);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }
    public Myad(List<Data> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data item = data.get(position);
        holder.author.setText(item.getaAuther());
        holder.title.setText(item.getaTitle());
        holder.thumbnail.setImageResource(item.getaThumbnail());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


}