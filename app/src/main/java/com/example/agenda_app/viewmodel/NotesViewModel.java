package com.example.agenda_app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.agenda_app.model.Note;
import com.example.agenda_app.model.NoteRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private final NoteRepository noteRepository;
    private final LiveData<List<Note>> notes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application, true);
        notes = noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getNotes(){
        return notes;
    }

    public void insertNote(Note note) {
        noteRepository.insert(note);
    }
}
