<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<div class="container">
    <div class="card">
        <article class="card-body">
            <c:if test="${requestScope.created}" >
                <p class="text-success text-center">Advert has been posted</p>
            </c:if>
            <c:if test="${not empty requestScope.invalidInputDataError}" >
                <p class="text-danger text-center">${requestScope.invalidInputDataError}</p>
            </c:if>
            <h4 class="card-title mb-4 mt-1">Post the advert</h4>
            <form action="/panel/add" method="post">
                <div class="form-group">
                    <label>Car brand</label>
                    <input name="brand" class="form-control" placeholder="Brand" type="text">
                </div> <!-- form-group// -->
                <div class="form-group">
                    <label>Car model</label>
                    <input name="model" class="form-control" placeholder="Model" type="text">
                </div> <!-- form-group// -->
                <div class="form-group">
                    <label>Registration year</label>
                    <input name="year" class="form-control" placeholder="Registration year" type="text">
                </div> <!-- form-group// -->
                <div class="form-group">
                    <label>Mileage</label>
                    <input name="mileage" class="form-control" placeholder="Mileage" type="text">
                </div> <!-- form-group// -->
                <div class="form-group">
                    <label>Description</label>
                    <textarea name="description" class="form-control" placeholder="Description"></textarea>
                </div> <!-- form-group// -->
                <div class="form-group">
                    <label>Price</label>
                    <input name="price" class="form-control" placeholder="Price" type="text">
                </div> <!-- form-group// -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Post  </button>
                </div> <!-- form-group// -->
            </form>
        </article>
    </div>
</div>

</body>
</html>
