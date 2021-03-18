package com.weirdsoft.Planner.services.impl;

import com.weirdsoft.Planner.dao.NoteDao;
import com.weirdsoft.Planner.exceptions.NotFoundException;
import com.weirdsoft.Planner.jpa.NoteJPA;
import com.weirdsoft.Planner.models.Note;
import com.weirdsoft.Planner.models.dtos.NoteTO;
import com.weirdsoft.Planner.services.NoteService;

import javax.ejb.*;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Stateless(name = "noteService", mappedName = "noteService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class NoteServiceImpl implements NoteService {
    private NoteDao noteDao;

    @EJB
    private NoteJPA noteJPA;

    @Inject
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public NoteTO getById(UUID id) throws NotFoundException {
        Note note = noteJPA.find(id);
        if(note == null){
            throw new NotFoundException();
        }
        return convert2TO(noteDao.find(id));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<NoteTO> getByDate(Date date, UUID userId) {
        return noteJPA.getByDate(date, userId)
                .stream()
                .map(n -> convert2TO(n))
                .collect(Collectors.toList());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<NoteTO> getByMonth(Date date, UUID userId) {
        return noteJPA.getByMonth(date, userId).stream().map(NoteServiceImpl::convert2TO).collect(Collectors.toList());
    }

    @Override
    public NoteTO createNote(NoteTO noteTO) {
        Note note = convert2dao(noteTO);
        Note newNote = noteJPA.create(note);
        return convert2TO(newNote);
    }

    @Override
    public UUID deleteNote(UUID id, UUID userId) {
        return noteJPA.delete(id, userId);
    }

    @Override
    public UUID updateNote(NoteTO note) {
        return noteJPA.update(convert2dao(note));
    }

    private static NoteTO convert2TO(Note note){
        NoteTO noteTO = new NoteTO();
        noteTO.setNoteId(note.getNoteId());
        noteTO.setName(note.getName());
        noteTO.setDescription(note.getDescription());
        noteTO.setDateTime(note.getDateTime());
        return noteTO;
    }

    private static Note convert2dao(NoteTO noteTO){
        Note note = new Note();
        note.setNoteId(noteTO.getNoteId());
        note.setName(noteTO.getName());
        note.setDescription(noteTO.getDescription());
        note.setDateTime(noteTO.getDateTime());
        note.setCreatorId(noteTO.getCreatorId());
        return note;
    }
}
