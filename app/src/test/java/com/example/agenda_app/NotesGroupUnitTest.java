package com.example.agenda_app;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Iterator;

import com.example.agenda_app.model.NotesGroup;
import com.example.agenda_app.model.Note;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NotesGroupUnitTest {
    @Test
    public void class_GroupNotes_basic_operation() {
        Note n0 = new Note(
                "n0",
                LocalDateTime.of(2015,4,7,12,59,30),
                true,
                "violonchelo");

        NotesGroup g0 = new NotesGroup("violonchelo");
        NotesGroup g1 = new NotesGroup("Flauta");
        assertEquals(true,g0.add(n0));
        assertNotEquals(true,g1.add(n0));
        assertEquals(true,g1.isEmpty());
        assertEquals(false,g0.isEmpty());

        for (int i = 0; i < 10; i++) {
            g1.add(new Note(
                    "n1",
                    LocalDateTime.of(2015,4,7,12,59,30),
                    true,
                    "Flauta"));
        }

        assertEquals(10,g1.length());
        assertEquals(1,g0.length());
        g1.removeAll();
        g0.remove(0);
        assertEquals(0,g1.length());
        assertEquals(0,g0.length());
    }

    @Test
    public void class_GroupNotes_createIterator_operation() {
        NotesGroup g1 = new NotesGroup("Flauta");
        for (int i = 0; i < 10; i++) {
            g1.add(new Note(
                    "n1",
                    LocalDateTime.of(2015 + i,4,7,12,59,30),
                    true,
                    "Flauta"));
        }

        int pos = 0;
        Iterator i = g1.createIterator();
        while (i.hasNext()){
            assertEquals(g1.get(pos), i.next());
            pos++;
        }
        assertEquals(10, pos);

    }
}