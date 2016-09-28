<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:page-template>
    <jsp:attribute name="content">
        <div class="row" style="margin-top: 1em">
            <div class="col-lg-12">
                <c:if test="${user.role eq 'ADMIN'}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">Users</h4>
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
                                        <th style="text-align: center">Delete link</th>
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
                                            <td style="text-align: center">
                                                <form method="post" action="${pagesPath}/users/delete/${tableUser.id}">
                                                    <button class="btn btn-primary" type="submit">delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="5"></td>
                                        <td style="text-align: center">
                                            <a class="btn btn-primary " href="${pagesPath}/users/add">
                                                Add
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </jsp:attribute>
</layout:page-template>