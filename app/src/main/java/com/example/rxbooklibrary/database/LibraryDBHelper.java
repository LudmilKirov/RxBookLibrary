package com.example.rxbooklibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rxbooklibrary.database.LibraryDbSchema.*;

public class LibraryDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "LibraryDBHelper";
    // Database Name
    public static final String DATABASE_NAME = "library.db";
    // Database Version
    public static final int DATABASE_VERSION = 1;

    public LibraryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create notes table
        final String SQL_CREATE_LIBRARY_TABLE =
                "CREATE TABLE " +
                        LibraryTable.NAME +
                        " ( " +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " TEXT NOT NULL, " +
                        LibraryTable.Cols.TITLE +
                        " TEXT NOT NULL, " +
                        LibraryTable.Cols.PUBLISHER +
                        " TEXT NOT NULL, " +
                        LibraryTable.Cols.DESCRIPTION
                        + " ) ";

        sqLiteDatabase.execSQL(SQL_CREATE_LIBRARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LibraryTable.NAME);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String title, String publisher, String decription) {
        //Create
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LibraryTable.Cols.TITLE, title);
        contentValues.put(LibraryTable.Cols.PUBLISHER, publisher);
        contentValues.put(LibraryTable.Cols.DESCRIPTION, publisher);
        //Insert method return -1 if it is not inserted
        long result = db.insert(LibraryTable.NAME, null, contentValues);
        //If result is equal to -1 then it will return false
        return (result != -1);
    }
}
