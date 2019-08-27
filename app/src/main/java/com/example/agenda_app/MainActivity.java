package com.example.agenda_app;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.agenda_app.model.Note;
import com.example.agenda_app.ui.CreateNoteFragment;
import com.example.agenda_app.ui.NotesDisplayFragment;
import com.example.agenda_app.viewmodel.NotesViewModel;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    private NotesViewModel model;
    private final String NOTES_DISPLAY_FRAGMENT_TAG = "NOTES_DISPLAY";
    private final String CREATE_NOTE_FRAGMENT_TAG = "CREATE_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ViewModelProviders.of(this).get(NotesViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(
                            R.id.fragments_wrapper,
                            new NotesDisplayFragment(),
                            NOTES_DISPLAY_FRAGMENT_TAG).
                    commit();
        }

        setContentView(R.layout.activity_main);
    }

    public void launchCreateNoteActivity(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(
                R.id.fragments_wrapper,
                new CreateNoteFragment(),
                CREATE_NOTE_FRAGMENT_TAG
        );
        fragmentTransaction.hide(fragmentManager.findFragmentByTag(NOTES_DISPLAY_FRAGMENT_TAG));
        fragmentTransaction.commit();
    }

    public void returnMainActivity(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragmentManager.findFragmentByTag(NOTES_DISPLAY_FRAGMENT_TAG));
        fragmentTransaction.remove(fragmentManager.findFragmentByTag(CREATE_NOTE_FRAGMENT_TAG));
        fragmentTransaction.commit();
    }

    public void createNewNote(View view){
        Note note = new Note(
                ((EditText) findViewById(R.id.note_description)).getText().toString(),
                LocalDateTime.now(),
                ((CheckBox) findViewById(R.id.priority_check)).isChecked()
        );
        model.insertNote(note);
        returnMainActivity(view);
    }

}