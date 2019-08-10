package com.example.agenda_app.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_app.model.Note;

import java.util.ArrayList;
import java.util.List;


public class NoteViewAdapter extends RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder> {
    private List<Note> notes;

    public static class NoteViewHolder extends RecyclerView.ViewHolder{

        NoteView noteView;

        NoteViewHolder(NoteView noteView) {
            super(noteView);
            this.noteView = noteView;
        }
    }

    public NoteViewAdapter() {
        notes = new ArrayList<>();
    }

    public void setData(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(new NoteView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.noteView.setData(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
