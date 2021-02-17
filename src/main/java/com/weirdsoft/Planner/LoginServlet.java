package com.weirdsoft.Planner;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        
        request.setAttribute("error", "");
        
        RequestDispatcher view = request.getRequestDispatcher("Auth/Login.jsp");
        view.forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String destPage = "Auth/Success.jsp";
        if (!email.equals("admin"))
        {
            destPage = "Auth/Login.jsp";
            request.setAttribute("error", "Incorrect email / password");
        }
        
        RequestDispatcher view = request.getRequestDispatcher(destPage);
        view.forward(request, response);
    }

    public void destroy() {
    }
}