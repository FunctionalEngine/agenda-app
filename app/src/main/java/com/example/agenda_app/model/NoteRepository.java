package com.example.agenda_app.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private final NoteDAO noteDao;
    private final LiveData<List<Note>> allNotes;

    public NoteRepository(Application application, Boolean AllowMainThreadQueries) {
        NoteDatabase db = NoteDatabase.getInstance(application, AllowMainThreadQueries);
        noteDao = db.notesDAO();
        this.allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDAO asyncNoteDao;

        InsertAsyncTask(NoteDAO dao) {
            this.asyncNoteDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... notes) {
            for(Note note : notes) { asyncNoteDao.insertNote(note); }
            return null;
        }
    }
}
