package com.example.rxbooklibrary.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.rxbooklibrary.models.VolumeInfo;

import static com.example.rxbooklibrary.database.LibraryDbSchema.LibraryTable.*;

public class LibraryCursorWrapper extends CursorWrapper {

    public LibraryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public VolumeInfo getInfo(){
        String title = getString(getColumnIndex(Cols.TITLE));
        String publisher = getString(getColumnIndex(Cols.PUBLISHER));
        String description = getString(getColumnIndex(Cols.DESCRIPTION));

        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setTitle(title);
        volumeInfo.setPublisher(publisher);
        volumeInfo.setDescription(description);

        return volumeInfo;
    }
}
