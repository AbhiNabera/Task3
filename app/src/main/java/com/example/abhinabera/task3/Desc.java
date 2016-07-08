package com.example.abhinabera.task3;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Abhi Nabera on 7/5/2016.
 */
public class Desc extends MainActivity {

    String i;
    int j = 0;
    Context context;
    ImageView poster;
    TextView title, year, rated, released, runtime, director, genre, plot, language, imdbrating;
    String[] Title = new String[20]; Integer[] Year = new Integer[20]; String[] Rated = new String[20]; String[] Released = new String[20];
    String[] Runtime = new String[20]; String[] Director = new String[20]; String[] Genre = new String[20]; String[] Plot = new String[20];
    String[] Language = new String[20]; Double[] imdbRating = new Double[20]; String[] Poster = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc);
        poster = (ImageView) findViewById(R.id.poster);
        title = (TextView) findViewById(R.id.title);
        year = (TextView) findViewById(R.id.year);
        rated = (TextView) findViewById(R.id.rated);
        released = (TextView) findViewById(R.id.released);
        runtime = (TextView) findViewById(R.id.runtime);
        director = (TextView) findViewById(R.id.director);
        genre = (TextView) findViewById(R.id.genre);
        plot = (TextView) findViewById(R.id.plot);
        language = (TextView) findViewById(R.id.language);
        imdbrating = (TextView) findViewById(R.id.imdbrating);
        i = getIntent().getExtras().getString("key");


        android.support.v7.widget.Toolbar my_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        getSupportActionBar().setTitle(R.string.app_name);


        DataBaseOperations dop = new DataBaseOperations(ctx);
        Cursor CR = dop.getInformation(dop);
        CR.moveToFirst();
        do{
            Title[j] = CR.getString(0);
            Year[j] = CR.getInt(1);
            Rated[j] = CR.getString(2);
            Released[j] = CR.getString(3);
            Runtime[j] = CR.getString(4);
            Director[j] = CR.getString(5);
            Genre[j] = CR.getString(6);
            Plot[j] = CR.getString(7);
            Language[j] = CR.getString(8);
            imdbRating[j] = CR.getDouble(10);
            Poster[j] = CR.getString(9);
            j++;
        }while(CR.moveToNext());

        for(j=0; j<20; j++){
            if(i.equals(Title[j])){
                title.setText("Title: " + Title[j]);
                year.setText("Year: " + Year[j]);
                rated.setText("Rating: " + Rated[j]);
                released.setText("Released: " + Released[j]);
                runtime.setText("Runtime: " + Runtime[j]);
                director.setText("Director: " + Director[j]);
                genre.setText("Genre: " + Genre[j]);
                plot.setText("Plot: " + Plot[j]);
                language.setText("Language: " + Language[j]);
                imdbrating.setText("imdbRating: " + imdbRating[j]);
                Picasso.with(context)
                        .load(Poster[j])
                        .resize(150, 150)
                        .into(poster);

            }
        }





    }

}
