package com.example.agenda_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.agenda_app.model.Note;
import com.example.agenda_app.ui.NoteViewAdapter;
import com.example.agenda_app.viewmodel.NotesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter noteViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotesViewModel model = ViewModelProviders.of(this).get(NotesViewModel.class);
        model.getNotes().observe(this, notes -> {
            showNotes(notes);
        });

        setContentView(R.layout.activity_main);
    }

    private void showNotes(List<Note> notes) {
        recyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        noteViewAdapter = new NoteViewAdapter(notes);
        recyclerView.setAdapter(noteViewAdapter);
    }
}
