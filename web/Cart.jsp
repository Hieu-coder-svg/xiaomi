
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>

    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Number Order</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Product</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Price</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Quantity</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Total Money</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Delete</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:set var="o" value="${sessionScope.cart}"/>
                                            <c:set var="t" value="0"/>
                                            <c:set var="sum" value="0"/>
                                            <c:forEach items="${o.items}" var="i">
                                                <c:set var="t" value="${t+1}"/>  

                                                <tr>
                                                    <td class="align-middle"><strong>${t}</strong></td>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="${i.product.getImageURL()}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${i.product.getProductName()}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong><fmt:formatNumber pattern="##.##" value="${i.price}"/>$</strong></td>
                                                    <td class="align-middle">
                                                        <a href="deletecart?number=-1&id=${i.product.getProductID()}"><button class="btnSub">-</button></a> 
                                                        <strong>${i.quantity}</strong>
                                                        <a href="deletecart?number=1&id=${i.product.getProductID()}"><button class="btnAdd">+</button></a>
                                                    </td>
                                                    <td  class="align-middle"><strong><fmt:formatNumber pattern="##.##" value="${i.price*i.quantity}"/>$</strong></td>
                                                    <c:set var="sum" value="${sum + i.price*i.quantity}"/>
                                                    <td class="align-middle">
                                                        <form action="deletecart" method="post" class="text-dark" onsubmit="return confirmDelete();" ><!-- Hiển thị thông báo trước khi gửi form -->
                                                            <input type="hidden" class="btn btn-danger" name="idcart" value="${i.product.getProductID()}">
                                                            <input type="submit" class="btn btn-danger" name="submit" value="DELETE">
                                                        </form>
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Voucher</div>
                                <div class="p-4">
                                    <div class="input-group mb-4 border rounded-pill p-2">
                                        <input type="text" placeholder="Enter Voucher" aria-describedby="button-addon3" class="form-control border-0">
                                        <div class="input-group-append border-0">
                                            <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2"></i>Apply</button>
                                        </div>
                                    </div>
                                </div>
                                <a href="home" class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Close Cart</a>
                            </div>
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Total Money</div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total Money Product</strong><strong><fmt:formatNumber pattern="##.##" value="${sum}"/>$</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Fee Ship</strong><strong>Free ship</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>8%</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total Money CheckOut</strong>
                                            <h5 class="font-weight-bold"><fmt:formatNumber pattern="##.##" value="${sum + sum*0.08}"/> $</h5>
                                        </li>
                                    </ul><a href="buy"class="btn btn-dark rounded-pill py-2 btn-block">BUY</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this item from the cart?");
        }
        </script>
    </body>
    
</html>
