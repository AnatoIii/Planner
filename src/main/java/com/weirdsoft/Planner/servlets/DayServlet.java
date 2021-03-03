/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weirdsoft.Planner.servlets;

import com.weirdsoft.Planner.Note;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    List<Note> notes;

    @Override
    public void init() {
        notes = new ArrayList<>();
        notes.add(new Note("Note 1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "08:00", LocalDate.of(2021, 3, 7)));
        notes.add(new Note("Note 2",
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ",
                "10:00", LocalDate.of(2021, 3, 9)));
        notes.add(new Note("Note 3",
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "12:00", LocalDate.of(2021, 3, 7)));
        notes.add(new Note("Note 4",
                "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful.",
                "14:00", LocalDate.of(2021, 3, 2)));
        notes.add(new Note("Note 5",
                "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.",
                "15:00", LocalDate.of(2021, 3, 23)));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate date;
                response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        if (request.getParameter("date") != null) {
            date = LocalDate.parse(request.getParameter("date"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy").withLocale(new Locale("en"));
            request.setAttribute("date", formatter.format(date));
        }

        request.setAttribute("notes", notes);

        RequestDispatcher view = request.getRequestDispatcher("Pages/day.jsp");
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
