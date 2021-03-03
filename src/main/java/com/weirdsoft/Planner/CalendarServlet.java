/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weirdsoft.Planner;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    Month month;
    String[] months;
    String[] days;
    List<Note> notes;
    ArrayList<int[]> calendar;

    @Override
    public void init() {
        YearMonth date = YearMonth.now();
        month = new Month(date);

        days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        notes = new ArrayList<>();
        notes.add(new Note("Note 1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "08:00", LocalDate.of(2021, 3, 7)));
        notes.add(new Note("Note 2",
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "10:00", LocalDate.of(2021, 3, 9)));
        notes.add(new Note("Note 3",
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "10:00", LocalDate.of(2021, 3, 7)));
        notes.add(new Note("Note 4",
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "10:00", LocalDate.of(2021, 3, 2)));
        notes.add(new Note("Note 5",
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "10:00", LocalDate.of(2021, 3, 23)));

        calendar = new ArrayList<int[]>();
        setCalendar(date, month);
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        YearMonth date;
        if (request.getParameter("year") == null || request.getParameter("month") == null) {
            date = YearMonth.now();
        } else {
            int yearParam = Integer.parseInt(request.getParameter("year"));
            int monthParam = Integer.parseInt(request.getParameter("month"));
            date = YearMonth.of(yearParam, monthParam);
        }

        month = new Month(date);
        setCalendar(date, month);

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
        for (int i = 1; i <= month.length; i++) {
            int dayOfWeek = date.atDay(i).getDayOfWeek().getValue();
            dates[dayOfWeek - 1] = i;
            if (dayOfWeek == 7 || i == month.length) {
                calendar.add(dates);
                dates = new int[7];
            }
        }
    }

}
