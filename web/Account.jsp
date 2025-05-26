<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/detail.css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    </head>
    <link rel="stylesheet" href="css/style.css">
    <!--begin of menu-->
    <style>
        .navbar-brand {
            font-size: 2rem; /* Adjust size as needed */
        }
        .nav-link {
            font-size: 1.2rem; /* Adjust size as needed */
        }
        .form-control {
            font-size: 1rem; /* Adjust size as needed */
        }
        .btn {
            font-size: 1rem; /* Adjust size as needed for all buttons */
        }
        .badge {
            font-size: 0.85rem; /* Adjust size as needed */
        }
    </style>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark" style="background-color: red !important;">
        <div class="container">
            <a class="navbar-brand" href="home">Mi Smart Store</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                <ul class="navbar-nav m-auto">
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

                <form action="adminaccount" method="post" class="form-inline my-2 my-lg-0">
                    <div class="input-group input-group-sm">
                        <input name="usersearch" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                        <div class="input-group-append">
                            <button type="submit" value="Search" class="btn btn-secondary btn-number">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <a class="btn btn-success btn-sm ml-3" href="Cart.jsp">
                        <i class="fa fa-shopping-cart"></i> Cart
                        <span class="badge badge-light">${sessionScope.size}</span>
                    </a>
                </form>
            </div>
        </div>
    </nav>
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Mi Smart Store</h1>
            <p class="lead text-muted mb-0">Xiaomi – Your Gateway to Smart Living</p>
        </div>
    </section>

    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manager <b>Account</b></h2>
                        </div>
                        <c:if test="${sessionScope.account.getRole()==2}">
                            <div class="col-sm-6">
                                <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Admin Account</span></a>

                            </div>
                        </c:if>
                    </div>
                </div>

                <table class="table table-striped table-hover">
                    <thead>
                        <tr>

                            <th>UserID</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <h2 style="color: red">${requestScope.error}</h2>
                    <h2 style="color: red">${requestScope.errorpass}</h2>
                    <h2 style="color: greenyellow">${requestScope.success}</h2>
                    <h2 style="color: red">${requestScope.result}</h2>
                    <h2 style="color: greenyellow">${requestScope.update}</h2>
                    <h1 style="color: greenyellow">${requestScope.annouce}</h1>
                    <c:forEach items="${requestScope.list1}" var="u">
                        <tr>

                            <td>${u.getUserID()}</td>
                            <td>${u.getUsername()}</td>
                            <td>******</td>
                            <td>${u.getRole()}</td>
                            <c:if test="${sessionScope.account.getRole()==2}">
                                <td>
                                    <a href="editaccount?edit=${u.getUserID()}"  class="edit" ><i class="material-icons" title="Edit">&#xE254;</i></a>
                                    <a href="deleteaccount?delete=${u.getUserID()}" class="delete" data-toggle="modal" onclick="confirmDeleteAccount(${u.getUserID()})">
                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <div class="clearfix">

                    <div class="hint-text">Showing <b>${i}</b> out of <b>${requestScope.endpage}</b> entries</div>

                    <ul class="pagination">
                        <c:forEach begin="1" end="${requestScope.endpage}" var="i">
                            <li class="page-item"><a href="adminaccount?index=${i}" class="page-link">${i}</a></li>
                            </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="deleteaccount" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add User</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>User Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input name="password" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>RePassword</label>
                                <input name="repassword" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="role">Choose a role:</label>
                                <select name="role" id="role">
                                    <option value="0" readonly>Admin</option>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="editEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Employee</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <textarea class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="text" class="form-control" required>
                            </div>					
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Delete Modal HTML -->
        <div id="deleteProductModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <p>Are you sure you want to delete these Records?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/manager.js" type="text/javascript"></script>
        <script>
                                        function confirmDeleteAccount(userId) {
                                            var confirmation = confirm("Do you want to delete the user account?");

                                            if (confirmation) {
                                                // If the user confirms, navigate to the delete URL
                                                window.location.href = "deleteaccount?delete=" + userId;
                                            } else {
                                                // If the user cancels, do nothing or provide feedback
                                                console.log("Account deletion canceled");
                                            }
                                        }
        </script>
    </body>
</html>