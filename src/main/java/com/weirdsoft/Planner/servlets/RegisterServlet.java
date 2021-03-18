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

@WebServlet(name = "Register", value = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService;

    public RegisterServlet(){
        UserDao dao = new UserDaoImpl();
        userService = new UserServiceImpl(dao);
    }
    
    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("error", "");
        
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/Auth/Register.jsp");
        view.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                
        request.setAttribute("error", "");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        String destPage = "/month";
        if (!password.equals(confirmPassword)) {
            destPage = "/register";
            request.setAttribute("error", "Passwords wasn't the same");
        } else {
            User user = registerUser(email, name, password);
            if (user == null) {
                destPage = "/register";
                request.setAttribute("error", "User with such email exists");
            } else {
                HttpSession session = request.getSession();        
                session.setAttribute("user", user);
            }
        }
        
        RequestDispatcher view = request.getRequestDispatcher(destPage);
        view.forward(request, response);
    }
    
    private User registerUser(String email, String name, String password) {
        return userService.Register(name, email, password);
    }
}