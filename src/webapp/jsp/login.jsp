<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<div class="container">
    <div style="max-width: 300px; margin-right: auto; margin-left: auto; margin-top: 80px">
        <div class="card">
            <article class="card-body">
                <h4 class="card-title text-center mb-4 mt-1">Sign in</h4>
                <hr>
                <c:if test="${requestScope.errorLogin}" >
                    <p class="text-danger text-center">${requestScope.errorLogin}</p>
                </c:if>
                <form action="/login" method="post">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                            </div>
                            <input name="login" class="form-control" placeholder="Email or login" type="text">
                        </div> <!-- input-group.// -->
                    </div> <!-- form-group// -->
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                            </div>
                            <input name="pwd" class="form-control" placeholder="******" type="password">
                        </div> <!-- input-group.// -->
                    </div> <!-- form-group// -->
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Login  </button>
                    </div> <!-- form-group// -->
                    <p class="text-center"><a href="#" class="btn">Forgot password?</a></p>
                </form>
            </article>
        </div>
    </div>
</div>

</body>
</html>