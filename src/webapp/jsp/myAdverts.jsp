<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<div class="container">
    <c:forEach items="${adverts}" var="advert">
        <div class="card" style="width: 350px; float: left; margin:10px">
            <img src="https://www.pngitem.com/pimgs/m/239-2398533_white-car-png-white-mercedes-transparent-background-png.png"
                 class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">${advert.car.brand} ${advert.car.model}</h5>
                <p class="card-text">Mileage: ${advert.car.mileage}</p>
                <p class="card-text">Year of production: ${advert.car.year}</p><br/>
                <p>
                    <a href="#" class="btn btn-primary" role="button"
                       style="font-weight: bold; font-size: larger">${advert.price}$</a>
                    <span style="margin-left: 60px;">${advert.date}</span>
                </p>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>