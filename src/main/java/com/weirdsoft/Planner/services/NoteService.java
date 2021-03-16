package com.weirdsoft.Planner.services;

import com.weirdsoft.Planner.exceptions.NotFoundException;
import com.weirdsoft.Planner.models.dtos.NoteTO;

import javax.ejb.Remote;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Remote
public interface NoteService {
    NoteTO getById(UUID id) throws NotFoundException;
    List<NoteTO> getByDate(Date date, UUID userId);
    List<NoteTO> getByMonth(Date date, UUID userId);
    NoteTO createNote(NoteTO note);
    UUID deleteNote(UUID id, UUID userId);
    UUID updateNote(NoteTO note);
}
