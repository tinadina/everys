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

public class TovarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Tovar> data;
    /*String[] img = new String[]{"https://images-na.ssl-images-amazon.com/images/I/61PIjLe6SVL._AC_SL1500_.jpg", "https://images-na.ssl-images-amazon.com/images/I/61PIjLe6SVL._AC_SL1500_.jpg","https://images.crateandbarrel.com/is/image/Crate/TateQueenBedCharSHS15_16x9/?$web_zoom$&190411135503&wid=450&hei=450","https://cdn.shopify.com/s/files/1/1848/5313/products/side_table_500_x_500_x_450_800x.jpg?v=1558103828", "https://assets.eflorist.com/assets/products/PHR_/TEV12-7A.jpg",
    "https://images.kz.prom.st/89628692_begovaya-dorozhka.jpg", "https://dictionary.cambridge.org/ru/images/thumb/cup_noun_002_09489.jpg?version=5.0.81","https://cdn.jkexer.com/comm/upimage/p_180810_07449.jpg",
"https://dictionary.cambridge.org/ru/images/thumb/cup_noun_002_09489.jpg?version=5.0.81",    "https://cdn.britannica.com/27/150727-050-EB9EB051/guitar.jpg",
            "https://motor.ru/thumb/2250x0/filters:quality(75):no_upscale()/imgs/2019/07/03/13/3438772/87750f830d93fba20ec0f0e03034fadfc454d509.jpg","https://collectionapi.metmuseum.org/api/collection/v1/iiif/501788/1467363/main-image", "https://static-eu.insales.ru/images/products/1/878/116941678/braslet-iz-agata-lev.jpg",
    "https://www.americasbest.com/medias/sys_master/product_images/product_images/hb8/h24/8998040731678/139812-01.jpg"};
*/
    Help current;
    private OnNoteListener mOnNoteListener;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public TovarAdapter(Context context, ArrayList<Tovar> data, OnNoteListener onNoteListener){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.mOnNoteListener = onNoteListener;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //  View view=inflater.inflate(R.layout.list_items, parent,false);
         View view=inflater.inflate(R.layout.grid_items, parent,false);

        MyHolder holder=new MyHolder(view, mOnNoteListener);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Tovar current=data.get(position);
        myHolder.textTovar.setText(current.tovar);
        myHolder.textPrice.setText( current.price);



        Picasso.get().load( current.url).into(myHolder.ivFish);
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
            textTovar= (TextView) itemView.findViewById(R.id.product);
            ivFish= (ImageView) itemView.findViewById(R.id.tovar_img);
            textPrice = (TextView) itemView.findViewById(R.id.price);
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
