<%-- 
    Document   : month
    Created on : Feb 17, 2021, 9:05:09 PM
    Author     : kater
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
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
                            <td>
                                <div class="day-number">
                                    <c:out value="${val}" />
                                </div>
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
                    Please, select date
                </p>
            </div>
        </div>
    </body>
</html>
