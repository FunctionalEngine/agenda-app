package com.example.agenda_app;

import org.junit.Test;

import java.util.Iterator;

import model.Group;
import model.GroupNotes;
import model.Note;
import model.State;
import model.Time;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
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

    @Test
    public void class_GroupNotes_basic_operation() {
        Note n0 = new Note("n0",new Time(3,3,3),true, "violonchelo");
        GroupNotes g0 = new GroupNotes("violonchelo");
        GroupNotes g1 = new GroupNotes("Flauta");
        assertEquals(true,g0.add(n0));
        assertNotEquals(true,g1.add(n0));
        assertEquals(true,g1.isEmpty());
        assertEquals(false,g0.isEmpty());
        for (int i = 0; i < 10; i++) {
            g1.add(new Note("n1",new Time(3,4,5 + i),true,"Flauta"));
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
        GroupNotes g1 = new GroupNotes("Flauta");
        for (int i = 0; i < 10; i++) {
            g1.add(new Note("n1",new Time(3,4,5 + i),true,"Flauta"));
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