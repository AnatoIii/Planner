package com.weirdsoft.Planner.models;

import java.util.Date;
import java.util.UUID;

public class Note {
    private UUID noteId;
    private String name;
    private String description;
    private Date dateTime;
    private UUID creatorId;

    public Note(UUID noteId, String name, String description, Date dateTime, UUID creatorId) {
        this.noteId = noteId;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.creatorId = creatorId;
    }

    public Note(){

    }

    public UUID getNoteId() {
        return noteId;
    }

    public void setNoteId(UUID noteId) {
        this.noteId = noteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }
}
