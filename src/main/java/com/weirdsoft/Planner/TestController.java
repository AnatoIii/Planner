package com.weirdsoft.Planner;

import com.weirdsoft.Planner.jpa.NoteJPA;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
@Controller
public class TestController {
    @Inject
    private Models models;
    @Inject
    private TestBean testBean;

    @GET
    public String testMethod() {
        testBean.setText("Fish");
        models.put("tb",testBean);
        return "/WEB-INF/views/hello.jsp";
    }
}
