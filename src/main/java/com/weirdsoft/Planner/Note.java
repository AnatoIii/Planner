package com.weirdsoft.Planner;

import java.time.LocalDate;

/**
 *
 * @author kater
 */
public class Note {

    String id;
    String name;
    String description;
    String time;
    LocalDate date;
    String creatorId;

    public Note(String noteName, String noteDescription, String noteTime, LocalDate noteDate) {
        name = noteName;
        description = noteDescription;
        time = noteTime;
        date = noteDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }
}
