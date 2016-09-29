<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<layout:page-template showNavigation="${false}">
    <jsp:attribute name="content">
        <fmt:message key="login.loginForm" bundle="${msg}" var="loginFormMsg"/>
        <fmt:message key="login.signIn" bundle="${msg}" var="signInMsg"/>

        <div class="row" style="margin-top: 5em" >
            <div class="col-lg-4 col-lg-offset-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">${loginFormMsg}</div>
                    <div class="panel-body">
                        <div style="padding: 15px">
                            <form method="post" action="${pagesPath}/login" class="form-horizontal">
                                <div class="form-group">
                                    <label for="email" class="control-label">${loginMsg}</label>
                                    <input type="email" id="email" name="email" class="form-control" placeholder="${loginMsg}" required/>
                                </div>
                                <div class="form-group">
                                    <span class="text-danger">
                                        <c:out value="${emailError}"/>
                                    </span>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="control-label">${passwordMsg}</label>
                                    <input type="password" id="password" name="password" class="form-control" placeholder="${passwordMsg}" required/>
                                </div>
                                <div class="form-group">
                                    <span class="text-danger">
                                        <c:out value="${passError}"/>
                                    </span>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">${signInMsg}</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</layout:page-template>
