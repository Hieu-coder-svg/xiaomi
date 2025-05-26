<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Category</title>

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
            <h2>Edit Category</h2>

            <!-- Product Form -->
            <form action="editcategory" method="post">
                <div class="form-group">
                    <label>CategoryID:</label>
                    <input type="text" value="${requestScope.category.getCategoryID()}" class="form-control"  name="cid" readonly>
                </div>

                <div class="form-group">
                    <label>Category Name:</label>
                    <input type="text" value="${requestScope.category.getCategoryName()}" class="form-control" name="cname" required>
                </div>

                <div class="form-group">
                    <label>Description:</label>
                    <input type="text" value="${requestScope.category.getDescription()}" class="form-control"  name="cdes" required>
                </div>

                <button type="submit" class="btn btn-primary"">Save</button>

            </form>
        </div>


        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
