<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div class="row" style="margin-top: 5em" >
                <div class="col-lg-4 col-lg-offset-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Login form
                        </div>
                        <div class="panel-body">
                            <div style="padding: 15px">
                                <form method="post" action="${pagesPath}/login" class="form-horizontal">
                                    <div class="form-group">
                                        <label for="email" class="control-label">Email</label>
                                        <input type="email" id="email" name="email" class="form-control" placeholder="Email" required/>
                                    </div>
                                    <div class="form-group">
                                        <span class="text-danger">${emailError}</span>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="control-label">Password</label>
                                        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required/>
                                    </div>
                                    <div class="form-group">
                                        <span class="text-danger">${passError}</span>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary">Sign in</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="${resPath}/jquery/3.1.0/jquery.min.js"></script>
        <script src="${resPath}/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    </body>
</html>
