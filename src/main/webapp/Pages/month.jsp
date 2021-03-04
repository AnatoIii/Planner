<%-- 
    Document   : month
    Created on : Feb 17, 2021, 9:05:09 PM
    Author     : kater
--%>
<%@page import="com.weirdsoft.Planner.Month"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.StreamSupport"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@page import="com.weirdsoft.Planner.servlets.CalendarServlet.CalendarNote"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <link href="./styles/month.css" rel="stylesheet" type="text/css">
        <jsp:include page="/Components/LinkRel.jsp"/>
        <title>Month | Planner</title>
        <script>
            function onChangeDate(e)
            {
                const date = e.target.value.split('-');
                const urlParams = new URLSearchParams(window.location.search);
                urlParams.set('year', date[0]);
                urlParams.set('month', date[1]);
                window.location.search = urlParams;
            }
        </script>
    </head>
    <body>
        <jsp:include page="/Components/Header.jsp"/>
        <div class="wrapper">
            <div class="top">
                <div>
                    <h1 class="month-title">${month.getDisplayText()}</h1>
                    <a href="${pageContext.request.contextPath}/create">Add</a>
                </div>
                <input type="month" id="start" name="date"
                       value="${month.getValueForInput()}" onchange="onChangeDate(event)"> 
            </div>

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
                                    <%
                                        String date = ((Month) request.getAttribute("month")).getValueForInput();
                                    %>
                                    <a href="${pageContext.request.contextPath}/day?date=<%=date%>-${String.format("%02d", week[j])}">
                                        <div style="height:100%;width:100%">

                                            <div class="day-number">
                                                <c:out value="${week[j]}" />
                                            </div>

                                            <c:forEach items="${notes}" var="note">
                                                <c:if test="${week[j] == note.date.getDayOfMonth()}">
                                                    <div class="note">
                                                        <p>
                                                            <span class="time">${note.time}</span> -
                                                            <span class="name">${note.name}</span>
                                                        </P>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </a>
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>  
        </div>
    </body>
</html>
