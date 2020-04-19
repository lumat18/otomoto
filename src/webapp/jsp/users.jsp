<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<div class="container">
    <div class="card">
        <article class="card-body">
            <h4 class="card-title mb-4 mt-1">Users</h4>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Login</th>
                        <th>Status</th>
                        <th>Edit</th>
                        <th>Block/Activate</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.login}</td>
                            <td>
                                <c:if test="${user.blocked}">
                                    blocked
                                </c:if>
                                <c:if test="${!user.blocked}">
                                    active
                                </c:if>
                            </td>
                            <td><a href="/panel/edit?login=${user.login}">Edit</a></td>
                            <td>
                                <c:if test="${user.blocked}">
                                    <a href="/panel/activate?login=${user.login}">Activate</a>
                                </c:if>
                                <c:if test="${!user.blocked}">
                                    <a href="/panel/block?login=${user.login}">Block</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </article>
    </div>
</div>

</body>
</html>
