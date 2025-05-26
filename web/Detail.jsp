<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>
                    <div class="col-sm-9">
                        <div class="container">
                            <div class="card">
                                <div class="row">
                                    <form action="cart" method="post">
                                        <aside class="col-sm-5 border-right">
                                            <article class="gallery-wrap"> 
                                                <div class="img-big-wrap">
                                                    <div> <a href="#" ><img src="${requestScope.detail.getImageURL()}"></a></div>
                                            </div> <!-- slider-product.// -->
                                            <div class="img-small-wrap">
                                            </div> <!-- slider-nav.// -->
                                        </article> <!-- gallery-wrap .end// -->
                                    </aside>
                                    <aside class="col-sm-7">

                                        <article class="card-body p-5">
                                            <h3 class="title mb-3">${requestScope.detail.getProductName()}</h3>

                                            <p class="price-detail-wrap"> 
                                                <span class="price h3 text-warning"> 
                                                    <span class="currency">US $</span><span class="num">${requestScope.detail.getPrice()}</span>
                                                </span> 
                                            </p> <!-- price-detail-wrap .// -->
                                            <dl class="item-property">
                                                <dt>Description</dt>
                                                <dd><p>
                                                        ${requestScope.detail.getDescription()}
                                                    </p></dd>
                                            </dl>
                                            <input type="hidden" name="id" value="${requestScope.detail.getProductID()}">
                                            <input type="hidden" name="image" value="${requestScope.detail.getImageURL()}">
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <dl class="param param-inline">

                                                        <dd>

                                                            The maximum number of products in stock is: ${requestScope.detail.getQuantity()}
                                                            Quantity: <input type="number" name="quantity" value="quantity" max="${requestScope.detail.getQuantity()}" min="1">

                                                        </dd>
                                                    </dl>  <!-- item-property .// -->
                                                </div> <!-- col.// -->

                                            </div> <!-- row.// -->
                                            <hr>
                                            <input type="submit" name="buy" value="BUY">
                                            <input type="submit" name="cart" value="Add To Cart">
                                        </article>

                                        </article> <!-- card-body.// -->

                                    </aside> <!-- col.// -->
                                </form>
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->


                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>                                
    </body>
</html>
