<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Servlet Application User Edit page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
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
            <div class="row" style="margin-top: 3em;">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4>User Editing</h4>
                        </div>
                        <div class="panel-body table-responsive">
                            <form method="POST" action="${pageContext.request.contextPath}/users/${targetUser.id}">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td><label for="name" class="control-label">Name</label></td>
                                            <td><input type="text" class="form-control" id="name" name="name" value="${targetUser.name}" required/></td>
                                        </tr>
                                        <tr>
                                            <td><label for="email" class="control-label">Email</label></td>
                                            <td><input type="text" class="form-control" id="email" name="email" value="${targetUser.email}" required/></td>
                                        </tr>
                                        <tr>
                                            <td><label for="password" class="control-label">Password</label></td>
                                            <td><input type="text" class="form-control" id="password" name="password"value="${targetUser.password}" required/></td>
                                        </tr>
                                        <tr>
                                            <td><label for="role" class="control-label">Role</label></td>
                                            <td><input type="text" class="form-control" id="role" name="role" value="${targetUser.role}" required/></td>
                                        </tr>
                                        <tr>
                                           <td colspan="2">
                                               <div class="pull-right">
                                                   <button type="submit" class="btn btn-primary">Save</button>
                                               </div>
                                           </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
