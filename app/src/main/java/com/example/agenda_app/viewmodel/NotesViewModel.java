package com.example.agenda_app.viewmodel;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agenda_app.model.Note;
import com.example.agenda_app.model.NotesDbHelper;

import java.util.List;

public class NotesViewModel extends ViewModel {
    MutableLiveData<List<Note>> notes = new MutableLiveData<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LiveData<List<Note>> getNotes(Context context){
        if(notes == null){
            loadNotes(context);
        }
        return notes;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadNotes(Context context) {
        this.notes.postValue(NotesDbHelper.getInstance(context).readNotes());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNote(Context context, Note note) {
        NotesDbHelper.getInstance(context).insertInto(note);
        loadNotes(context);
    }
}
