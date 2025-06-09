<%-- 
    Document   : login
    Created on : May 24, 2025, 10:13:04 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <p>Visit Count: ${visitCount}</p>
        <form action="login" method="POST">
            Username: <input type="text" name="username" value="" /><br>
            Password: <input type="password" name="password" value="" /><br>
            <input type="submit" value="Login" />
        </form>
        <p style="color: red">${requestScope.errorMessage}</p>
    </body>
</html>
