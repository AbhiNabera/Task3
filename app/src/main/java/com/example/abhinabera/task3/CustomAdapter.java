package com.example.abhinabera.task3;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Abhi Nabera on 7/4/2016.
 */
public class CustomAdapter extends BaseAdapter {

    String[] title = new String[20];
    String[] genre = new String[20];
    String[] poster = new String[20];
    Context context;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Grid_View gridView, String[] Poster, String[] Title, String[] Genre){

        title = Title;
        genre = Genre;
        poster = Poster;
        context = gridView;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder{
        TextView title,genre;
        ImageView poster;

    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {



        final Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.custom, null);
        holder.title = (TextView) rowView.findViewById(R.id.title);
        holder.genre = (TextView) rowView.findViewById(R.id.genre);
        holder.poster = (ImageView) rowView.findViewById(R.id.imageView);

        holder.title.setText(title[i]);
        holder.genre.setText(genre[i]);
        Picasso.with(context)
                .load(poster[i])
                .resize(80, 80)
                .into(holder.poster);


        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                 //TODO Auto-generated method stub
                if(title[i] == null){
                    Toast.makeText(context, "Please select a movie/series.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent j = new Intent(context, Desc.class);
                    j.putExtra("key", title[i]);
                    context.startActivity(j);
                }
            }

        });

        return rowView;
    }
}
