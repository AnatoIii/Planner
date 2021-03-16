/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weirdsoft.Planner.filters;

import com.weirdsoft.Planner.models.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anatoliko
 */
//@WebFilter(filterName = "userFilter", value = {"/*"})
public class UserFilter implements Filter {

    
    public void init(FilterConfig arg0) throws ServletException { }
    
    public void destroy() {}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        
        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();
        User user = null;
        try {
            user = (User)session.getAttribute("user");
        } catch (Exception e) {}
        
        if (user != null) {
            req.setAttribute("userName", user.getName());
        }
        
        fc.doFilter(request, response);
    }
}
