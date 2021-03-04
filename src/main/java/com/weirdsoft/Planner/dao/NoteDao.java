package com.weirdsoft.Planner.dao;

import com.weirdsoft.Planner.models.Note;
import com.weirdsoft.Planner.models.dtos.NoteTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface NoteDao extends GenericDao<Note>{
    List<Note> getByDate(Date date, UUID userId);
    List<Note> getByMonth(Date date, UUID userId);
}
