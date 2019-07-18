package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.acl.NotOwnerException;

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
        sqLiteDatabase.execSQL("CREATE TABLE " + NotesContract.NotesEntry.TABLE_NAME
                + " (" + NotesContract.NotesEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NotesContract.NotesEntry.DURATION + " REAL,"
                + NotesContract.NotesEntry.HOUR + " INTEGER NOT NULL,"
                + NotesContract.NotesEntry.MINUTE + " INTEGER NOT NULL,"
                + NotesContract.NotesEntry.SECONDS + " INTEGER NOT NULL,"
                + NotesContract.NotesEntry.STATE + " TEXT NOT NULL,"
                + NotesContract.NotesEntry.PRIORITY + " INTEGER NOT NULL,"
                + NotesContract.NotesEntry.GRP + " TEXT,"
                + NotesContract.NotesEntry.DES + " TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE INDEX index_gruop ON " + NotesContract.NotesEntry.TABLE_NAME
                + " (" + NotesContract.NotesEntry.GRP + " )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
