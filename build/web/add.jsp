<%-- 
    Document   : add
    Created on : May 26, 2025, 10:36:01 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Todo</title>
    </head>
    <body>
        <h1>Add New Todo</h1>
        <form action="add" method="POST">
            Title: <input type="text" name="title" required />
            <input type="submit" value="Add" />
        </form>
        <a href="list">Back to List</a>
    </body>
</html>
