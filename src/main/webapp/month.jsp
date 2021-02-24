<%-- 
    Document   : month
    Created on : Feb 17, 2021, 9:05:09 PM
    Author     : kater
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@page import="com.weirdsoft.Planner.Note"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <link href="./styles/month.css" rel="stylesheet" type="text/css">
        <title>Month page</title>
    </head>
    <body>
        <%
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            request.setAttribute("columnHeaders", days);

            List<Note> list = new ArrayList<>();
            Note note1 = new Note("Note 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "08:00");
            Note note2 = new Note("Note 2", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "10:00");
            list.add(note1);
            list.add(note2);
            request.setAttribute("mondayNotes", list);
        %>
        <div class="wrapper">
            <div class="left-side">
                <h1 class="month-title">February 2021</h1>
                <table>
                    <tr>
                        <c:forEach items="${columnHeaders}" var="day">
                            <th>
                                <c:out value="${day}" />
                            </th>
                        </c:forEach>
                    </tr>
                    <tr>
                        <c:forEach begin="1" end="7" var="val">
                            <td class="day">
                                <div class="day-number">
                                    <c:out value="${val}" />
                                </div>
                                <c:if test="${val == '1'}">
                                    <c:forEach items="${mondayNotes}" var="note">
                                        <div class="note">
                                            <p>
                                                <span class="time">${note.time}</span> -
                                                <span class="name">${note.name}</span>
                                            </P>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <c:forEach begin="8" end="14" var="val">
                            <td>
                                <div class="day-number">
                                    <c:out value="${val}" />
                                </div>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <c:forEach begin="15" end="21" var="val">
                            <td>
                                <div class="day-number">
                                    <c:out value="${val}" />
                                </div>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <c:forEach begin="22" end="28" var="val">
                            <td>
                                <div class="day-number">
                                    <c:out value="${val}" />
                                </div>
                            </td>
                        </c:forEach>
                    </tr>
                </table>  
            </div>
            <div class="right-side">
                <p class="hint">
                    You chosen Monday
                </p>
                <c:forEach items="${mondayNotes}" var="note">
                    <div class="note">
                        <p>
                            <span class="time">${note.time}</span> -
                            <span class="name">${note.name}</span>
                        </P>
                        <p class="descr">${note.description}</P>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
