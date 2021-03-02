package com.weirdsoft.Planner.services;

import com.weirdsoft.Planner.models.dtos.NoteTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface NoteService {
    NoteTO getById(UUID id);
    List<NoteTO> getByDate(Date date);
    List<NoteTO> getByMonth(Date date);
    NoteTO createNote(NoteTO note);
    UUID deleteNote(UUID id);
    UUID updateNote(NoteTO note);
}
