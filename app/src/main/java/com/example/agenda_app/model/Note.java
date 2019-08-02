package com.example.agenda_app.model;


import java.time.LocalDateTime;
import java.util.Objects;

public class Note {
    private String description;
    private Double duration;
    private LocalDateTime time;
    private State state;
    private Boolean priority;
    private String group;
    private Integer id;

    public Note(String description, Double duration, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
    }

    public Note(String description, Double duration, LocalDateTime time, Boolean priority, String group) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = State.INCOMPLETE;
        this.priority = priority;
        this.group = group;
    }

    public Note(String description, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
    }

    public Note(String description, Double duration, LocalDateTime time, Boolean priority) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
    }

    public Note(String description, LocalDateTime time, Boolean priority) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
    }

    public Note(String description, LocalDateTime time, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.group = group;
        this.state = State.INCOMPLETE;
    }

    public Note(int id,String description, Double duration, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
        this.id = id;
    }

    public Note(int id,String description, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
        this.id = id;
    }

    public Note(int id,String description, Double duration, LocalDateTime time, Boolean priority) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
        this.id = id;
    }

    public Note(int id,String description, LocalDateTime time, Boolean priority) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
        this.id = id;
    }

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
