package com.weirdsoft.Planner.dao;

import com.weirdsoft.Planner.models.Note;
import com.weirdsoft.Planner.models.dtos.NoteTO;

import java.util.Date;
import java.util.List;

public interface NoteDao extends GenericDao<Note>{
    List<Note> getByMonth(Date date);
}
