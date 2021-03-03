package com.weirdsoft.Planner.mappers;

import com.weirdsoft.Planner.models.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NoteMapper {
    public static Note mapNote(ResultSet rs) {
        Note note = null;
        try {
            if(rs.next()){
                note = new Note();
                note.setNoteId(UUID.fromString(rs.getString("noteId")));
                String creatorId = rs.getString("creatorId");
                note.setCreatorId(creatorId != null ? UUID.fromString(creatorId) : null);
                note.setDateTime(new Date(rs.getTimestamp("dateTime").getTime()));
                note.setDescription(rs.getString("description"));
                note.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    public static List<Note> mapNotes(ResultSet rs){
        List<Note> notes = new ArrayList<>();
        try {
        while(rs.next()){
            Note note = new Note();
            note.setNoteId(UUID.fromString(rs.getString("noteId")));
            String creatorId = rs.getString("creatorId");
            note.setCreatorId(creatorId != null ? UUID.fromString(creatorId) : null);
            note.setDateTime(new Date(rs.getTimestamp("dateTime").getTime()));
            note.setDescription(rs.getString("description"));
            note.setName(rs.getString("name"));
            notes.add(note);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }
}
