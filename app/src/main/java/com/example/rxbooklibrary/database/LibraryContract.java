package com.example.rxbooklibrary.database;

import android.provider.BaseColumns;

public class LibraryContract {

    private LibraryContract(){};


    public static final class LibraryEntry implements BaseColumns{

        public static final String TABLE_NAME = "library";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_TIMESTAMP="timestamp";
    }
}
