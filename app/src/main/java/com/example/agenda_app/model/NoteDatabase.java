package com.example.agenda_app.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Note.class,exportSchema = false, version = 1)
@TypeConverters({TypeConvert.class})
public abstract class NoteDatabase extends RoomDatabase {
    private static final String DB_NAME = "notes_db";
    private static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(Context context, Boolean allowMainQueries) {
        if (instance == null){
            if(allowMainQueries){
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        NoteDatabase.class, DB_NAME).allowMainThreadQueries().build();
            } else {
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        NoteDatabase.class, DB_NAME).build();
            }
        }
        return instance;
    }

    public abstract NoteDAO notesDAO();
}
