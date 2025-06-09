<%-- 
    Document   : edit
    Created on : Jun 7, 2025, 9:50:04 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Todo</h1>
        <form action="edit" method="post">
            <input type="hidden" name="id" value="${todo.id}" />
            Title: <input type="text" name="title" required value="${todo.title}" /><br>
            Status: <input type="checkbox" name="status" value="1" ${todo.status ? 'checked' : ''} /> Done<br>
            <input type="submit" value="Update" />
        </form>
        <a href="list">Back to List</a>
    </body>
</html>
