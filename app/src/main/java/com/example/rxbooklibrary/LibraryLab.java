package com.example.rxbooklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rxbooklibrary.database.LibraryCursorWrapper;
import com.example.rxbooklibrary.database.LibraryDBHelper;
import com.example.rxbooklibrary.database.LibraryDbSchema;
import com.example.rxbooklibrary.models.VolumeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LibraryLab {
    private static LibraryLab mLibraryLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private LibraryLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new LibraryDBHelper(mContext)
                .getWritableDatabase();
    }

    public static LibraryLab get(Context context) {
        if (mLibraryLab == null) {
            mLibraryLab = new LibraryLab(context);
        }
        return mLibraryLab;
    }

    //Add books to the list
    public void addBook(VolumeInfo volumeInfo) {
        ContentValues values = getContentValues(volumeInfo);
        mDatabase.insert(LibraryDbSchema.LibraryTable.NAME, null, values);
    }

    //To put in the database
    private static ContentValues getContentValues(VolumeInfo volumeInfo) {
        ContentValues values = new ContentValues();
        values.put(LibraryDbSchema.LibraryTable.Cols.UUID, volumeInfo.getID().toString());
        values.put(LibraryDbSchema.LibraryTable.Cols.TITLE, volumeInfo.getTitle());
        values.put(LibraryDbSchema.LibraryTable.Cols.PUBLISHER, volumeInfo.getPublisher());
        values.put(LibraryDbSchema.LibraryTable.Cols.DESCRIPTION, volumeInfo.getDescription());

        return values;
    }

    public void updateLibrary(VolumeInfo volumeInfo) {
        String uuidString = volumeInfo.getID().toString();
        ContentValues values = getContentValues(volumeInfo);

        //If you put the string directly not using
        // ? and String [],String itself might itself
        // contain SQL code.It is called SQL injection attack.
        //If you use ? it will treat the string as a
        // value not code.So always use ?
        mDatabase.update(LibraryDbSchema.LibraryTable.NAME, values,
                LibraryDbSchema.LibraryTable.Cols.UUID + " + ?",
                new String[]{uuidString});
    }

    //Delete a book
    public void deleteBook(VolumeInfo volumeInfo) {

        String uuidString = volumeInfo.getID().toString();
        //Using ? to prevent sql injection
        mDatabase.delete(LibraryDbSchema.LibraryTable.NAME,
                LibraryDbSchema.LibraryTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private LibraryCursorWrapper queryBooks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                LibraryDbSchema.LibraryTable.NAME,
                null,//Collumns-null selects all colums
                whereClause,
                whereArgs,
                null,//groupBy
                null,//having
                null//orderBy
        );
        return new LibraryCursorWrapper(cursor);
    }

    //Using a object easily can if later using differnet list to be changed
    public List<VolumeInfo> getBooks() {
        List<VolumeInfo> mVolumeInfos = new ArrayList<>();
        LibraryCursorWrapper mCursorWrapper = queryBooks(null, null);

        try{
            //Start with first one and isAfterLast tells
            //that the pointer is off the end of the datasheet
            mCursorWrapper.moveToFirst();
            while(!mCursorWrapper.isAfterLast()){
                mVolumeInfos.add(mCursorWrapper.getInfo());
                mCursorWrapper.moveToNext();
            }

        }finally {
            //Close the cursor
            mCursorWrapper.close();
        }
        return mVolumeInfos;
    }

    //Get particular crime
    public VolumeInfo getBook(UUID id){
        LibraryCursorWrapper cursor = queryBooks(LibraryDbSchema.LibraryTable.Cols.UUID +"= ?",new String[]{id.toString()});
        try{
            if(cursor.getCount()==0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getInfo();
        }finally {
            cursor.close();
        }
    }


}
