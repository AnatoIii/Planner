package com.weirdsoft.Planner;

import java.util.Date;

/**
 *
 * @author kater
 */
public class Note {

    String id;
    String name;
    String description;
    String time;
    Date date;
    String creatorId;

    public Note(String noteName, String noteDescription, String noteTime) {
        name = noteName;
        description = noteDescription;
        time = noteTime;
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
}
