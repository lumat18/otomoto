<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<div class="container">
    <div class="card" style="width: 100%; margin: 10px">
        <div class="card-body">
            <c:if test="${not empty requestScope.invalidInputDataError}" >
                <p class="text-danger text-center">${requestScope.invalidInputDataError}</p>
            </c:if>
            <form method="post" action="/panel/search">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="brand">Brand</label>
                        <input name="brand" type="text" class="form-control" id="brand">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="model">Model</label>
                        <input name="model" type="text" class="form-control" id="model">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="minYear">Min year</label>
                        <input name="minYear" type="text" class="form-control" id="minYear">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="minYear">Max year</label>
                        <input name="maxYear" type="text" class="form-control" id="maxYear">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="minYear">Min mileage</label>
                        <input name="minMileage" type="text" class="form-control" id="minMileage">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="minYear">Max mileage</label>
                        <input name="maxMileage" type="text" class="form-control" id="maxMileage">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
            <form method="post" action="/panel/sort" style="margin-top: 10px">
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="sequence">Sort by</label>
                        <select name="sequence" id="sequence" class="form-control">
                            <option selected value="priceASC">price ascending</option>
                            <option value="priceDESC">price descending</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <button type="submit" class="btn btn-primary" style="margin-top: 32px">Sort</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
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
                <div style="margin-right: 10px">
                    <span style="float: right">Follow <i class="far fa-star"></i></span>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>