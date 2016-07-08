package com.example.abhinabera.task3;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhi Nabera on 7/3/2016.
 */
public class Grid_View extends MainActivity {


    int i =0;
    String[] Title = new String[20];
    String[] Genre = new String[20];
    String[] Poster = new String[20];
    GridView gv;
    private ArrayList<String> title;
    private ArrayList<String> filteredtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        gv = (GridView) findViewById(R.id.gridView);
        android.support.v7.widget.Toolbar my_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        setSupportActionBar(my_toolbar);



        DataBaseOperations dop = new DataBaseOperations(ctx);
        Cursor CR = dop.getInformation(dop);
        CR.moveToFirst();
        do{
            Title[i] = CR.getString(0);
            Genre[i] = CR.getString(5);
            Poster[i] = CR.getString(9);
            i++;
        }while(CR.moveToNext());



        gv.setAdapter(new CustomAdapter(this, Poster, Title, Genre));



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menu_back:
                Intent i = new Intent(Grid_View.this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.menu_sort:
                int j = 0;
                DataBaseOperations dop = new DataBaseOperations(ctx);
                Cursor CR = dop.getInformation1(dop);
                CR.moveToFirst();
                do{
                    Title[j] = CR.getString(0);
                    Genre[j] = CR.getString(5);
                    Poster[j] = CR.getString(9);
                    j++;
                }while(CR.moveToNext());

                gv.setAdapter(new CustomAdapter(this, Poster, Title, Genre));
        }

        return super.onOptionsItemSelected(item);
    }
}
