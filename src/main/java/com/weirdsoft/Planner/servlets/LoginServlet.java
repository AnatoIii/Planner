package com.weirdsoft.Planner.servlets;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (true) {
            response.sendError(404);
            // response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        
        request.setAttribute("error", "");
        
        RequestDispatcher view = request.getRequestDispatcher("Auth/Login.jsp");
        view.forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                
        request.setAttribute("error", "");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String destPage = "Auth/Success.jsp";
        if (!email.equals("admin"))
        {
            HttpSession session = request.getSession();
            
            destPage = "Auth/Login.jsp";
            request.setAttribute("error", "Incorrect email / password");
        }
        
        RequestDispatcher view = request.getRequestDispatcher(destPage);
        view.forward(request, response);
    }
}