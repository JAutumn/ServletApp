<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:page-template>
    <jsp:attribute name="content">
        <fmt:message key="usrEdt.title" bundle="${msg}" var="titleMsg"/>
        <form method="POST" action="${pagesPath}/users/${targetUser.id}" enctype="multipart/form-data">
            <div class="row" style="margin-top: 3em;">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4>${titleMsg}</h4>
                            <h4><c:out value="${editingErr}"/></h4>
                        </div>
                        <div class="panel-body table-responsive">
                            <div class="row">
                                <div class="col-lg-3">
                                    <div class="row">
                                        <img src="${pagesPath}/avatars/${targetUser.id}" class="img-rounded" height="180" width="180" style="margin: 30px"/>
                                    </div>
                                </div>
                                <div class="col-lg-9"><table class="table">
                                    <tbody>
                                        <tr>
                                            <td><label for="name" class="control-label">${nameMsg}</label></td>
                                            <td>
                                                <input type="text" class="form-control" id="name" name="name" value="<c:out value="${targetUser.name}"/>" required placeholder="${nameMsg}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label for="email" class="control-label">${emailMsg}</label></td>
                                            <td><input type="email" class="form-control" id="email" name="email" value="<c:out value="${targetUser.email}"/>" required placeholder="${emailMsg}"/></td>
                                        </tr>
                                        <tr>
                                            <td><label for="password" class="control-label">${passwordMsg}</label></td>
                                            <td><input type="text" class="form-control" id="password" name="password"value="<c:out value="${targetUser.password}"/>" required placeholder="${passwordMsg}"/></td>
                                        </tr>
                                        <tr>
                                            <td><label for="role" class="control-label">${roleMsg}</label></td>
                                            <td><input type="text" class="form-control" id="role" name="role" value="<c:out value="${targetUser.role}"/>" required placeholder="${roleMsg}"/></td>
                                        </tr>
                                        <tr>
                                            <td><label for="avatar" class="control-label">${avatarMsg}</label> </td>
                                            <td><input type="file" id="avatar" name="avatar" required/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <div class="pull-right">
                                                    <button type="submit" class="btn btn-primary">${saveMsg}</button>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:attribute>
</layout:page-template>
