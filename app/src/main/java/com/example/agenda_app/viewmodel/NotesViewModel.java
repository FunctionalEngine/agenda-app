package com.example.agenda_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agenda_app.model.Note;

import java.util.List;

public class NotesViewModel extends ViewModel {
    MutableLiveData<List<Note>> notes;

    public LiveData<List<Note>> getNotes(){
        if(notes == null){
            notes = new MutableLiveData<List<Note>>();
            loadNotes();
        }

        return notes;
    }

    private void loadNotes() {

    }
}
