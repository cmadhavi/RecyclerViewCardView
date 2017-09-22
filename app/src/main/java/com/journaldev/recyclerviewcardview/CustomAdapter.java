package com.journaldev.recyclerviewcardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Item> moviesList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;
        public  ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewName);
            imageView = (ImageView) view.findViewById(R.id.imageView);
          ////  year = (TextView) view.findViewById(R.id.textViewVersion);
        }
    }


    public CustomAdapter(List<Item> moviesList) {
        this.moviesList = moviesList;
    }

    //lgkj0eignpfkm
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item movie = moviesList.get(position);
        holder.title.setText(movie.getName());

       // holder.year.setText(movie.getId());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}