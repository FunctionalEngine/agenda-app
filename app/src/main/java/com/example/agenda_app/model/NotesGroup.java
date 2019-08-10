package com.example.agenda_app.model;

import java.util.ArrayList;
import java.util.Iterator;

public class NotesGroup implements Group {

    private ArrayList<Note> listNotes;
    private String name_group;

    public NotesGroup(String name_group) {
        listNotes = new ArrayList<>();
        this.name_group = name_group;
    }

    @Override
    public Boolean add(Note note) {
        if (note.getGroup().equals(name_group)) {
            listNotes.add(note);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void remove(Note note) {
        listNotes.remove(note);
    }

    @Override
    public void remove(int pos) {
        listNotes.remove(pos);
    }

    @Override
    public void removeAll() {
        listNotes.removeAll(listNotes);
    }

    @Override
    public int length() {
        return listNotes.size();
    }

    @Override
    public Note get(int pos) {
        return listNotes.get(pos);
    }

    @Override
    public Boolean isEmpty() {
        return listNotes.size() == 0;
    }


    @Override
    public Iterator createIterator() {
        return new Iterator() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < listNotes.size();
            }

            @Override
            public Object next() {
                return listNotes.get(index++);
            }
        };
    }

    @Override
    public String toString() {
        return "NotesGroup{" +
                "listNotes=" + listNotes +
                ", name_group='" + name_group + '\'' +
                '}';
    }
}
