package com.weirdsoft.Planner;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Register", value = "/register")
public class RegisterServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher view = request.getRequestDispatcher("Auth/Register.jsp");
        view.forward(request, response);
    }

    public void destroy() {
    }
}