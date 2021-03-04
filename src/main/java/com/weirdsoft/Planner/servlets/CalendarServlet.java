/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weirdsoft.Planner.servlets;

import com.weirdsoft.Planner.Month;
import com.weirdsoft.Planner.dao.NoteDao;
import com.weirdsoft.Planner.dao.impl.NoteDaoImpl;
import com.weirdsoft.Planner.models.dtos.NoteTO;
import com.weirdsoft.Planner.services.NoteService;
import com.weirdsoft.Planner.services.impl.NoteServiceImpl;

import com.weirdsoft.Planner.models.User;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kater
 */
@WebServlet(name = "Calendar", value = "/month")
public class CalendarServlet extends HttpServlet {
    private NoteService noteService;

    public CalendarServlet(){
        NoteDao dao = new NoteDaoImpl();
        noteService = new NoteServiceImpl(dao);
    }

    Month month;
    String[] months;
    String[] days;
    ArrayList<int[]> calendar;
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    @Override
    public void init() {
        YearMonth date = YearMonth.now();
        month = new Month(date);

        days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        
        calendar = new ArrayList<int[]>();
        setCalendar(date, month);
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        YearMonth date;
        User user = (User)request.getSession().getAttribute("user");
        if (request.getParameter("year") == null || request.getParameter("month") == null) {
            date = YearMonth.now();
        } else {
            int yearParam = Integer.parseInt(request.getParameter("year"));
            int monthParam = Integer.parseInt(request.getParameter("month"));
            date = YearMonth.of(yearParam, monthParam);
        }

        month = new Month(date);
        setCalendar(date, month);

        List<NoteTO> newNotes = noteService.getByMonth(Date.from(date.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant()), user.getUserId());
        List<CalendarNote> notes = newNotes.stream().map(this::mapNoteTO2CalendarNote).collect(Collectors.toList());

        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        request.setAttribute("month", month);
        request.setAttribute("calendar", calendar);
        request.setAttribute("days", days);
        request.setAttribute("notes", notes);

        RequestDispatcher view = request.getRequestDispatcher("Pages/month.jsp");
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void setCalendar(YearMonth date, Month month) {
        calendar.clear();
        int dates[] = new int[7];
        for (int i = 1; i <= month.getLength(); i++) {
            int dayOfWeek = date.atDay(i).getDayOfWeek().getValue();
            dates[dayOfWeek - 1] = i;
            if (dayOfWeek == 7 || i == month.getLength()) {
                calendar.add(dates);
                dates = new int[7];
            }
        }
    }

    private CalendarNote mapNoteTO2CalendarNote(NoteTO note){
        return new CalendarNote(note.getNoteId().toString(), note.getName(),note.getDescription(),
                timeFormat.format(note.getDateTime()),
                note.getDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public static class CalendarNote{
        String id;
        String name;
        String description;
        String time;
        LocalDate date;
        String creatorId;

        public CalendarNote(String id, String noteName, String noteDescription, String noteTime, LocalDate noteDate) {
            this.id = id;
            name = noteName;
            description = noteDescription;
            time = noteTime;
            date = noteDate;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getTime() {
            return time;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getCreatorId() {
            return creatorId;
        }
    }

}
