<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your Page Title</title>
        <!-- Add Bootstrap CSS link here -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #fff; /* Màu nền trắng */
                color: #007BFF; /* Màu chữ xanh nước biển */
                padding-top: 0px;
            }
            .card {
                margin-bottom: 10px; /* Giảm khoảng cách giữa các card */
                border: 1px solid #dee2e6; /* Đường viền card */
                border-radius: 8px; /* Góc bo của card */
            }

            .breadcrumb {
                background: #007BFF;
                color: #fff; /* Màu chữ trắng */
                border-radius: 5px; /* Góc bo của breadcrumb */
            }

            .breadcrumb-item + .breadcrumb-item::before {
                content: '>';
                color: #fff; /* Màu chữ trắng */
            }

            .show_txt {
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }

            .card-img-top {
                width: 100%;
                height: 25vw; /* Giảm kích thước ảnh */
                object-fit: cover;
                border-top-left-radius: 8px; /* Góc bo của ảnh */
                border-top-right-radius: 8px; /* Góc bo của ảnh */
            }

            .card-body {
                padding: 15px;
            }

            @media (max-width: 768px) {
                .card-img-top {
                    height: 20vw; /* Giảm kích thước ảnh trên thiết bị nhỏ */
                }

                .col-md-3 {
                    max-width: 100%; /* Chiều rộng 100% trên thiết bị nhỏ */
                    margin-bottom: 10px; /* Khoảng cách giữa sidebar và sản phẩm */
                }

                .card {
                    margin-bottom: 10px; /* Giảm khoảng cách giữa các card trên thiết bị nhỏ */
                }
            }

            /* Thêm lớp để định dạng màu xanh cho card-header "Last product" */
            .card-header-last-product {
                background-color: #007BFF; /* Màu xanh giống các phần khác */
                color: #fff; /* Màu chữ trắng */
            }
            .clearfix {
                display: flex;
                justify-content: flex-end;
                align-items: center;
                margin-top: 10px; /* Adjust as needed for spacing */
            }

            .hint-text {
                margin-right: 10px; /* Add margin for spacing between text and pagination */
            }
        </style>
    </head>
    <body>
        <h1 style="color: greenyellow">${requestScope.success}</h1>

        <jsp:include page="Menu.jsp"></jsp:include>
        <h3 style="color: #007bff">${requestScope.notfound}</h3>

        <div class="container-fluid">
            <div class="row">
                <!-- Left Sidebar -->
                <div class="col-md-3">
                    <!-- Categories Card -->
                    <div class="card bg-light">
                        <div class="card-header bg-info text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">

                            <c:forEach items="${requestScope.categories}" var="o">
                                <li class="list-group-item text-white"><a href="categories?cid=${o.getCategoryID()}">${o.getCategoryName()}</a></li>
                                </c:forEach>
                        </ul>
                    </div>

                    <div class="card bg-light mb-3">
                        <div class="card-header card-header-last-product text-uppercase">Best Seller Product</div>
                        <div class="card-body">
                            <img class="img-fluid" src="${requestScope.last.getImageURL()}" alt="Last Product image">
                            <h5 class="card-title">${requestScope.last.getProductName()}</h5>
                            <p class="card-text">${requestScope.last.getDescription()}</p>
                            <p class="bloc_left_price">${requestScope.last.getPrice()} $</p>
                        </div>
                    </div>

                    <!-- Price Filter Form -->
                    <div class="card bg-light">
                        <div class="card-header bg-info text-white text-uppercase"><i class="fa fa-filter"></i> Price Filter</div>
                        <div class="card-body">
                            <div class="form-group">
                                <form action="control1" method="post">
                                    <div class="form-group">
                                        <label for="minPrice">Min Price:</label>
                                        <input type="number" class="form-control" id="minPrice" name="minprice" placeholder="Enter min price">
                                    </div>
                                    <div class="form-group">
                                        <label for="maxPrice">Max Price:</label>
                                        <input type="number" class="form-control" id="maxPrice" name="maxprice" placeholder="Enter max price">
                                    </div>
                                    <button type="submit" class="fa fa-filter">Apply Filter</button>
                                </form>
                            </div>

                            <div class="form-group">
                                <label>Filter By Price:</label>
                                <div class="btn-group" role="group">
                                    <a href="control1" class="btn btn-info">Low To High Price</a>
                                    <a href="control2" class="btn btn-info">High To Low Price</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- Main Content -->
                <div class="col-md-9">
                    <!-- Product Cards -->
                    <div class="row">
                        <c:forEach items="${requestScope.products}" var="o">
                            <div class="col-md-4">
                                <div class="card">
                                    <img class="card-img-top" src="${o.getImageURL()}" alt="Product image">
                                    <div class="card-body">
                                        <h5 class="card-title show_txt"><a title="View Product" href="detail?pname=${o.getProductName()}">${o.getProductName()}</a></h5>
                                        <p class="card-text show_txt">${o.getDescription()}</p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <span class="text-danger font-weight-bold">${o.getPrice()} $</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearfix">
            <ul class="pagination">
                <c:forEach begin="1" end="${requestScope.endpage}" var="i">
                    <li class="page-item"><a href="home?index=${i}" class="page-link">${i}</a></li>
                    </c:forEach>
                    <c:forEach begin="1" end="${requestScope.endpage1}" var="o">
                    <li class="page-item"><a href="controlcid?index=${o}" class="page-link">${o}</a></li>
                    </c:forEach>
            </ul>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>

        <!-- Add Bootstrap JS and Popper.js scripts here -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>