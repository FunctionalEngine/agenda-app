package com.example.agenda_app.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Note.class,exportSchema = false, version = 1)
@TypeConverters({TypeConvert.class})
public abstract class NotesDatabase extends RoomDatabase {
    private static final String DB_NAME = "notes_db";
    private static NotesDatabase instance;

    public static synchronized NotesDatabase getInstance(Context context, Boolean allowMainQueries) {
        if (instance == null){
            if(allowMainQueries){
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        NotesDatabase.class, DB_NAME).allowMainThreadQueries().build();
            } else {
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        NotesDatabase.class, DB_NAME).build();
            }
        }
        return instance;
    }

    public abstract NotesDAO notesDAO();
}
