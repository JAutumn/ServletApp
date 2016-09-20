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
                <input type="email" id="email" name="email" placeholder="Email" required>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Password" required>
            </div>
            <button type="submit">Sign in</button>
        </form>
    </body>
</html>
