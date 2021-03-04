<%-- 
    Document   : Header
    Created on : Mar 3, 2021, 10:21:30 PM
    Author     : anato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<c:if test="${userName != null}">
    <div class="header">
        <div class="icon">
            <a href="/month"><img src="/favicon.png" height="20px"></a>
        </div>
        <div class="username">
            <div>
                <c:out value="${userName}" />
            </div>
            <img src="https://image.flaticon.com/icons/png/512/130/130925.png" height="15px" onclick="logout()">
        </div>
    </div>
</c:if>
    

<script>
    function logout() {
        const http = new XMLHttpRequest();
        http.open("POST", "/logout");
        http.send();
        window.location = "/";
    }
</script>

<style>
    .header {
        width: 100%;
        background: #e0e0e0;
        padding: 10px 0;
        margin-bottom: -10px;
        display: flex;
        justify-content: space-between;
    }
    
    .header .icon {
        margin-left: 60px;
    }
    
    .header .username {
        margin-right: 60px;
        display: flex;
        align-items: center;
    }
    .header .username div {
        margin-right: 20px;
    }
    .header .username img {
        cursor: pointer;
    }
</style>