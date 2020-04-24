<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<div class="container">
    <c:forEach items="${requestScope.observedAdvertDTOs}" var="advertDTO">
        <div class="card" style="width: 350px; float: left; margin:10px">
            <img src="https://www.pngitem.com/pimgs/m/239-2398533_white-car-png-white-mercedes-transparent-background-png.png"
                 class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">${advertDTO.advert.car.brand} ${advertDTO.advert.car.model}</h5>
                <p class="card-text">Mileage: ${advertDTO.advert.car.mileage}</p>
                <p class="card-text">Year of production: ${advertDTO.advert.car.year}</p><br/>
                <p>
                    <a href="#" class="btn btn-primary" role="button"
                       style="font-weight: bold; font-size: larger">${advertDTO.advert.price}$</a>
                    <span style="margin-left: 60px;">${advertDTO.advert.date}</span>
                </p>
                <div style="margin-right: 10px">
                    <c:if test="${advertDTO.observed}">
                        <a href="/panel/not-observe?id=${advertDTO.advert.id}">
                            <span style="float: right">Followed <i class="fas fa-star"></i></span>
                        </a>
                    </c:if>
                    <c:if test="${!advertDTO.observed}">
                        <a href="/panel/observe?id=${advertDTO.advert.id}">
                            <span style="float: right">Follow <i class="far fa-star"></i></span>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>