<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<layout:page-template>
    <jsp:attribute name="content">
        <div class="row" style="margin-top: 1em">
            <div class="col-lg-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Your data</h4>
                    </div>
                    <div class="panel-body table-responsive">
                        <table class="table">
                            <tbody>
                                <tr>
                                    <td><b>Email:</b></td>
                                    <td>${user.email}</td>
                                </tr>
                                <tr>
                                    <td><b>Password:</b></td>
                                    <td>   ${user.password}</td>
                                </tr>
                                <tr>
                                    <td><b>Role:</b></td>
                                    <td>${user.role}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</layout:page-template>
