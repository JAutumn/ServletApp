<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:page-template>
    <jsp:attribute name="content">
        <fmt:message key="usrView.editLnk" bundle="${msg}" var="editLnkMsg"/>
        <fmt:message key="usrView.delLnk" bundle="${msg}" var="delLnkMsg"/>
        <fmt:message key="usrView.add" bundle="${msg}" var="addMsg"/>
        <fmt:message key="usrView.edit" bundle="${msg}" var="editMsg"/>
        <fmt:message key="usrView.del" bundle="${msg}" var="delMsg"/>

        <div class="row" style="margin-top: 1em">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">${usersMsg}</h4>
                        <h4><c:out value="${deletingErr}"/></h4>
                    </div>
                    <div class="panel-body table-responsive">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th style="text-align: center">${avatarMsg}</th>
                                    <th style="text-align: center">${nameMsg}</th>
                                    <th style="text-align: center">${emailMsg}</th>
                                    <th style="text-align: center">${passwordMsg}</th>
                                    <th style="text-align: center">${roleMsg}</th>
                                    <th style="text-align: center">${editLnkMsg}</th>
                                    <th style="text-align: center">${delLnkMsg}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="tableUser" items="${users}">
                                    <tr>
                                        <td style="text-align: center">
                                            <img src="${pagesPath}/avatars/${tableUser.id}" height="40" width="40" class="img-circle">
                                        </td>
                                        <td><c:out value="${tableUser.name}"/></td>
                                        <td><c:out value="${tableUser.email}"/></td>
                                        <td><c:out value="${tableUser.password}"/></td>
                                        <td><c:out value="${tableUser.role}"/></td>
                                        <td style="text-align: center">
                                            <a class="btn btn-primary" href="${pagesPath}/users/${tableUser.id}">
                                                ${editMsg}
                                            </a>
                                        </td>
                                        <td style="text-align: center">
                                            <form method="post" action="${pagesPath}/users/delete/${tableUser.id}">
                                                <button class="btn btn-primary" type="submit">${delMsg}</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="6"></td>
                                    <td style="text-align: center">
                                        <a class="btn btn-primary " href="${pagesPath}/users/add">
                                            ${addMsg}
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</layout:page-template>