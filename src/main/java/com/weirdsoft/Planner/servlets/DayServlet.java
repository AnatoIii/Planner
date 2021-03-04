/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weirdsoft.Planner.servlets;

import com.weirdsoft.Planner.dao.NoteDao;
import com.weirdsoft.Planner.dao.impl.NoteDaoImpl;
import com.weirdsoft.Planner.models.User;
import com.weirdsoft.Planner.models.dtos.NoteTO;
import com.weirdsoft.Planner.services.NoteService;
import com.weirdsoft.Planner.services.impl.NoteServiceImpl;
import com.weirdsoft.Planner.servlets.CalendarServlet.CalendarNote;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kater
 */
@WebServlet(name = "Day", value = "/day")
public class DayServlet extends HttpServlet {
    private final NoteService noteService;
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
    public DayServlet(){
        NoteDao noteDao = new NoteDaoImpl();
        noteService = new NoteServiceImpl(noteDao);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        LocalDate date = null;
                        
        User user = (User)request.getSession().getAttribute("user");
         
        if (request.getParameter("date") != null) {
            date = LocalDate.parse(request.getParameter("date"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy").withLocale(new Locale("en"));
            
            Date dateTime = java.sql.Date.valueOf(date);
            List<CalendarNote> notesList = noteService.getByDate(dateTime, user.getUserId())
                    .stream()
                    .map(this::mapNoteTO2CalendarNote)
                    .collect(Collectors.toList());
            
            request.setAttribute("notes", notesList);

            request.setAttribute("date", formatter.format(date));
        }

        RequestDispatcher view = request.getRequestDispatcher("Pages/day.jsp");
        request.setAttribute("date", date);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        
        UUID noteId = UUID.fromString(request.getParameter("noteId"));
        noteService.deleteNote(noteId, user.getUserId());
        
        String date = request.getParameter("date");
        
        response.sendRedirect("/day?date=" + date);  
    }
    
    private CalendarNote mapNoteTO2CalendarNote(NoteTO note){
    return new CalendarNote(note.getNoteId().toString(), note.getName(),note.getDescription(),
            timeFormat.format(note.getDateTime()),
            note.getDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
        
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
