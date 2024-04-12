package com.example.everyss;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

public class HelpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    List<Help> data= Collections.emptyList();
    Help current;
    Help cur;
    String id,name;
    int currentPos=0;


    // create constructor to innitilize context and data sent from MainActivity
    public HelpAdapter(Context context, List<Help> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;

    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_item, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        final Help current=data.get(position);
        myHolder.textName.setText(current.name);
        myHolder.textProblem.setText("Problem: " + current.problem);
        myHolder.textPurpose.setText("Purpose: " + current.purpose+"$");
        myHolder.textEarning.setText("Earned: " + current.earning+"$");
        myHolder.textId.setText(current.idd);


       /* myHolder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UserPosts.class);
                intent.putExtra("id",current.idd);
                intent.putExtra("name",current.name);
                context.startActivity(intent);
            }
        });*/

     //   myHolder.textPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

      /*  Glide.with(context).load(current.url)
        .apply(new RequestOptions()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image))
                .into(myHolder.image);*/
        // load image into imageview using glide
      /* Glide.with(context).load(current.url).placeholder(R.drawable.ic_img_error)
                .into(myHolder.image);*/

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder  {


        TextView textName;
        ImageView image;
        TextView textProblem;
        TextView textPurpose;
        TextView textEarning;
        TextView textId;
        CardView rel;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textName= (TextView) itemView.findViewById(R.id.name);
            image= (ImageView) itemView.findViewById(R.id.image1);
            textProblem = (TextView) itemView.findViewById(R.id.problem);
            textPurpose = (TextView) itemView.findViewById(R.id.purpose);
            textEarning = (TextView) itemView.findViewById(R.id.earning);
            textId = (TextView) itemView.findViewById(R.id.iddd);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,userprofile.class);
                    intent.putExtra("id",textId.getText().toString());
                    intent.putExtra("name",textName.getText().toString());
                    context.startActivity(intent);
                }
            });

        }


    }



}
