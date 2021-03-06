package com.weirdsoft.Planner.models;

import com.weirdsoft.Planner.jpa.UuidConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//@Entity
//@Table(name = "notes")
public class Note {
//    @Id
//    @Column(name = "noteid")
//    @GeneratedValue
//    @Convert(converter = UuidConverter.class)
    private UUID noteId;
//    @Column(name = "name")
    private String name;
//    @Column(name = "description")
    private String description;
//    @Column(name = "datetime")
    private Date dateTime;
//    @Column(name = "creatorid")
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
