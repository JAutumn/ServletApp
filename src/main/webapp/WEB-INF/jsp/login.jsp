<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Servlet Application login page</title>
    </head>
    <body>
        <form method="post" action="login">
            <div>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Email">
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Password">
            </div>
            <button type="submit">Sign in</button>
        </form>
        <c:if test="${not empty error}">
            <span style="color: red">${error}</span>
        </c:if>
    </body>
</html>
