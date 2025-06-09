<%-- 
    Document   : list
    Created on : May 26, 2025, 10:46:20 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Todo List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container">
            <div class="header">
                <h1>
                    <i class="fas fa-tasks text-primary"></i> Todo List
                </h1>
                <div class="nav-links">
                    <c:if test="${not empty username}">
                        <a href="add" class="btn btn-success">
                            <i class="fas fa-plus"></i> Add Todo
                        </a>
                        <a href="logout" class="btn btn-outline-danger">
                            <i class="fas fa-sign-out-alt"></i> Logout
                        </a>
                    </c:if>
                </div>
            </div>

            <div class="table-responsive todo-table">
                <table class="table table-hover table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th>Title</th>
                            <th>Status</th>
                            <c:if test="${not empty username}">
                                <th>Actions</th>
                            </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="todo" items="${list}">
                            <tr>
                                <td>${todo.title}</td>
                                <td>
                                    <span class="status-badge ${todo.status ? 'status-done' : 'status-doing'}">
                                        ${todo.status ? "Done" : "Doing"}
                                    </span>
                                </td>
                                <c:if test="${not empty username}">
                                    <td>
                                        <div class="action-buttons">
                                            <a href="edit?id=${todo.id}" class="btn btn-sm btn-primary">
                                                <i class="fas fa-edit"></i> Edit
                                            </a>
                                            <a href="delete?id=${todo.id}" class="btn btn-sm btn-danger" 
                                               onclick="return confirm('Are you sure you want to delete this todo?')">
                                                <i class="fas fa-trash"></i> Delete
                                            </a>
                                        </div>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <footer class="text-center mt-4">
                <small class="text-muted">Visit Count: ${visitCount}</small>
            </footer>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
