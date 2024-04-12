package com.example.everyss;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Share> data;
    Help current;
    private OnNoteListener mOnNoteListener;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public ShareAdapter(Context context, ArrayList<Share> data, OnNoteListener onNoteListener){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.mOnNoteListener = onNoteListener;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_item, parent,false);
        MyHolder holder=new MyHolder(view, mOnNoteListener);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Share current=data.get(position);
        myHolder.textTovar.setText(current.tovar);
        myHolder.textPrice.setText( current.price);



        Picasso.get().load("https://ibb.co/j6KFLNx ").into(myHolder.ivFish);
       // Glide.with(context).load("https://ibb.co/j6KFLNx ").apply(options).into(myHolder.ivFish);

        // load image into imageview using glide
       /* Glide.with(context).load( current.url)
                .placeholder(R.drawable.bgg)
                .error(R.drawable.loading_shape)
                .into(myHolder.ivFish);*/

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textTovar;
        ImageView ivFish;
        TextView textPrice;
        OnNoteListener onNoteListener;
        // create constructor to get widget reference
        public MyHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            textTovar= (TextView) itemView.findViewById(R.id.problem);
            ivFish= (ImageView) itemView.findViewById(R.id.image1);
            textPrice = (TextView) itemView.findViewById(R.id.earning);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
public interface OnNoteListener{
        void onNoteClick(int position);
}

}
