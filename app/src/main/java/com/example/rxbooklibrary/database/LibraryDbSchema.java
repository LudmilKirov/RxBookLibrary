package com.example.rxbooklibrary.database;

public class LibraryDbSchema {
    public static final class LibraryTable{
        public static final String NAME="library";

        public static final class Cols{
            public static final String TITLE = "title";
            public static final String PUBLISHER = "publisher";
            public static final String DESCRIPTION = "description";
        }
    }
}
