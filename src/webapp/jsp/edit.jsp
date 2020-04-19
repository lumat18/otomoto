<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>


<div class="container">
    <div class="card">
        <article class="card-body">
            <h4 class="card-title mb-4 mt-1">Edit user</h4>
            <form action="/panel/edit" method="post">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" class="form-control" value="${user.name}" name="name">
                </div>
                <div class="form-group">
                    <label for="surname">Surname:</label>
                    <input type="text" id="surname" class="form-control" value="${user.surname}" name="surname">
                </div>
                <input type="hidden" name="login" value="${user.login}">
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </article>
    </div>
</div>

</body>
</html>