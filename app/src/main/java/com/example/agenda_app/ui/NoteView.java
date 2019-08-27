package com.example.agenda_app.ui;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agenda_app.R;
import com.example.agenda_app.model.Note;

import java.time.format.DateTimeFormatter;

public class NoteView extends LinearLayout {
    //TODO
    // - Design UI Component

    private TextView description;
    private TextView date;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

    public NoteView(Context context) {
        super(context);
        inflate(getContext(), R.layout.note_view, this);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
    }

    public void setData(Note note) {
        description.setText(note.getDescription());
        date.setText(note.getTime().format(formatter));
    }
}
