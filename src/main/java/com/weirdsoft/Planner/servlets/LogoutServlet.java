package com.weirdsoft.Planner.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Logout", value = "/logout")
public class LogoutServlet extends HttpServlet {
    
    @Override
    public void init() {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        
        response.sendRedirect("/");
    }
}