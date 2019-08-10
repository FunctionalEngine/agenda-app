package com.example.agenda_app.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("Select * from notes")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM notes")
    void deleteAllNotes();

    @Query("select count(*) from notes")
    int getSize();

    @Query("select * from notes where Namegroup LIKE :name_group")
    List<Note> getNoteGroup(String name_group);
}
