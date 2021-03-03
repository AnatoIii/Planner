package com.weirdsoft.Planner.services;

import com.weirdsoft.Planner.exceptions.NotFoundException;
import com.weirdsoft.Planner.models.dtos.NoteTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface NoteService {
    NoteTO getById(UUID id) throws NotFoundException;
    List<NoteTO> getByDate(Date date, UUID userId);
    List<NoteTO> getByMonth(Date date);
    NoteTO createNote(NoteTO note);
    UUID deleteNote(UUID id);
    UUID updateNote(NoteTO note);
}
