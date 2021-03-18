package com.weirdsoft.Planner.servlets;

import com.weirdsoft.Planner.dao.CategoryDao;
import com.weirdsoft.Planner.dao.NoteDao;
import com.weirdsoft.Planner.dao.impl.CategoryDaoImpl;
import com.weirdsoft.Planner.dao.impl.NoteDaoImpl;
import com.weirdsoft.Planner.models.Category;
import com.weirdsoft.Planner.models.User;
import com.weirdsoft.Planner.models.dtos.NoteTO;
import com.weirdsoft.Planner.services.CategoryService;
import com.weirdsoft.Planner.services.NoteService;
import com.weirdsoft.Planner.services.impl.CategoryServiceImpl;
import com.weirdsoft.Planner.services.impl.NoteServiceImpl;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "NoteCreate", value = "/create")
public class NoteCreateServlet extends HttpServlet {
    private final CategoryService categoryService;
    @EJB
    private NoteService noteService;

    public NoteCreateServlet(){
        CategoryDao dao = new CategoryDaoImpl();
        categoryService = new CategoryServiceImpl(dao);
    }
    
    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        
        List<Category> categories = categoryService.getAll();
        
        request.setAttribute("categories", categories);
        
        RequestDispatcher view = request.getRequestDispatcher("Pages/Forms/createNote.jsp");
        view.forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                
        User user = (User)request.getSession().getAttribute("user");
        
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        UUID categoryId = UUID.fromString(request.getParameter("categoryId"));
        String description = request.getParameter("description");
      
        NoteTO note = new NoteTO();
        note.setDescription(description);
        note.setName(name);
        note.setCreatorId(user.getUserId());
        
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            Date dateTime = dateFormat.parse(date + " " + time);
            
            note.setDateTime(dateTime);
        } catch (ParseException ex) {
            Logger.getLogger(NoteCreateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        NoteTO noteRes = noteService.createNote(note);
        
        String destPage = "/Planner-1.0-SNAPSHOT/month";
        
        response.sendRedirect(destPage);
    }
}