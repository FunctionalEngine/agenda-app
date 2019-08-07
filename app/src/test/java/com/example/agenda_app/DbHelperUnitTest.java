package com.example.agenda_app;
import com.example.agenda_app.model.Note;
import com.example.agenda_app.model.NotesDAO;
import com.example.agenda_app.model.NotesDatabase;
import com.example.agenda_app.model.NotesDbHelper;
import com.example.agenda_app.model.NotesGroup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;


import static org.junit.Assert.*;

import android.os.Build;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class DbHelperUnitTest{

    private NotesDatabase db;
    private Note n0 = new Note(
                "description 1",
                LocalDateTime.of(2015,4,7,12,59,30),
                true,
                        "violonchelo");
    private Note n1 = new Note(
            "description 2",
            6.6,
            LocalDateTime.of(2015,4,7,12,59,30),
            false,
            "flauta");
    private Note n2 = new Note(
            "description 3",
            LocalDateTime.of(2019,6,6,11,3,30),
            true,
            "violonchelo");

    @Before
    public void before(){
        db = NotesDatabase.getInstance(RuntimeEnvironment.application,true);
    }

    @Test
    public void test_to_getSize_of_elements_into_the_datebase(){
        assertEquals(0,db.notesDAO().getSize());
        db.notesDAO().insertNote(n0);
        assertEquals(1,db.notesDAO().getSize());
        db.notesDAO().insertNote(n1);
        db.notesDAO().insertNote(n2);
        assertEquals(3,db.notesDAO().getSize());
        db.notesDAO().deleteAllNotes();

    }

    @Test
    public void test_to_insert_Into_DataBase_and_read(){
        db.notesDAO().insertNote(n0);
        db.notesDAO().insertNote(n1);
        db.notesDAO().insertNote(n2);
        List<Note> list = db.notesDAO().getAllNotes();
        assertTrue(n0.equals(list.get(0)));
        assertTrue(n1.equals(list.get(1)));
        assertTrue(n2.equals(list.get(2)));
        db.notesDAO().deleteAllNotes();

    }


    @Test
    public void test_to_deleteAllNote(){
        assertEquals(0,db.notesDAO().getSize());
        db.notesDAO().insertNote(n0);
        db.notesDAO().insertNote(n1);
        db.notesDAO().insertNote(n2);
        assertEquals(3,db.notesDAO().getSize());
        db.notesDAO().deleteAllNotes();
        assertEquals(0,db.notesDAO().getSize());
    }

    @Test
    public void test_to_deleteAnyNote(){
        db.notesDAO().insertNote(n0);
        List<Note> list = db.notesDAO().getAllNotes();
        db.notesDAO().deleteNote(n1);
        assertEquals(1,db.notesDAO().getSize());
        db.notesDAO().deleteNote(list.get(0));
        assertEquals(0,db.notesDAO().getSize());
    }

    @Test
    public void test_to_readGroups(){
        db.notesDAO().insertNote(n0);
        db.notesDAO().insertNote(n1);
        db.notesDAO().insertNote(n2);
        List<Note> list = db.notesDAO().getNoteGroup("flauta");
        List<Note> list1 = db.notesDAO().getNoteGroup("violonchelo");
        List<Note> list2 = db.notesDAO().getNoteGroup("");
        assertEquals(db.notesDAO().getSize(), list.size()+list1.size());
        assertEquals(1, list.size());
        assertEquals(2, list1.size());
        assertEquals(0, list2.size());
        assertTrue(n1.equals(list.get(0)));

    }


    @After
    public void after(){
        db.close();
    }
}
