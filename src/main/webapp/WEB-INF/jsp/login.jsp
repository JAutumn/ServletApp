<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Servlet Application Login page</title>
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <div>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Email" required/>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Password" required/>
            </div>
            <button type="submit">Sign in</button>
        </form>
        <c:if test="${not empty error}">
            <span style="color: red">${error}</span>
        </c:if>
    </body>
</html>
