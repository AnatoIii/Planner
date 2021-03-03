package com.weirdsoft.Planner.servlets;

import com.weirdsoft.Planner.dao.UserDao;
import com.weirdsoft.Planner.dao.impl.UserDaoImpl;
import com.weirdsoft.Planner.models.User;
import com.weirdsoft.Planner.services.UserService;
import com.weirdsoft.Planner.services.impl.UserServiceImpl;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet(){
        UserDao dao = new UserDaoImpl();
        userService = new UserServiceImpl(dao);
    }
    
    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        
        request.setAttribute("error", "");
        
        RequestDispatcher view = request.getRequestDispatcher("Pages/month.jsp");
        view.forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                
        request.setAttribute("error", "");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User loginResult = userService.Login(email, password);
        
        String destPage = "Auth/Success.jsp";
        if (loginResult == null) {
            destPage = "Auth/Login.jsp";
            request.setAttribute("error", "Incorrect email / password");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", loginResult);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(destPage);
        view.forward(request, response);
    }
}