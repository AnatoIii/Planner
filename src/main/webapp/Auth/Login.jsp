<%-- 
    Document   : Login
    Created on : Feb 17, 2021, 11:12:30 PM
    Author     : anatolii
--%>

<%@page contentType="text/html" pageEncoding="windows-1252" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            <%@include file="/Auth/style.css"%>
            <%@include file="/styles.css"%>
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <jsp:include page="/Components/LinkRel.jsp"/>
        <title>Login | Planner</title>
    </head>
    <body>
        <div class="name">
            <h3>Planner</h3>
        </div>
        <div class="login_form">
            <div class="header">
                <h3>Log in</h3>
            </div>
            <div class="login">
                <div class="login-card">
                    <form class="flex-center flex-column" [formGroup]="loginForm" action="login" method="POST">
                        <div class="email">
                            <div class="label"><label for="email">Email</label></div>
                            <input id="email" name="email" type="text" placeholder="Enter email" required minlength="4">
                        </div>
                        <div class="password">
                            <div class="label">Password</div>
                            <input id="password" name="password" type="password" placeholder="Enter password" required minlength="4">
                        </div>
                        <div class="error">
                            <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
                        </div>
                        <div class="button-container">
                            <button type="submit" id="login_button">Login</button>
                        </div>
                    </form>
                </div>
            </div>

            <div class="toRegister">
                <a href="register">Create an account</a>
            </div>
        </div>
    </body>
</html>
