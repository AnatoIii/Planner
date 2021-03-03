package com.weirdsoft.Planner.servlets;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "startupservlet", value = "/startup")
public class StartupServlet extends HttpServlet {
    public StartupServlet(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
