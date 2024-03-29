package com.example.agenda_app.model;

import android.provider.BaseColumns;

@Deprecated
public class NotesContract {

    private  NotesContract() {}

    public static abstract class NotesEntry implements BaseColumns {
        public static final String TABLE_NAME ="Notes";

        public static final String ID = "id";
        public static final String DURATION = "duration";
        public static final String DAY = "day";
        public static final String MONTH = "month";
        public static final String YEAR = "year";
        public static final String HOUR = "hour";
        public static final String MINUTE = "minute";
        public static final String SECONDS = "seconds";
        public static final String STATE = "state";
        public static final String PRIORITY = "priority";
        public static final String GRP = "name_group";
        public static final String DES = "description";
    }

    public static final String CREATE_TABLE = "CREATE TABLE " + NotesEntry.TABLE_NAME
            + " (" + NotesEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NotesEntry.DURATION + " REAL,"
            + NotesEntry.DAY + " INTEGER NOT NULL,"
            + NotesEntry.MONTH + " INTEGER NOT NULL,"
            + NotesEntry.YEAR + " INTEGER NOT NULL,"
            + NotesEntry.HOUR + " INTEGER NOT NULL,"
            + NotesEntry.MINUTE + " INTEGER NOT NULL,"
            + NotesEntry.SECONDS + " INTEGER NULL,"
            + NotesEntry.STATE + " TEXT NOT NULL,"
            + NotesEntry.PRIORITY + " INTEGER NOT NULL,"
            + NotesEntry.GRP + " TEXT,"
            + NotesEntry.DES + " TEXT NOT NULL)";

    public static final String CREATE_INDEX = "CREATE INDEX index_gruop ON " + NotesEntry.TABLE_NAME
            + " (" + NotesEntry.GRP + " )";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + NotesEntry.TABLE_NAME;

    public static final String DELETE_ALL_NOTES =
            "DELETE FROM " + NotesEntry.TABLE_NAME;

}
