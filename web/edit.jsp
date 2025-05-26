<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Product</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!-- Custom CSS -->
        <style>
            body {
                background-image: url('background.jpg'); /* Add the path to your background image */
                background-size: cover;
                color: #ffffff; /* Set text color to white */
                padding: 20px;
            }

            .container {
                background-color: #666; /* Semi-transparent black background for the form container */
                padding: 20px;
                border-radius: 10px;
            }

            label {
                color: #ffffff; /* Set label text color to white */
            }

            button {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>Edit Xiaomi Product</h2>

            <!-- Product Form -->
            <form action="editproduct" method="post">
                <div class="form-group">
                    <label for="productID">Product ID:</label>
                    <input type="text" value="${requestScope.dataproduct.getProductID()}" class="form-control"  name="id" readonly>
                </div>

                <div class="form-group">
                    <label for="productName">Product Name:</label>
                    <input type="text" value="${requestScope.dataproduct.getProductName()}" class="form-control" name="name" >
                </div>

                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" value="${requestScope.dataproduct.getPrice()}" class="form-control" id="price" name="price" required>
                </div>

                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="number" value="${requestScope.dataproduct.getQuantity()}" class="form-control" id="quantity" name="quantity" required>
                </div>

                <div class="form-group">
                    <label for="description">Description:</label>
                    <input type="text" value="${requestScope.dataproduct.getDescription()}" class="form-control" id="description" name="description" required>
                </div>

                <div class="form-group">
                    <label for="imageURL">Image URL:</label>
                    <input type="text" class="form-control" id="imageURL" name="image" value="${requestScope.dataproduct.getImageURL()}" required                </div>
                </div>

                <div class="form-group">
                    <label>Category</label>
                    <select name="category" class="form-control" aria-label="Default select example">
                        <c:forEach items="${requestScope.categories}" var="o">
                            <option value="${o.getCategoryID()}">${o.getCategoryName()}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary"">Save</button>
        </div>
    </form>
</div>


<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
