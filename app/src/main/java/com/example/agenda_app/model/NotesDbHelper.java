package com.example.agenda_app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;



public class NotesDbHelper extends SQLiteOpenHelper {

    private static NotesDbHelper sInstance;

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 3;


    public static synchronized NotesDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NotesDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private NotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NotesContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(NotesContract.CREATE_INDEX);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(NotesContract.DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertInto(Note note){
        SQLiteDatabase db = sInstance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotesContract.NotesEntry.DURATION, note.getDuration());
        values.put(NotesContract.NotesEntry.DAY, note.getTime().getDayOfMonth());
        values.put(NotesContract.NotesEntry.MONTH, note.getTime().getMonthValue());
        values.put(NotesContract.NotesEntry.YEAR, note.getTime().getYear());
        values.put(NotesContract.NotesEntry.HOUR, note.getTime().getHour());
        values.put(NotesContract.NotesEntry.MINUTE, note.getTime().getMinute());
        values.put(NotesContract.NotesEntry.SECONDS, note.getTime().getSecond());
        values.put(NotesContract.NotesEntry.STATE, note.getState() + "");
        values.put(NotesContract.NotesEntry.PRIORITY, note.getPriority() ? 1 : 0);
        values.put(NotesContract.NotesEntry.GRP, note.getGroup());
        values.put(NotesContract.NotesEntry.DES, note.getDescription());

        long newRowId = db.insert(NotesContract.NotesEntry.TABLE_NAME, null, values);

    }


}
