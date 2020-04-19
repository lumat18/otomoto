<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/home"><span>OTO</span>MOTO</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <c:if test="${sessionScope.user!=null}">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/panel/add">Add car</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/panel/myadverts">My Adverts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/panel/search">Search</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/panel/followed">Followed</a>
                </li>
                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="/panel/users">Users</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="float-right">
            <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
                <span style="color: #ffffff; margin-right: 20px">Signed as: ${user.name}</span>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
            </form>
        </div>
    </c:if>
    <c:if test="${sessionScope.user==null}">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/register">Register</a>
                </li>
            </ul>
        </div>
        <div class="navbar-header" style="float: right">
            <div class="navbar-header">
                <a class="navbar-brand" href="/login">Login</a>
            </div>
        </div>
    </c:if>
</nav>
<div style="height: 100px"></div>

