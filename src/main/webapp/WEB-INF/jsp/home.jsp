<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Servlet Application login page</title>
</head>
<body>
<form>
    <div>
        Your email: ${sessionScope.get("email")}
    </div>
</form>
</body>
</html>
