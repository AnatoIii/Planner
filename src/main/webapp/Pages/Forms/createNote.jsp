<%-- 
    Document   : createNote
    Created on : Mar 3, 2021, 6:54:40 PM
    Author     : anato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="styles/createNote.css" rel="stylesheet" type="text/css">
        <jsp:include page="/Components/LinkRel.jsp"/>
        <title>New Note | Planner</title>
    </head>
    <body>
        <jsp:include page="/Components/Header.jsp"/>
        <div class="container">
            <div class="content">
                <h2>Create new note</h2>
                <form method="POST" action="create">
                    <div class="input">
                        <div class="label">Name</div>
                        <input id="name" name="name" type="text" placeholder="Enter note name" required minlength="5">
                    </div>
                    <div class="input">
                        <div class="label">Time</div>
                        <input id="time" name="time" type="time" required>
                    </div>
                    <div class="input">
                        <div class="label">Date</div>
                        <input id="date" name="date" type="date" required>
                    </div>
                    <div class="input">
                        <div class="label">Category</div>
                        <select id="categoryId" name="categoryId" required>
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}">
                                    ${category.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="textarea">
                        <div class="label">Description</div>
                        <textarea id="description" name="description" required></textarea>
                    </div>
                    <div class="button-container">
                        <button type="submit" id="submit_button">Create</button>
                    </div>
                </form>
            </div>
        </div>
        <script>
            function getTodayDate() {
                const date = new Date();
                const year = date.getFullYear();
                const month = date.getMonth() + 1;
                const day = date.getDay();

                return year + "-" + (month > 9 ? month : "0" + month) + "-" + (day > 9 ? day : "0" + day);
            }
            
            document.getElementById('date').value = getTodayDate();
        </script>
    </body>
</html>
