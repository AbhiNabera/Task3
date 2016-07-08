package com.example.abhinabera.task3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Abhi Nabera on 7/3/2016.
 */
public class DataBaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 1;
    public String CreateQuery = "CREATE TABLE "+ TableData.TableInfo.TableName +" ("+TableData.TableInfo.Title+" TEXT, "+TableData.TableInfo.Year+" INTEGER, "+TableData.TableInfo.Rated+" TEXT, "+TableData.TableInfo.Released+" TEXT, "+TableData.TableInfo.Runtime+" TEXT, "+TableData.TableInfo.Genre+" TEXT, "+TableData.TableInfo.Director+" TEXT, "+TableData.TableInfo.Plot+" TEXT, "+TableData.TableInfo.Language+" TEXT, "+TableData.TableInfo.Poster+" TEXT, "+TableData.TableInfo.imdbRating+" REAL, "+TableData.TableInfo.Type+" TEXT);";


    public DataBaseOperations(Context context) {
        super(context, TableData.TableInfo.DataBaseName, null, database_version);
        Log.d("DataBaseOperations", "DataBaseCreated");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(CreateQuery);
        Log.d("DataBaseOperations", "TableCreated");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void putInformation(DataBaseOperations dop, String title, int year, String rated, String released, String runtime, String genre, String director, String plot, String language, String poster, double imdbrating, String type){

        SQLiteDatabase SQ =dop.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(TableData.TableInfo.Title, title);
        cv.put(TableData.TableInfo.Year, year);
        cv.put(TableData.TableInfo.Rated, rated);
        cv.put(TableData.TableInfo.Released, released);
        cv.put(TableData.TableInfo.Runtime, runtime);
        cv.put(TableData.TableInfo.Genre, genre);
        cv.put(TableData.TableInfo.Director, director);
        cv.put(TableData.TableInfo.Plot, plot);
        cv.put(TableData.TableInfo.Language, language);
        cv.put(TableData.TableInfo.Poster, poster);
        cv.put(TableData.TableInfo.imdbRating, imdbrating);
        cv.put(TableData.TableInfo.Type, type);
        long k = SQ.insert(TableData.TableInfo.TableName, null, cv);
        Log.d("DataBaseOperations", "OneRowInsereted");

    }

    public Cursor getInformation(DataBaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.Title, TableData.TableInfo.Year, TableData.TableInfo.Rated, TableData.TableInfo.Released, TableData.TableInfo.Runtime, TableData.TableInfo.Genre, TableData.TableInfo.Director, TableData.TableInfo.Plot, TableData.TableInfo.Language, TableData.TableInfo.Poster, TableData.TableInfo.imdbRating, TableData.TableInfo.Type};
        Cursor CR = SQ.query(TableData.TableInfo.TableName, coloumns, null, null, null, null, null);
        return CR;
    }

    public Cursor getInformation1(DataBaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.Title, TableData.TableInfo.Year, TableData.TableInfo.Rated, TableData.TableInfo.Released, TableData.TableInfo.Runtime, TableData.TableInfo.Genre, TableData.TableInfo.Director, TableData.TableInfo.Plot, TableData.TableInfo.Language, TableData.TableInfo.Poster, TableData.TableInfo.imdbRating, TableData.TableInfo.Type};
        Cursor CR = SQ.query(TableData.TableInfo.TableName, coloumns, null, null, null, null, TableData.TableInfo.imdbRating);
        return CR;
    }



}
