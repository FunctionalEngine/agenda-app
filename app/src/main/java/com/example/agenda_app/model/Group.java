package com.example.agenda_app.model;

import java.util.Iterator;

interface Group {
    Boolean add(Note note);
    void remove(Note note);
    void remove(int pos);
    void removeAll();
    int length();
    Note get(int pos);
    Boolean isEmpty();
    Iterator createIterator();
}
