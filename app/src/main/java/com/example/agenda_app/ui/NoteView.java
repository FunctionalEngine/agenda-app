package com.example.agenda_app.ui;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agenda_app.model.Note;

public class NoteView extends LinearLayout {

    private TextView title;
    private TextView shortContent;
    private TextView date;

    public NoteView(Context context, Note note) {
        super(context);

        setViewParameters();

        title = new TextView(this.getContext());
        shortContent = new TextView(this.getContext());
        date = new TextView(this.getContext());

        setData(note);

        addView(title);
        addView(shortContent);
        addView(date);
    }

    private void setViewParameters() {
        LayoutParams params = (LayoutParams) this.getLayoutParams();
        params.height = LayoutParams.WRAP_CONTENT;
        params.width = LayoutParams.MATCH_PARENT;

        setLayoutParams(params);
    }

    public void setData(Note note) {
        title.setText("Title");
        shortContent.setText(note.getDescription());
        date.setText(note.getTime().toString());
    }
}
