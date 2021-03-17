/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weirdsoft.Planner.filters;

import com.weirdsoft.Planner.Configuration;
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
@WebFilter(filterName = "pageFilter", value = {"/*"})
public class PageFilter implements Filter {

    
    public void init(FilterConfig arg0) throws ServletException { }
    
    public void destroy() {}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        
        String route = req.getRequestURL().toString();
        String path = getPath(route);
        if (path.equals(Configuration.GLASSFISH_ROUTE + "/")
                || path.equals(Configuration.GLASSFISH_ROUTE + "/register") 
                || path.equals(Configuration.GLASSFISH_ROUTE + "/login") 
                || path.startsWith(Configuration.GLASSFISH_ROUTE + "/images")) {
            fc.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();
            User user = null;
            try {
                user = (User)session.getAttribute("user");
            } catch (Exception e) {}

            if (user != null) {
                fc.doFilter(request, response);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect(Configuration.GLASSFISH_ROUTE + "/");
            }

        }
    }
    
    private String getPath(String url) {
        String res = url.substring(url.indexOf('/', 9));
        return res;
    }
}
