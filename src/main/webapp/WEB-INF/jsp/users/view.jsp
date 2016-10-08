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

        <%--context = '/' - cause we have context path in pagesPath variable--%>
        <c:url var="getUrlWithPaging" value="${pagesPath}/users" context="/">
            <c:if test="${not empty param.page}">
                <c:param name="page" value="${param.page}"/>
            </c:if>
            <c:if test="${not empty param.limit}">
                <c:param name="limit" value="${param.limit}"/>
            </c:if>
        </c:url>
        <c:url var="getUrlWithSorting" value="${pagesPath}/users" context="/">
            <c:if test="${not empty param.sort}">
                <c:param name="sort" value="${param.sort}"/>
            </c:if>
            <c:if test="${not empty param.type}">
                <c:param name="type" value="${param.type}"/>
            </c:if>
        </c:url>
        <c:set var="sortingDelimiter" value="${empty param.page and empty param.limit ? '?' : '&'}"/>
        <c:set var="pagingDelimiter" value="${empty param.sort and empty param.type ? '?' : '&'}"/>

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
                                    <th style="text-align: center; vertical-align: middle">${avatarMsg}</th>
                                    <th style="text-align: center">
                                        <span>${nameMsg}</span>
                                        <div style="display: inline-block;vertical-align: middle">
                                            <div>
                                                <a href="${getUrlWithPaging}${sortingDelimiter}sort=name&type=asc">&#9650;</a>
                                            </div>
                                            <div>
                                                <a href="${getUrlWithPaging}${sortingDelimiter}sort=name&type=desc">&#9660;</a>
                                            </div>
                                        </div>
                                    </th>
                                    <th style="text-align: center">
                                        <span>${emailMsg}</span>
                                        <div style="display: inline-block; vertical-align: middle">
                                            <div>
                                                <a href="${getUrlWithPaging}${sortingDelimiter}sort=email&type=asc">&#9650;</a>
                                            </div>
                                            <div>
                                                <a href="${getUrlWithPaging}${sortingDelimiter}sort=email&type=desc">&#9660;</a>
                                            </div>
                                        </div>
                                    </th>
                                    <th style="text-align: center">
                                        <span>${passwordMsg}</span>
                                        <div style="display: inline-block; vertical-align: middle">
                                            <div>
                                                <a href="${getUrlWithPaging}${sortingDelimiter}sort=password&type=asc">&#9650;</a>
                                            </div>
                                            <div>
                                                <a href="${getUrlWithPaging}${sortingDelimiter}sort=password&type=desc">&#9660;</a>
                                            </div>
                                        </div>
                                    </th>
                                    <th style="text-align: center">
                                        <span>${roleMsg}</span>
                                        <div style="display: inline-block; vertical-align: middle">
                                            <div>
                                                <a href="${getUrlWithPaging}${sortingDelimiter}sort=role&type=asc">&#9650;</a>
                                            </div>
                                            <div>
                                                <a href="${getUrlWithPaging}${sortingDelimiter}sort=role&type=desc">&#9660;</a>
                                            </div>
                                        </div>
                                    </th>
                                    <th style="text-align: center;vertical-align: middle">${editLnkMsg}</th>
                                    <th style="text-align: center;vertical-align: middle">${delLnkMsg}</th>
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
                                            <a class="btn btn-primary" href="${pagesPath}/users/edit/${tableUser.id}">
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
                            <c:if test="${paging.pagesCount > 1}">
                                <tfoot>
                                    <tr>
                                        <td colspan="9" style="text-align: center">
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination">
                                                    <li class="${paging.pageNumber eq 1 ? 'disabled' : ''}">
                                                        <a href="${getUrlWithSorting}${pagingDelimiter}page=${paging.pageNumber - 1}&limit=5" aria-label="Previous" onclick="return ${paging.pageNumber ne 1};">
                                                            <span aria-hidden="true">&laquo;</span>
                                                        </a>
                                                    </li>

                                                    <c:forEach begin="${paging.pageNumber > paging.pagesCount - 3 ? paging.pagesCount - 3 : paging.pageNumber}" end="${paging.pageNumber + 3 > paging.pagesCount ? paging.pagesCount : paging.pageNumber + 3}" varStatus="status">
                                                        <li class="${paging.pageNumber eq status.current ? 'active' : ''}">
                                                            <a href="${getUrlWithSorting}${pagingDelimiter}page=${status.current}&limit=5">${status.current}</a>
                                                        </li>
                                                    </c:forEach>

                                                    <li class="${paging.pageNumber eq paging.pagesCount ? 'disabled' : ''}">
                                                        <a href="${getUrlWithSorting}${pagingDelimiter}page=${paging.pageNumber + 1}&limit=5" aria-label="Next" onclick="return ${paging.pageNumber ne paging.pagesCount};">
                                                            <span aria-hidden="true">&raquo;</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </nav>
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</layout:page-template>