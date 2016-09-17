<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Servlet Application User Edit page</title>
    </head>
    <body>
        <div>
            <h1>User Edit</h1>
            <form method="POST" action="${pageContext.request.contextPath}/users/${targetUser.id}">
                <table>
                    <tbody>
                        <tr>
                            <td><label for="name">Name</label></td>
                            <td><input type="text" id="name" name="name" value="${targetUser.name}" required/></td>
                        </tr>
                        <tr>
                            <td><label for="email">Email</label></td>
                            <td><input type="text" id="email" name="email" value="${targetUser.email}" required/></td>
                        </tr>
                        <tr>
                            <td><label for="password">Password</label></td>
                            <td><input type="text" id="password" name="password"value="${targetUser.password}" required/></td>
                        </tr>
                        <tr>
                            <td><label for="role">Role</label></td>
                            <td><input type="text" id="role" name="role" value="${targetUser.role}" required/></td>
                        </tr>
                    </tbody>
                </table>
                <button type="submit">Save</button>
            </form>
        </div>

    </body>
</html>
