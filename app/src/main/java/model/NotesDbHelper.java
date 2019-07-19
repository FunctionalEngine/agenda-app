package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NotesDbHelper extends SQLiteOpenHelper {

    private static NotesDbHelper sInstance;

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;


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

    public void insertInto(Note note){
        SQLiteDatabase db = sInstance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotesContract.NotesEntry.DURATION, note.getDuration());
        values.put(NotesContract.NotesEntry.HOUR, note.getTime().getHour());
        values.put(NotesContract.NotesEntry.MINUTE, note.getTime().getMinutes());
        values.put(NotesContract.NotesEntry.SECONDS, note.getTime().getSeconds());
        values.put(NotesContract.NotesEntry.STATE, note.getState() + "");
        values.put(NotesContract.NotesEntry.PRIORITY, note.getPriority() ? 1 : 0);
        values.put(NotesContract.NotesEntry.GRP, note.getGroup());
        values.put(NotesContract.NotesEntry.DES, note.getDescription());

        long newRowId = db.insert(NotesContract.NotesEntry.TABLE_NAME, null, values);

    }


}
