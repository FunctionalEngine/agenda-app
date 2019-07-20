package com.example.agenda_app.model;


public class Note {
    private String description;
    private Double duration;
    private Time time;
    private State state;
    private Boolean priority;
    private String group;

    public Note(String description, Double duration, Time time, State state, Boolean priority, String group) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
    }

    public Note(String description, Time time, State state, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.state = state;
        this.priority = priority;
        this.group = group;
    }

    public Note(String description, Double duration, Time time, Boolean priority) {
        this.description = description;
        this.duration = duration;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
    }

    public Note(String description, Time time, Boolean priority) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.state = State.INCOMPLETE;
    }

    public Note(String description, Time time, Boolean priority, String group) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.group = group;
        this.state = State.INCOMPLETE;
    }

    public String getDescription() {
        return description;
    }

    public Double getDuration() {
        return duration;
    }

    public Time getTime() {
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

    public void setTime(Time time) {
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
}
