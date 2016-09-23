<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:page-template showNavigation="${false}">
    <jsp:attribute name="content">
        <div class="row" style="margin-top: 5em" >
            <div class="col-lg-4 col-lg-offset-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Login form
                    </div>
                    <div class="panel-body">
                        <div style="padding: 15px">
                            <form method="post" action="${pagesPath}/login" class="form-horizontal">
                                <div class="form-group">
                                    <label for="email" class="control-label">Email</label>
                                    <input type="email" id="email" name="email" class="form-control" placeholder="Email" required/>
                                </div>
                                <div class="form-group">
                                    <span class="text-danger">${emailError}</span>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="control-label">Password</label>
                                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required/>
                                </div>
                                <div class="form-group">
                                    <span class="text-danger">${passError}</span>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Sign in</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</layout:page-template>
