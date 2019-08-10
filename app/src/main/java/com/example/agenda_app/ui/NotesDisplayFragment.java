package com.example.agenda_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_app.R;
import com.example.agenda_app.viewmodel.NotesViewModel;

public class NotesDisplayFragment extends Fragment {

    private NoteViewAdapter noteViewAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        noteViewAdapter = new NoteViewAdapter();

        NotesViewModel model = ViewModelProviders.of(getActivity()).get(NotesViewModel.class);
        model.getNotes().observe(this, notes -> { noteViewAdapter.setData(notes); });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_display, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.notes_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(noteViewAdapter);

        return view;
    }
}