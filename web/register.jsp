<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>  
<html lang="en" >  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Register | Xiaomi Account</title>  
        <link rel="icon" type="image/x-icon" href="icon/favicon.png">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">  
    </head>  
    <style>
        body {
            background: #ecf0f1;
            font-family: 'Muli', sans-serif;
        }
        h1 {
            color: #fff;
            padding-bottom: 2rem;
            font-weight: bold;
        }
        a {
            color: #333;
        }
        a:hover {
            color: #1428A0;
            text-decoration: none;
        }
        .form-control:focus {
            color: #000;
            background-color: #fff;
            border: 2px solid #1428A0;
            outline: 0;
            box-shadow: none;
        }
        .btn {
            background: darkseagreen;
            border: #1428A0;
        }
        .btn:hover {
            background: #1428A0;
            border: #1428A0;
        }
        .text-center{
            color: darkgreen;
        }
    </style>  
    <body>  
        <div class="pt-5">  
            <h1 class="text-center">Register a Samsung account</h1>  
            <div class="container">  
                <div class="row">  
                    <div class="col-md-5 mx-auto">  
                        <div class="card card-body">  
                            <form id="submitForm" action="signup" method="post" >  
                                <input type="hidden" name="_csrf" value="7635eb83-1f95-4b32-8788-abec2724a9a4">  
                                <div class="form-group required">  
                                    <lSabel for="username"> Enter your Username </lSabel>  
                                    <input type="text" class="form-control text-lowercase" id="username" required="" name="user" value="">  
                                </div>                      
                                <div class="form-group required">  
                                    <lSabel for="password"> Enter your Password </lSabel>  
                                    <input type="password" class="form-control" id="password" required="" name="pass" value="">  
                                </div>  
                                <div class="form-group required">  
                                    <lSabel for="password"> Confirm Password </lSabel>  
                                    <input type="password" class="form-control" id="repassword" required="" name="repass" value="">  
                                </div>
                                <div class="form-group pt-1">  
                                    <input class="btn btn-primary btn-block" type ="submit" name ="submitregister" value = "Submit">
                                </div>  
                            </form>  
                            <lSabel style="color: red">${requestScope.error}</lSabel>
                            <p class="small-xl pt-3 text-center">  
                                <span class="text-muted"> Have an account? </span>  
                                <a href="Login.jsp"> Log in </a>  
                            </p>  
                        </div>  
                    </div>  
                </div>  
            </div>  
        </div> 
        <%
            if(request.getAttribute("in")!=null){
            String er = (String)request.getAttribute("in");
            
        %>
        <div class="error-message">
            <h3><%= er %></h3>
        </div>
        <%
            }
        %>
        <%
        if(request.getAttribute("duplicate")!=null){
        String er = (String)request.getAttribute("duplicate");
            
        %>
        <div class="error-message">
            <h3><%= er %></h3>
        </div>
        <%
            }
        %>                  
    </body>  
    <style>
        .error-message {
            background-color: #ffcccc;
            border: 1px solid #ff0000;
            padding: 10px;
            margin-bottom: 10px;
            font-size: 5px; /* Độ lớn của chữ trong error message */
            text-align: center;
            border-radius: 8px; /* Góc bo của error message */
            width: 24%; /* Độ rộng của error message */
            margin-top: 10px; /* Khoảng cách từ error message đến form */
            margin-left: 38%;
        }
    </style>
</html>  
