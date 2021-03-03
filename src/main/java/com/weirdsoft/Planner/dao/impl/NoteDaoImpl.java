package com.weirdsoft.Planner.dao.impl;

import com.weirdsoft.Planner.dao.DaoUtils;
import com.weirdsoft.Planner.dao.NoteDao;
import com.weirdsoft.Planner.mappers.NoteMapper;
import com.weirdsoft.Planner.models.Note;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
public class NoteDaoImpl implements NoteDao {
    private static final String getByIdSql = "select noteId, name, description, dateTime, creatorId from Notes where noteId = ?";
    private static final String deleteByIdSql = "delete from Notes where noteid = ? RETURNING noteid ";
    private static final String createSql = "insert into Notes(noteId, name, description, dateTime, creatorId) VALUES (?, ?, ?, ?, ?) RETURNING noteId";
    private static final String updateSql = "update notes set name=?, description=? WHERE noteid=? RETURNING noteid";
    private static final String getByMonth = "select noteId, name, description, dateTime, creatorId from Notes where dateTime >= ? AND dateTime < ?";

    @Override
    public Note find(UUID id) {
        return DaoUtils.executeAndMap(getByIdSql, NoteMapper::mapNote, (ps) -> {
            try {
                ps.setString(1, id.toString());
            } catch (Exception e) {

            }
        });
    }

    @Override
    public Note create(Note object) {
        UUID newId = UUID.randomUUID();
        DaoUtils.executeAndMap(createSql,DaoUtils::uuidMapper,(ps) -> {
            try {
                ps.setString(1,newId.toString());
                ps.setString(2,object.getName());
                ps.setString(3,object.getDescription());
                ps.setTimestamp(4,new Timestamp(object.getDateTime().getTime()));
                String creatorId = null;
                if(object.getCreatorId() != null){
                    creatorId = object.getCreatorId().toString();
                }
                ps.setString(5,creatorId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        object.setNoteId(newId);
        return object;
    }

    @Override
    public UUID delete(UUID id) {
        return DaoUtils.executeAndMap(deleteByIdSql, DaoUtils::uuidMapper, (ps) -> {
            try {
                ps.setString(1,id.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    @Override
    public UUID update(Note object) {
        return DaoUtils.executeAndMap(updateSql, DaoUtils::uuidMapper, (ps) -> {
            try {
                ps.setString(1, object.getName());
                ps.setString(2, object.getDescription());
                ps.setString(3, object.getNoteId().toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    @Override
    public List<Note> getByMonth(Date date) {
        return DaoUtils.executeAndMap(getByMonth,NoteMapper::mapNotes,(ps) -> {
            try {
                ps.setTimestamp(1, new Timestamp(date.getYear(),date.getMonth(),1,0,0,0,0));
                ps.setTimestamp(2, new Timestamp(date.getYear(),date.getMonth() + 1,1,0,0,0,0));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
