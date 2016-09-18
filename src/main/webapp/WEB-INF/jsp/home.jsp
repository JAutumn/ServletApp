<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Servlet Application Home page</title>
        <link rel="stylesheet" href="${resPath}/bootstrap/3.3.7-1/css/bootstrap.min.css">
        <link rel="stylesheet" href="${resPath}/bootstrap/3.3.7-1/css/bootstrap-theme.min.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row bg-primary">
                <div class="col-lg-12">
                    <div class="page-header">
                        <h1>Servlet Application
                            <br/>
                            <small style="color: white">Hello ${user.name}!</small>
                        </h1>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 1em">
                <div class="col-lg-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4>Your data</h4>
                        </div>
                        <div class="panel-body table-responsive">
                            <table class="table">
                                <tbody>
                                <tr>
                                    <td><b>Email:</b></td>
                                    <td>${user.email}</td>
                                </tr>
                                <tr>
                                    <td><b>Password:</b></td>
                                    <td>   ${user.password}</td>
                                </tr>
                                <tr>
                                    <td><b>Role:</b></td>
                                    <td>${user.role}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <form method="post" action="${pagesPath}/logout">
                                            <button type="submit" class="btn btn-primary">Logout</button>
                                        </form>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <c:if test="${user.role eq 'ADMIN'}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>Users</h4>
                            </div>
                            <div class="panel-body table-responsive">
                                <table class="table table-striped table-hover table-bordered">
                                    <thead>
                                        <tr>
                                            <th style="text-align: center">Name</th>
                                            <th style="text-align: center">Email</th>
                                            <th style="text-align: center">Password</th>
                                            <th style="text-align: center">Role</th>
                                            <th style="text-align: center">Edit link</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="tableUser" items="${users}">
                                        <tr>
                                            <td>${tableUser.name}</td>
                                            <td>${tableUser.email}</td>
                                            <td>${tableUser.password}</td>
                                            <td>${tableUser.role}</td>
                                            <td style="text-align: center">
                                                <a class="btn btn-primary" href="${pagesPath}/users/${tableUser.id}">
                                                    edit
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div
                    </c:if>
                </div>
            </div>
        </div>
        <script src="${resPath}/jquery/3.1.0/jquery.min.js"></script>
        <script src="${resPath}/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    </body>
</html>
