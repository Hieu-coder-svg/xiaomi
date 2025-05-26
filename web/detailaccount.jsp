<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account's Information</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <style>
            .container {
                margin-top: 20px;
            }
            h2 {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <jsp:include page="Menu.jsp"></jsp:include>
        <body>
            <h4 style="color: red">${requestScope.mess}</h4>
        <h4 style="color: red">${requestScope.mess1}</h4>
        <h4 style="color: greenyellow">${requestScope.mess2}</h4>
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <form action="account" method="post">
                        <h4>User's Information</h4>
                        <div class="form-group">
                            <label for="email">Username: </label>
                            <input type="email" class="form-control" id="email" value="${sessionScope.account.getUsername()}" readonly>
                        </div>

                        <h4>View Order</h4>
                        <div class="form-group">
                            <a href="orderdetails?name=${sessionScope.account.getUsername()}">View Order</a>
                        </div>

                        

                        


                        <h4>Change Password</h4>
                        <div class="form-group">
                            <label for="newPassword">Old password</label>
                            <input type="text" name="oldpass" class="form-control" id="newPassword">
                            <input type="hidden" name="user" value="${sessionScope.account.getUsername()}">
                        </div>
                        <div class="form-group">
                            <label for="newPassword">New password:</label>
                            <input type="text" name="pass" class="form-control" id="newPassword">
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Repeat new password:</label>
                            <input type="text" name="repass" class="form-control" id="confirmPassword">
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>