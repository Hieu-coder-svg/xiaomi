<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Checkout</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/checkout.css">
        <style>
            /* Cập nhật màu sắc cho navbar */
            .navbar-brand {
                padding-left: 350px; /* Thêm padding bên trái để dịch chữ vào trong */
            }
            /* Tùy chỉnh các phần tử trong navbar */
            .navbar-nav .nav-item {
                margin-right: 10px; /* Điều chỉnh margin giữa các mục trong navbar */
            }

            /* Đặt khoảng cách giữa "Hello" và "Logout" */
            .navbar-nav .nav-item:last-child {
                margin-right: 0; /* Đặt margin của phần tử cuối cùng về 0 */
            }

            /* Đặt khoảng cách giữa "Mi store" và "Hello" */
            .navbar-brand {
                margin-right: 10px; /* Điều chỉnh margin giữa brand và mục đầu tiên của navbar-nav */
            }
            body {
                background-color: whitesmoke;
            }

            /* Đảm bảo các thiết lập khác không thay đổi */
            .container {
                background-color: whitesmoke;
                color: black;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
                padding: 20px;
                margin-top: 20px;
            }
            /* Các thiết lập cho nút, bảng, v.v... giữ nguyên */
            .btn {
                background-color: #007bff;
                color: #ffffff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
            }
            .btn:hover {
                background-color: #0056b3;
            }
            .table th, .table td {
                text-align: center;
            }
            .table img {
                max-width: 50px;
                border-radius: 5px;
            }

            .btnSub:hover, .btnAdd:hover {
                background-color: #218838;
            }
            .btn-danger {
                background-color: #dc3545;
                color: black;
                border: none;
                padding: 5px 10px;
                border-radius: 3px;
                cursor: pointer;
            }
            .btn-danger:hover {
                background-color: #c82333;
            }
            .table-container {
                max-width: 100%;
                overflow-x: auto;
                border: 2px solid #dee2e6;
                border-radius: 10px;
                margin-top: 20px;
            }
            .table {
                width: 100%;
            }
            .checkout-summary {
                margin-top: 20px;
            }
            .checkout-summary .rounded-pill {
                background-color: #f8f9fa;
                border: 2px solid #007bff;
                color: #007bff;
                padding: 10px;
                font-weight: bold;
                margin-bottom: 20px;
            }
            .checkout-summary ul {
                list-style: none;
                padding: 0;
            }
            .checkout-summary li {
                display: flex;
                justify-content: space-between;
                padding: 10px 0;
                border-bottom: 1px solid #dee2e6;
            }
            .checkout-summary h5 {
                font-weight: bold;
                color: #007bff;
            }
            .checkout-summary .btn-dark {
                background-color: #007bff;
                color: #ffffff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
                display: inline-block;
            }
            .checkout-summary .btn-dark:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <c:set var="o" value="${sessionScope.cart}"/>
        <c:set var="t" value="0"/>
        <c:set var="sum" value="0"/>
        <c:set var="n" value="${sessionScope.account}"/>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">

            <a class="navbar-brand" href="home">Mi Smart Store</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                <ul class="navbar-nav m-auto">
                    <c:if test="${sessionScope.account.getRole()==2}">
                        <li class="nav-item">
                            <a class="nav-link" href="adminaccount">Manager Account</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="manager">Manager Products</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="crudcategory">Manager Categories</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.account.getRole()==0}">
                        <li class="nav-item">
                            <a class="nav-link" href="adminaccount">View Account</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="manager">Manager Products</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="crudcategory">Manager Categories</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.account != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="account?name=${sessionScope.account.getUsername()}">Hello ${sessionScope.account.getUsername()}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="login?lg=2">Logout</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.account == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="login?lg=1">Login</a>
                        </li>
                    </c:if>
                </ul>

            </div>

        </nav>
        <div class="container">
            <div class="row">
                <!-- Bảng Order sẽ chiếm 7/12 chiều rộng của container -->
                <div class="col-lg-7">
                    <div class="table-responsive table-container">
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
                                </tr>
                            </thead>
                            <tbody>
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
                                            <strong>${i.quantity}</strong>
                                        </td>
                                        <td class="align-middle"><strong><fmt:formatNumber pattern="##.##" value="${i.price*i.quantity}"/>$</strong></td>
                                        <c:set var="sum" value="${sum + i.price*i.quantity}"/>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-lg-6 checkout-summary">
                        <div class="rounded-pill px-4 py-3 text-uppercase font-weight-bold">Total Money</div>
                        <div class="p-4">
                            <ul class="list-unstyled mb-4">
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total Money</strong><strong><fmt:formatNumber pattern="##.##" value="${sum}"/>$</strong></li>
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Fee Ship</strong><strong>Free ship</strong></li>
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>8%</strong></li>
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total Checkout Money</strong>
                                    <h5 class="font-weight-bold"><fmt:formatNumber pattern="##.##" value="${sum + sum*0.08}"/> $</h5>
                                </li>
                            </ul>

                        </div>
                    </div>
                </div>

                <div class="col-lg-5">
                    <form action="buy" method="post">
                        <div class="row">
                            <div class="col-12">
                                <h3>Receiving Information </h3>
                            </div>
                            <div class="col-md-6">
                                <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                                <input type="text" id="fname" name="name" value="${n.getUsername()}" readonly>
                                <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                                <input type="text" id="adr" name="address" placeholder="Thanh Ba - Phu Tho" required>
                                <label for="adr"><i class="fa fa-address-card-o"></i>${n.getUserID()} </label>
                                <input type="hidden" id="adr" name="cid" value="${n.getUserID()}">
                            </div>
                            <div class="col-md-6">
                                <label for="zip">Phone</label>
                                <input type="text" id="zip" name="phone" placeholder="0944362986" required>
                                <label for="zip">Total</label>
                                <input type="text" id="zip" name="total" value="${sum+sum*0.08}" readonly>
                            </div>
                        </div>

                        <label>
                            <input type="checkbox" checked="checked" name="sameadr" readonly> Payment on delivery
                        </label>
                        <input type="submit" value="Buy" class="btn">
                    </form>
                </div>


            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>