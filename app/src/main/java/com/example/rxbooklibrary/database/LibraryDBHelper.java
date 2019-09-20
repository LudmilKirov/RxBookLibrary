package com.example.rxbooklibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.rxbooklibrary.database.LibraryDbSchema.*;

public class LibraryDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="library.db";
    public static final int DATABASE_VERSION = 1;

    public LibraryDBHelper(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_LIBRARY_TABLE=
                "CREATE TABLE "+
                LibraryTable.NAME+"(" +
                " _ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LibraryTable.Cols.UUID.toString()+
                LibraryTable.Cols.TITLE+" TEXT NOT NULL,"+
                LibraryTable.Cols.PUBLISHER +"TEXT NOT NULL," +
                LibraryTable.Cols.DESCRIPTION+"TEXT NOT NULL,"+")";

        sqLiteDatabase.execSQL(SQL_CREATE_LIBRARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LibraryTable.NAME);
        onCreate(sqLiteDatabase);
    }
}
