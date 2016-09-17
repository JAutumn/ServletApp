<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Servlet Application login page</title>
    </head>
    <body>
        <div>
            <h1>Hello ${user.name}!</h1>
            <div style="margin-left: 20px">
                Your data
                <div style="margin-left: 20px">
                    <div>
                        <b>Email:</b> ${user.email}
                    </div>
                    <div>
                        <b>Password:</b> ${user.password}
                    </div>
                    <div>
                        <b>Role:</b> ${user.role}
                    </div>
                    <form method="post" action="${pageContext.request.contextPath}/logout">
                        <button type="submit">Logout</button>
                    </form>
                </div>
            </div>
        </div>
        <c:if test="${user.role eq 'ADMIN'}">
            <div>
                <h3>Users</h3>
                <table border="1px">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Password</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tableUser" items="${users}">
                            <tr>
                                <td>${tableUser.name}</td>
                                <td>${tableUser.email}</td>
                                <td>${tableUser.password}</td>
                                <td>${tableUser.role}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div
        </c:if>
    </body>
</html>
