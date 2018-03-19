<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        Welcome, ${username}
        <a href="login?logout">Logout</a>
        <c:if test="${isAdmin==true}">
            <h2>Add User</h2>
            <form action="users" method="GET">
                Username: <input type="text" name="username"><br/>
                Password: <input type="password" name="password"><br/>
                <input type="submit" value="Add User">
            </form>
        </c:if>
        <br/>
        ${message}
        <br/>

        <h2>List of Users</h2>
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Hashed and Salted Password</th>
                    <c:if test="${isAdmin}">
                    <th>Delete</th>
                    </c:if>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.hashedandsaltedpassword}</td>
                    <c:if test="${isAdmin}">
                        <%--<c:if test="${user.username!=username}">--%>
                            <td><a href="?username=${user.username}&delete">Delete</a></td>
                        <%--</c:if>--%>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
