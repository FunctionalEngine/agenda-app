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

@Deprecated
public class NotesDbHelper extends SQLiteOpenHelper {

    // Singleton instance
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

    public int getSize(){
        SQLiteDatabase db = sInstance.getWritableDatabase();
        Cursor mCount= db.rawQuery("select count(*) from " + NotesContract.NotesEntry.TABLE_NAME, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;
    }

    public boolean deleteAnyNote(int id){
        SQLiteDatabase db = sInstance.getWritableDatabase();
        String selection = NotesContract.NotesEntry.ID + " LIKE ?";
        String[] selectionArgs = { id + ""};
        return db.delete(NotesContract.NotesEntry.TABLE_NAME, selection, selectionArgs) > 0;
    }

    public void deleteAllNotes(){
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
            int id = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.ID));

            Double duration;
            if(cursor.getDouble(cursor.getColumnIndex(NotesContract.NotesEntry.DURATION)) == 0.0){
                duration = null;
            } else {
                duration = cursor.getDouble(cursor.getColumnIndex(NotesContract.NotesEntry.DURATION));
            }

            LocalDateTime date = LocalDateTime.of(cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.YEAR)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.MONTH)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.DAY)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.HOUR)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.MINUTE)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.SECONDS)));

            State state;
            if(cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.STATE)).equals("COMPLETE")){
                state = State.COMPLETE;
            } else if (cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.STATE)).equals("INCOMPLETE")){
                state = State.INCOMPLETE;
            } else {
                state = State.DOING;
            }

            String nameGroup = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.GRP));

            Boolean priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.PRIORITY)) > 0;

            String description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.DES));

            items.add(new Note(id,description,duration,date,state,priority,nameGroup));
        }
        cursor.close();
        return items;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotesGroup readGroupNotes(String nameGroup){
        SQLiteDatabase db = sInstance.getReadableDatabase();

        String selection = NotesContract.NotesEntry.GRP + " = ?";
        String[] selectionArgs = { nameGroup };

        Cursor cursor = db.query(
                NotesContract.NotesEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        NotesGroup notesGroup = new NotesGroup(nameGroup);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.ID));

            Double duration;
            if(cursor.getDouble(cursor.getColumnIndex(NotesContract.NotesEntry.DURATION)) == 0.0){
                duration = null;
            } else {
                duration = cursor.getDouble(cursor.getColumnIndex(NotesContract.NotesEntry.DURATION));
            }

            LocalDateTime date = LocalDateTime.of(cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.YEAR)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.MONTH)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.DAY)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.HOUR)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.MINUTE)),
                    cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.SECONDS)));

            State state;
            if(cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.STATE)).equals("COMPLETE")){
                state = State.COMPLETE;
            } else if (cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.STATE)).equals("INCOMPLETE")){
                state = State.INCOMPLETE;
            } else {
                state = State.DOING;
            }

            Boolean priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.PRIORITY)) > 0;

            String description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.DES));

            notesGroup.add(new Note(id,description,duration,date,state,priority,nameGroup));
        }
        cursor.close();
        return notesGroup;
    }


}
