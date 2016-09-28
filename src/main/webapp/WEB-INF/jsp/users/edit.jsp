<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:page-template>
    <jsp:attribute name="content">
        <div class="row" style="margin-top: 3em;">
            <div class="col-lg-8">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>User Editing</h4>
                    </div>
                    <div class="panel-body table-responsive">
                        <form method="POST" action="${pagesPath}/users/${targetUser.id}" enctype="multipart/form-data">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td><label for="photo" class="control-label">Photo</label></td>
                                        <td><input type="file" id="photo" name="photo"/></td>
                                    </tr>
                                    <tr>
                                        <td><label for="name" class="control-label">Name</label></td>
                                        <td><input type="text" class="form-control" id="name" name="name" value="${targetUser.name}" required placeholder="Name"/></td>
                                    </tr>
                                    <tr>
                                        <td><label for="email" class="control-label">Email</label></td>
                                        <td><input type="email" class="form-control" id="email" name="email" value="${targetUser.email}" required placeholder="Email"/></td>
                                    </tr>
                                    <tr>
                                        <td><label for="password" class="control-label">Password</label></td>
                                        <td><input type="text" class="form-control" id="password" name="password"value="${targetUser.password}" required placeholder="password"/></td>
                                    </tr>
                                    <tr>
                                        <td><label for="role" class="control-label">Role</label></td>
                                        <td><input type="text" class="form-control" id="role" name="role" value="${targetUser.role}" required placeholder="role"/></td>
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
    </jsp:attribute>
</layout:page-template>
