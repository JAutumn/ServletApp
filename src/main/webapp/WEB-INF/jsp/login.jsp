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
            <div>
                <label for="description">Description</label>
                <input type="text" id="description" name="description" placeholder="Description">
            </div>
            <button type="submit">Sign in</button>
        </form>
    </body>
</html>
