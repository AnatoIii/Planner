package com.weirdsoft.Planner;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Register", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("error", "");
        
        RequestDispatcher view = request.getRequestDispatcher("Auth/Register.jsp");
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
            
            destPage = "Auth/Register.jsp";
            request.setAttribute("error", "Incorrect email / password");
        }
        
        RequestDispatcher view = request.getRequestDispatcher(destPage);
        view.forward(request, response);
    }

    @Override
    public void destroy() {
    }
}