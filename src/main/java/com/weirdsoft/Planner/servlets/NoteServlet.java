package com.weirdsoft.Planner.servlets;

import com.weirdsoft.Planner.dao.NoteDao;
import com.weirdsoft.Planner.dao.impl.NoteDaoImpl;
import com.weirdsoft.Planner.models.dtos.NoteTO;
import com.weirdsoft.Planner.services.NoteService;
import com.weirdsoft.Planner.services.impl.NoteServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@WebServlet(name = "noteServlet", value = "/note-servlet")
public class NoteServlet extends HttpServlet {
    private NoteService noteService;
    public NoteServlet(){
        NoteDao dao = new NoteDaoImpl();
        noteService = new NoteServiceImpl(dao);
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("noteId");
        UUID uuid = UUID.fromString(id);
        PrintWriter out = response.getWriter();
        out.println(noteService.getById(uuid));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        JsonReader reader = Json.createReader(request.getReader());
        JsonObject jsonObject = reader.readObject();
        NoteTO noteTO = new NoteTO();
        noteTO.setName(jsonObject.getString("name"));
        noteTO.setDescription(jsonObject.getString("description"));
        noteTO.setDateTime(Date.from(Instant.parse(jsonObject.getString("dateTime"))));
        NoteTO newNote =  noteService.createNote(noteTO);
        PrintWriter out = response.getWriter();
        out.println(newNote);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("noteId");
        UUID uuid = UUID.fromString(id);
        PrintWriter out = response.getWriter();
        //out.println(noteService.deleteNote(uuid));
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonReader reader = Json.createReader(request.getReader());
        JsonObject jsonObject = reader.readObject();
        NoteTO noteTO = new NoteTO();
        noteTO.setNoteId(UUID.fromString(jsonObject.getString("noteId")));
        noteTO.setName(jsonObject.getString("name"));
        noteTO.setDescription(jsonObject.getString("description"));
        noteTO.setDateTime(Date.from(Instant.parse(jsonObject.getString("dateTime"))));
        PrintWriter out = response.getWriter();
        out.println(noteService.updateNote(noteTO));
    }
}
