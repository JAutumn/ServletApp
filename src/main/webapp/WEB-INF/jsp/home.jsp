<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:page-template>
    <jsp:attribute name="content">
        <fmt:message key="home.data" bundle="${msg}" var="dataMsg"/>

        <div class="row" style="margin-top: 1em">
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">${dataMsg}</h4>
                    </div>
                    <div class="panel-body table-responsive">
                        <div class="row">
                            <div class="col-lg-6">
                                <img src="${pagesPath}/avatars/${user.id}" class="img-rounded" height="180" width="180"/>
                            </div>
                            <div class="col-lg-6">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <b>${emailMsg}:</b>
                                            </td>
                                            <td>
                                                <c:out value="${user.email}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <b>${passwordMsg}:</b>
                                            </td>
                                            <td>
                                               <c:out value="${user.password}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <b>${roleMsg}:</b>
                                            </td>
                                            <td>
                                                <c:out value="${user.role}"/>
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
    </jsp:attribute>
</layout:page-template>
