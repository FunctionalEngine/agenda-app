package com.example.agenda_app;
import com.example.agenda_app.model.Note;
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

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.time.LocalDateTime;
import java.util.List;

//import com.groceryreminder.BuildConfig;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class DbHelperUnitTest{

    private NotesDbHelper dbHelper;
    private Note n0 = new Note(
                "Erase una vez en un reino muy muy lejano",
                LocalDateTime.of(2015,4,7,12,59,30),
                true,
                        "violonchelo");
    private Note n1 = new Note(
            "llamado (Arriba) España",
            6.6,
            LocalDateTime.of(2015,4,7,12,59,30),
            false,
            "flauta");
    private Note n2 = new Note(
            "Donde la gente que vivia allí votaba a otra gente que les robaba,pero les daba igual.",
            LocalDateTime.of(2019,6,6,11,3,30),
            true,
            "violonchelo");

    @Before
    public void before(){
        dbHelper = NotesDbHelper.getInstance(RuntimeEnvironment.application);
    }

    @Test
    public void test_to_getSize_of_elements_into_the_datebase(){
        dbHelper.deleteAllNote();
        assertEquals(0,dbHelper.getSize());
        dbHelper.insertInto(n0);
        dbHelper.insertInto(n1);
        assertEquals(2,dbHelper.getSize());
        dbHelper.deleteAllNote();

    }

    @Test
    public void test_to_insert_Into_DataBase_and_read(){
        dbHelper.deleteAllNote();
        dbHelper.insertInto(n0);
        dbHelper.insertInto(n1);
        List<Note> list = dbHelper.readNotes();
        assertEquals(dbHelper.getSize(),dbHelper.readNotes().size());
        assertTrue(n0.equals(list.get(0)));
        assertEquals(n1,list.get(1));
        assertNotEquals(null,list.get(0).getId());
        assertNotEquals(null,list.get(1).getId());
    }


    @Test
    public void test_to_deleteAllNote(){
        dbHelper.deleteAllNote();
        dbHelper.insertInto(n0);
        dbHelper.insertInto(n1);
        assertEquals(2,dbHelper.getSize());
        dbHelper.deleteAllNote();
        assertEquals(0,dbHelper.getSize());
    }

    @Test
    public void test_to_deleteAnyNote(){
        dbHelper.deleteAllNote();
        dbHelper.insertInto(n0);
        dbHelper.insertInto(n1);
        List<Note> list = dbHelper.readNotes();
        assertEquals(2,dbHelper.getSize());
        assertTrue(dbHelper.deleteAnyNote(list.get(0).getId()));
        assertEquals(1,dbHelper.getSize());
        assertFalse(dbHelper.deleteAnyNote(list.get(0).getId()));
        assertTrue(dbHelper.deleteAnyNote(list.get(1).getId()));
        assertEquals(0,dbHelper.getSize());
        assertFalse(dbHelper.deleteAnyNote(list.get(0).getId()));
        assertFalse(dbHelper.deleteAnyNote(list.get(1).getId() + 1));
    }

    @Test
    public void test_to_readGroups(){
        dbHelper.deleteAllNote();
        dbHelper.insertInto(n0);
        dbHelper.insertInto(n1);
        dbHelper.insertInto(n2);
        NotesGroup g_flauta = dbHelper.readGroupNotes("flauta");
        NotesGroup g_violanchelo = dbHelper.readGroupNotes("violonchelo");
        assertEquals(dbHelper.getSize(),g_flauta.length()+g_violanchelo.length());
        assertEquals(1,g_flauta.length());
        assertEquals(2,g_violanchelo.length());
        assertTrue(n0.equals(g_violanchelo.get(0)));
        assertTrue(n1.equals(g_flauta.get(0)));
        assertTrue(n2.equals(g_violanchelo.get(1)));
        NotesGroup g_null = dbHelper.readGroupNotes("null");
        assertEquals(0,g_null.length());

    }


    @After
    public void after(){
        dbHelper.close();
    }
}
