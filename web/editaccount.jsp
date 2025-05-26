
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Account</title>

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
            <h2>Edit Account's Information</h2>

            <!-- Account Form -->
            <form id="productForm" action="editaccount" method="post">
                <div class="form-group">
                    <label for="productID"> UserID:</label>
                    <input type="text" value="${requestScope.user.getUserID()}" class="form-control" id="productID" name="userid" readonly>
                </div>

                <div class="form-group">
                    <label for="productName">User Name:</label>
                    <input type="text" value="${requestScope.user.getUsername()}" class="form-control" id="productName" name="username" readonly>
                </div>
                
                <div class="form-group">
                    <label for="productName">Password:</label>
                    <input type="text" value="${requestScope.user.getPassword()}" class="form-control" id="productName" name="password" readonly>
                </div>

                <div class="form-group">
                    <label for="role">Choose a role:</label>
                    <select name="role" id="role">
                        <option value="1">User</option>
                        <option value="0">Admin</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary"">Save</button>
            </form>
        </div>

        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>