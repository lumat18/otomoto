<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>


<div class="container">
    <div class="card">
        <article class="card-body">
            <h4 class="card-title mb-4 mt-1">Register</h4>

            <form action="/register" method="post">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" class="form-control" placeholder="Enter name" name="name">
                </div>
                <div class="form-group">
                    <label for="surname">Surname:</label>
                    <input type="text" id="surname" class="form-control" placeholder="Enter surname" name="surname">
                </div>
                <div class="form-group">
                    <label for="login">Login:</label>
                    <input type="text" class="form-control" id="login" placeholder="Enter login" name="login">
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
                </div>
                <div style="color: red">
                    <c:if test="${not empty requestScope.loginExists}">
                        Login : ${requestScope.loginExists} already exists in system
                    </c:if>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Sign up</button>
                </div>
            </form>
        </article>
    </div>
</div>

</body>
</html>