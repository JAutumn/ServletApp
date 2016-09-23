<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="content" fragment="true" required="true" %>
<%@ attribute name="showNavigation" type="java.lang.Boolean" %>

<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:if test="${empty showNavigation}">
    <c:set var="showNavigation" value="${true}"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Servlet Application Login page</title>
        <link rel="stylesheet" href="${resPath}/bootstrap/3.3.7-1/css/bootstrap.min.css">
        <link rel="stylesheet" href="${resPath}/bootstrap/3.3.7-1/css/bootstrap-theme.min.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row bg-primary">
                <div class="col-lg-12">
                    <div class="page-header">
                        <h1>Servlet Application</h1>
                    </div>
                </div>
            </div>
            <c:if test="${showNavigation}">
                <nav class="navbar navbar-default" style="margin-top: 1em">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand active" href="#">Project Demo</a>
                        </div>

                        <div class="collapse navbar-collapse" id="navbar-collapse">
                            <ul class="nav navbar-nav">
                                <li class="${currentUrl eq pagesPath.concat('/home') ? 'active' : ''}">
                                    <a href="${pagesPath}/home">Home</a>
                                </li>
                                <c:if test="${user.role eq 'ADMIN'}">
                                    <li class="${currentUrl eq pagesPath.concat('/users/') ? 'active' : ''}">
                                        <a href="${pagesPath}/users/">Users</a>
                                    </li>
                                </c:if>
                            </ul>
                            <form method="post" action="${pagesPath}/logout" class="navbar-form navbar-right">
                                <button type="submit" class="btn btn-primary">Logout</button>
                            </form>
                        </div>
                    </div>
                </nav>
            </c:if>
            <jsp:invoke fragment="content"/>
            <script src="${resPath}/jquery/3.1.0/jquery.min.js"></script>
            <script src="${resPath}/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>