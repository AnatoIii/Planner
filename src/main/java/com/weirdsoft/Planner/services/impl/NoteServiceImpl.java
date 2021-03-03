package com.weirdsoft.Planner.services.impl;

import com.weirdsoft.Planner.dao.NoteDao;
import com.weirdsoft.Planner.exceptions.NotFoundException;
import com.weirdsoft.Planner.models.Note;
import com.weirdsoft.Planner.models.dtos.NoteTO;
import com.weirdsoft.Planner.services.NoteService;

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

@Named("noteService")
public class NoteServiceImpl implements NoteService {
    private NoteDao noteDao;

    @Inject
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public NoteTO getById(UUID id) throws NotFoundException {
        Note note = noteDao.find(id);
        if(note == null){
            throw new NotFoundException();
        }
        return convert2TO(noteDao.find(id));
    }

    @Override
    public List<NoteTO> getByDate(Date date) {
        return null;
    }

    @Override
    public List<NoteTO> getByMonth(Date date) {
        return noteDao.getByMonth(date).stream().map(NoteServiceImpl::convert2TO).collect(Collectors.toList());
    }

    @Override
    public NoteTO createNote(NoteTO noteTO) {
        Note note = convert2dao(noteTO);
        Note newNote = noteDao.create(note);
        return convert2TO(newNote);
    }

    @Override
    public UUID deleteNote(UUID id) {
        return noteDao.delete(id);
    }

    @Override
    public UUID updateNote(NoteTO note) {
        return noteDao.update(convert2dao(note));
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
        return note;
    }
}
