package com.example.abhinabera.task3;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends ActionBarActivity {

    EditText name;
    Button save,view;
    String url1;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        save = (Button) findViewById(R.id.save);
        view = (Button) findViewById(R.id.view);

        android.support.v7.widget.Toolbar my_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        getSupportActionBar().setTitle(R.string.app_name);



        url1 = name.getText().toString();
        url1 = url1.replace(' ' , '+');

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url1 = name.getText().toString();
                url1 = url1.replace(' ' , '+');
                new JSONTask().execute("http://www.omdbapi.com/?t=" + url1 + "&y=&plot=short&r=json");
            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Grid_View.class);
                startActivity(i);
            }
        });

    }

    public class JSONTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... urls) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while((line = reader.readLine()) != null){
                    buffer.append(line);

                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);

                String title = parentObject.getString("Title");
                int year = parentObject.getInt("Year");
                String rated = parentObject.getString("Rated");
                String released = parentObject.getString("Released");
                String runtime = parentObject.getString("Runtime");
                String genre = parentObject.getString("Genre");
                String director = parentObject.getString("Director");
                String plot = parentObject.getString("Plot");
                String language = parentObject.getString("Language");
                String poster = parentObject.getString("Poster");
                double imdbrating = parentObject.getDouble("imdbRating");
                String type = parentObject.getString("Type");

                DataBaseOperations DB = new DataBaseOperations(ctx);
                DB.putInformation(DB, title, year, rated, released, runtime, genre, director, plot, language, poster, imdbrating, type);


                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();}
                finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(ctx, "Saved to database.", Toast.LENGTH_SHORT).show();

        }
    }


}

