package com.example.agenda_app;

import org.junit.Test;

import java.util.Iterator;

import com.example.agenda_app.model.NotesGroup;
import com.example.agenda_app.model.Note;
import com.example.agenda_app.model.State;
import com.example.agenda_app.model.Time;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NoteUnitTest {
    @Test
    public void struct_of_class_Note_null_atributes() {
        Note n = new Note("nota", new Time(13,3,10), true);

        assertEquals(null, n.getDuration());
        assertEquals(null, n.getGroup());
    }

    @Test
    public void struct_of_class_Note_states() {
        Note n = new Note("nota", new Time(13,3,10), true);
        assertEquals(State.INCOMPLETE, n.getState());
        n.setState(State.COMPLETE);
        assertEquals(State.COMPLETE, n.getState());
        n.setState(State.DOING);
        assertEquals(State.DOING, n.getState());
        assertEquals("DOING", n.getState() + "");
    }
}