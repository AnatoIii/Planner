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
        <div class="wrapper">
            <div class="left-side">
                <h1 class="month-title">${month.getDisplayText()}</h1>
                <table>
                    <tr>
                        <c:forEach items="${days}" var="day">
                            <th>
                                <c:out value="${day}" />
                            </th>
                        </c:forEach>
                    </tr>

                    <c:forEach items="${calendar}" var="week"> 
                        <tr>
                            <c:forEach begin="0" end="6" var="j">
                                <td class="day">
                                    <c:if test="${week[j] != '0'}">
                                        <div class="day-number">
                                            <c:out value="${week[j]}" />
                                        </div>
                                    </c:if>
                                    <c:if test="${week[j] == '1'}">
                                        <c:forEach items="${notes}" var="note">
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
                    </c:forEach>
                </table>  
            </div>
            <div class="right-side">
                <p class="hint">
                    You chosen Monday
                </p>
                <c:forEach items="${notes}" var="note">
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
