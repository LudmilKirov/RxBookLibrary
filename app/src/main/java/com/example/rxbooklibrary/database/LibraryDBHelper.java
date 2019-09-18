package com.example.rxbooklibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.rxbooklibrary.database.LibraryContract.*;

public class LibraryDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="library.db";
    public static final int DATABSE_VERSION = 1;

    public LibraryDBHelper(Context context) {
        super(context,DATABASE_NAME , null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_LIBRARY_TABLE="CREATE TABLE "+
                LibraryEntry.TABLE_NAME+" (" +
                LibraryEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LibraryEntry.COLUMN_NAME+" TEXT NOT NULL,"+
                LibraryEntry.COLUMN_TIMESTAMP +"TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_LIBRARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LibraryEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
