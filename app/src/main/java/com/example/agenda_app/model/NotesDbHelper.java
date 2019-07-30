package com.example.agenda_app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public boolean deleteAnyNote(int id){
        SQLiteDatabase db = sInstance.getWritableDatabase();
        String selection = NotesContract.NotesEntry.ID + " LIKE ?";
        String[] selectionArgs = { id + ""};
        return db.delete(NotesContract.NotesEntry.TABLE_NAME, selection, selectionArgs) > 0;
    }

    public void deleteAllNote(){
        SQLiteDatabase db = sInstance.getWritableDatabase();
        db.execSQL(NotesContract.DELETE_ALL_NOTES);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Note> readNotes(){
        SQLiteDatabase db = sInstance.getReadableDatabase();
        String sortOrder =
                NotesContract.NotesEntry.GRP + " DESC";

        Cursor cursor = db.query(
                NotesContract.NotesEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List<Note> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);

            double duration = cursor.getDouble(1);

            LocalDateTime date = LocalDateTime.of(cursor.getInt(4),cursor.getInt(3),cursor.getInt(2),
                    cursor.getInt(5),cursor.getInt(6),cursor.getInt(7));

            State state;
            if(cursor.getString(8).equals("COMPLETE")){
                state = State.COMPLETE;
            } else if (cursor.getString(8).equals("INCOMPLETE")){
                state = State.INCOMPLETE;
            } else {
                state = State.DOING;
            }

            Boolean priority = cursor.getInt(9) > 0;

            String nameGroup = cursor.getString(10);

            String description = cursor.getString(11);

            items.add(new Note(id,description,duration,date,state,priority,nameGroup));
        }
        cursor.close();
        return items;
    }

}
