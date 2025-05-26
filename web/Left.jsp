<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${requestScope.categories}" var="o">
                <li class="list-group-item text-white"><a href="categories?cid=${o.getCategoryID()}">${o.getCategoryName()}</a></li>
                </c:forEach>

        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Best seller product</div>
        <div class="card-body">

            <img class="img-fluid" src="${requestScope.last.getImageURL()}" />
            <h5 class="card-title">${requestScope.last.getProductName()}</h5>
            <p class="card-text">${requestScope.last.getDescription()}</p>
            <p class="bloc_left_price">${requestScope.last.getPrice()} $</p>

        </div>
    </div>
</div>