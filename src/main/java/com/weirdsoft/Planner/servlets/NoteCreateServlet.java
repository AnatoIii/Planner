package com.weirdsoft.Planner.servlets;

import com.weirdsoft.Planner.dao.UserDao;
import com.weirdsoft.Planner.dao.impl.UserDaoImpl;
import com.weirdsoft.Planner.services.UserService;
import com.weirdsoft.Planner.services.impl.UserServiceImpl;
import java.io.*;
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
        
        RequestDispatcher view = request.getRequestDispatcher("Pages/Forms/createNote.jsp");
        view.forward(request, response);
    }
}