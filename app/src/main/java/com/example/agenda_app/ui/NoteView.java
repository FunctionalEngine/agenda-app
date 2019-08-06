package com.example.agenda_app.ui;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agenda_app.model.Note;

public class NoteView extends LinearLayout {
    //TODO
    // - Design UI Component

    private TextView description;
    private TextView priority;
    private TextView date;

    public NoteView(Context context) {
        super(context);

        setViewParameters();

        description = new TextView(this.getContext());
        priority = new TextView(this.getContext());
        date = new TextView(this.getContext());

        addView(description);
        addView(priority);
        addView(date);
    }

    private void setViewParameters() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(params);
    }

    public void setData(Note note) {
        description.setText("Title");
        priority.setText(note.getDescription());
        date.setText(note.getTime().toString());
    }
}
