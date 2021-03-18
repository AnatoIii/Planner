package com.weirdsoft.Planner.jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "notes")
public class DBNote {
    @Id
    @Column(name = "noteid")
    private String noteId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "datetime")
    private Timestamp dateTime;
    @Column(name = "creatorid")
    private String  creatorId;

    public DBNote(String noteId, String name, String description, Timestamp dateTime, String creatorId) {
        this.noteId = noteId;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.creatorId = creatorId;
    }

    public DBNote(){

    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
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

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}