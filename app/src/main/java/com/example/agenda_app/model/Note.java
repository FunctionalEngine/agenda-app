package com.example.agenda_app.model;


import java.time.LocalDateTime;

public class Note {
    private String description;
    private Double duration;
    private LocalDateTime time;
    private State state;
    private Boolean priority;
    private String group;
    private int id;

    public Note(String description, Double duration, LocalDateTime time, State state, Boolean priority, String group) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = state;
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
}
