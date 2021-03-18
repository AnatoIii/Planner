package com.weirdsoft.Planner.jpa;

import com.weirdsoft.Planner.dao.DaoUtils;
import com.weirdsoft.Planner.mappers.NoteMapper;
import com.weirdsoft.Planner.models.Note;
import com.weirdsoft.Planner.jpa.DBNote;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class NoteJPA {
    private static final String getAllByDateAndUser = "select n from DBNote n where DATE_PART('doy', dateTime) = DATE_PART('doy', TO_TIMESTAMP(:date,'YYYY-MM-DD')) AND creatorId = :creator";
    private static final String getByMonth = "select n from DBNote n where dateTime >= :dtStart AND dateTime < :dtEnd AND creatorId = :creator";

    //@PersistenceContext(name = "default")
    EntityManager entityManager;

    public NoteJPA(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        entityManager = factory.createEntityManager();
    }

    private Note convertToNote(DBNote dbNote){
        return dbNote == null ? null : new Note(UUID.fromString(dbNote.getNoteId()),dbNote.getName(),dbNote.getDescription(),dbNote.getDateTime(),UUID.fromString(dbNote.getCreatorId()));
    }

    private DBNote convertToDBNote(Note note){
        return new DBNote(note.getNoteId() != null ? note.getNoteId().toString() : null,note.getName(),note.getDescription(),Timestamp.from(note.getDateTime().toInstant()), note.getCreatorId().toString());
    }

    public Note find(UUID id) {
        DBNote note = null;
        try {
            note = entityManager.find(DBNote.class, id.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        return convertToNote(note);
    }

    public Note create(Note object) {
        DBNote note = convertToDBNote(object);
        note.setNoteId(UUID.randomUUID().toString());
        entityManager.persist(note);
        entityManager.flush();
        return convertToNote(note);
    }

    public UUID delete(UUID id, UUID userId) {
        DBNote note = entityManager.find(DBNote.class, id.toString());
        if(note.getCreatorId().equals(userId.toString())){
            entityManager.remove(note);
        }
        return id;
    }

    public UUID update(Note object) {
        DBNote note = convertToDBNote(object);
        entityManager.merge(note);
        entityManager.flush();
        return object.getNoteId();
    }

    public List<Note> getByMonth(Date date, UUID userId) {
        List<DBNote> dbnotes = null;
        try {
            dbnotes = (List<DBNote> ) entityManager.createQuery(getByMonth)
                    .setParameter("dtStart", new Timestamp(date.getYear(),date.getMonth(),1,0,0,0,0))
                    .setParameter("dtEnd", new Timestamp(date.getYear(),date.getMonth() + 1,1,0,0,0,0))
                    .setParameter("creator",userId.toString())
                    .getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return dbnotes.stream().map(this::convertToNote).collect(Collectors.toList());
    }

    public List<Note> getByDate(Date date, UUID userId) {

        return ((List<DBNote>) entityManager.createQuery(getAllByDateAndUser)
                .setParameter("date", date.toString())
                .setParameter("creator",userId.toString())
                .getResultList()).stream().map(this::convertToNote).collect(Collectors.toList());
    }


}
