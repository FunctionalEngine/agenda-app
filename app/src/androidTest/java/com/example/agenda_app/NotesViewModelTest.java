package com.example.agenda_app;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.agenda_app.viewmodel.NotesViewModel;

import org.junit.Before;
import org.junit.Test;

public class NotesViewModelTest {
    //TODO
    // - Create Instrumented Tests!!!!!!!!

    private NotesViewModel notesViewModel;
    private Context testContext;

    @Before
    public void before(){
        this.notesViewModel = new NotesViewModel();
        this.testContext = InstrumentationRegistry.getInstrumentation().getContext();
    }

    @Test
    public void getNotesTest() {
        notesViewModel.getNotes(testContext).getValue();
    }
}