package com.example.agenda_app.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(tableName = "Notes",indices = {@Index(value = {"Namegroup"})})
public class Note {
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "duration")
    private Double duration;
    @ColumnInfo(name = "time")
    private LocalDateTime time;
    @ColumnInfo(name = "state")
    private State state;
    @ColumnInfo(name = "priority")
    private Boolean priority;
    @ColumnInfo(name = "Namegroup")
    private String group;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Note(String description, Double duration, LocalDateTime time, State state, Boolean priority, String group, int id) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
        this.id = id;
    }

    @Ignore
    public Note(String description, Double duration, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
    }

    @Ignore
    public Note(String description, Double duration, LocalDateTime time, Boolean priority, String group) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = State.INCOMPLETE;
        this.priority = priority;
        this.group = group;
    }

    @Ignore
    public Note(String description, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
    }

    @Ignore
    public Note(String description, Double duration, LocalDateTime time, Boolean priority) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
    }

    @Ignore
    public Note(String description, LocalDateTime time, Boolean priority) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
    }

    @Ignore
    public Note(String description, LocalDateTime time, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.group = group;
        this.state = State.INCOMPLETE;
    }

    @Ignore
    public Note(int id,String description, Double duration, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
        this.id = id;
    }

    @Ignore
    public Note(int id,String description, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
        this.id = id;
    }

    @Ignore
    public Note(int id,String description, Double duration, LocalDateTime time, Boolean priority) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
        this.id = id;
    }

    @Ignore
    public Note(int id,String description, LocalDateTime time, Boolean priority) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
        this.id = id;
    }

    @Ignore
    public Note(int id,String description, LocalDateTime time, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.group = group;
        this.state = State.INCOMPLETE;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Double getDuration() {
        return duration;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public State getState() {
        return state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public String getGroup() {
        return group;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return Objects.equals(getDescription(), note.getDescription()) &&
                Objects.equals(getDuration(), note.getDuration()) &&
                Objects.equals(getTime(), note.getTime()) &&
                getState() == note.getState() &&
                Objects.equals(getPriority(), note.getPriority()) &&
                Objects.equals(getGroup(), note.getGroup());
    }

    @Override
    public String toString() {
        return "Note{" +
                "description='" + description + '\'' +
                ", duration=" + duration +
                ", time=" + time +
                ", state=" + state +
                ", priority=" + priority +
                ", group='" + group + '\'' +
                '}';
    }
}
