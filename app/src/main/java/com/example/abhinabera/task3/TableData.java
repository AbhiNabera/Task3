package com.example.abhinabera.task3;

import android.provider.BaseColumns;

/**
 * Created by Abhi Nabera on 7/3/2016.
 */
public class TableData {

    public TableData(){

    }

    public static abstract class TableInfo implements BaseColumns{

        public static final String Title = "Title";
        public static final String Year = "Year";
        public static final String Rated = "Rated";
        public static final String Released = "Released";
        public static final String Runtime = "Runtime";
        public static final String Genre = "Genre";
        public static final String Director = "Director";
        public static final String Plot = "Plot";
        public static final String Language = "Language";
        public static final String Poster = "Poster";
        public static final String imdbRating = "imdbRating";
        public static final String Type = "Type";
        public static final String DataBaseName = "Movies_Series.db";
        public static final String TableName = "Description";


    }
}
