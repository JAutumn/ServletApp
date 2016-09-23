<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:page-template>
    <jsp:attribute name="content">
        <h1>Something wrong</h1>
        <div>
            <b>Error:</b> ${error}
        </div>
    </jsp:attribute>
</layout:page-template>
