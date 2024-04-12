package com.example.everyss;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<News> data= Collections.emptyList();
    Help current;
    int currentPos=0;


    // create constructor to innitilize context and data sent from MainActivity
    public NewsAdapter(Context context, List<News> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_news, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        News current=data.get(position);
        myHolder.textName.setText( current.name);
        myHolder.textTitle.setText( current.title);
        myHolder.textDesc.setText( current.description);



    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{


        ImageView image;
        TextView textTitle;
        TextView textDesc ;
        TextView textName;


        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            int position = getAdapterPosition();
            image= (ImageView) itemView.findViewById(R.id.imagenews);
            textTitle = (TextView) itemView.findViewById(R.id.title);
            textDesc = (TextView) itemView.findViewById(R.id.description);
            textName = (TextView) itemView.findViewById(R.id.Name);


        }

    }

}
