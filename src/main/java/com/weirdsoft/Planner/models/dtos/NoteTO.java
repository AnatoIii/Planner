package com.weirdsoft.Planner.models.dtos;

import java.util.Date;
import java.util.UUID;

public class NoteTO {
    private UUID noteId;
    private String name;
    private String description;
    private Date dateTime;
    private UUID creatorId;
    
    public NoteTO() {
    }

    public NoteTO(UUID noteId, String name, String description, Date dateTime) {
        this.noteId = noteId;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
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

    @Override
    public String toString() {
        return "NoteTO{" +
                "noteId=" + noteId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
