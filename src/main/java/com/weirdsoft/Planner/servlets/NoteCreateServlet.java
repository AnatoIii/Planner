package com.weirdsoft.Planner.servlets;

import com.weirdsoft.Planner.dao.UserDao;
import com.weirdsoft.Planner.dao.impl.UserDaoImpl;
import com.weirdsoft.Planner.models.dtos.CategoryTO;
import com.weirdsoft.Planner.services.UserService;
import com.weirdsoft.Planner.services.impl.UserServiceImpl;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "NoteCreate", value = "/create")
public class NoteCreateServlet extends HttpServlet {
    private final UserService userService;

    public NoteCreateServlet(){
        UserDao dao = new UserDaoImpl();
        userService = new UserServiceImpl(dao);
    }
    
    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        
        ArrayList<CategoryTO> categories = new ArrayList<>();
        categories.add(new CategoryTO("Category 1", UUID.randomUUID()));
        categories.add(new CategoryTO("Category 2", UUID.randomUUID()));
        categories.add(new CategoryTO("Category 3", UUID.randomUUID()));
        
        request.setAttribute("categories", categories);
        
        RequestDispatcher view = request.getRequestDispatcher("Pages/Forms/createNote.jsp");
        view.forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        UUID categoryId = UUID.fromString(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        
        String destPage = "Auth/Success.jsp";
        
        RequestDispatcher view = request.getRequestDispatcher(destPage);
        view.forward(request, response);
    }
}