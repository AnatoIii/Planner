<%-- 
    Document   : day
    Created on : Mar 3, 2021, 7:41:08 PM
    Author     : kater
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <link href="./styles/day.css" rel="stylesheet" type="text/css">
        <jsp:include page="/Components/LinkRel.jsp"/>
        <title>Day | Planner</title>
    </head>
    <body>
        <jsp:include page="/Components/Header.jsp"/>
        <div class="wrapper">
            <div class="top">
                <h1>${date}</h1>
                <div class="menu">
                    <a href="${pageContext.request.contextPath}/create">Add</a>
                    <a href="${pageContext.request.contextPath}/month">Back</a>
                </div>
            </div>


            <c:forEach items="${notes}" var="note">
                <div class="note">
                        <p>
                            <span class="time">${note.time}</span> -
                            <span class="name">${note.name}</span>
                        </P>
                        <p class="desc">${note.description}</P>
                    <div class="menu">
                        <!-- <a href="${pageContext.request.contextPath}/edit">Edit</a> -->
                        <a href="${pageContext.request.contextPath}/delete">Delete</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
