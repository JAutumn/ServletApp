<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:page-template>
    <jsp:attribute name="content">
        <fmt:message key="error.error" bundle="${msg}" var="errorMsg"/>
        <fmt:message key="error.smthWrong" bundle="${msg}" var="smthWrongMsg"/>

        <h1>${smthWrongMsg}</h1>
        <div>
            <b>${errorMsg}:</b> <c:out value="${error}"/>
        </div>
    </jsp:attribute>
</layout:page-template>
