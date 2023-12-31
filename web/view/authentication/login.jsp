<%-- 
    Document   : Login
    Created on : Oct 2, 2023, 12:26:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>


        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            }

            .login {
                width: 60%;
                margin: 0 auto;
                background-color: #fff;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }

            img {
                display: block;
                margin: 0 auto;
                width: 60%;
            }

            h1 {
                background-color: #f27024;
                color: white;
                text-align: center;
                border-radius: 10px;
                padding: 10px;
                margin-top: 20px;
            }

            form {
                border: 1px solid #ccc;
                border-radius: 10px;
                padding: 20px;
                margin-top: 20px;
            }

            table {
                width: 100%;
            }

            td.col1 {
                width: 30%;
                text-align: right;
                padding-right: 10px;
            }

            select,
            #Email,
            #Password {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            select:focus,
            #Email:focus,
            #Password:focus {
                border: 1px solid #f27024;
                box-shadow: 0 0 5px rgba(242, 112, 36, 0.6);
            }

            .form-check {
                display: flex;
                align-items: center;
                justify-content: center;
                /* Căn giữa theo chiều ngang */
                margin-bottom: 10px;
            }

            .check-label {
                margin-left: 10px;
            }

            input[type="checkbox"] {
                margin-right: 10px;
            }

            button {
                background-color: #f27024;
                width: 50%;
                color: white;
                border-radius: 10px;
                padding: 8px;
                border: none;
                margin: 15px auto;
                display: block;
                cursor: pointer;
            }

            button:hover {
                background-color: #ff8c42;
            }
        </style>
    </head>




    <body>
        <div class="login">
            <img src="https://cdn.haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png" alt="fpt university">
            <h1> Attendance Taking Management System </h1>
            <form action="login" method="post">
                <div class="form-wrap">
                    <div id="error" style="display: none;">${requestScope.error}</div>
                    <table>
                        <tr>
                            <td class="col1"><label for="Campus"><b>Select campus</b></label></td>
                            <td><select name="campus" required="">
                                    <c:forEach var="c" items="${requestScope.campuses}">
                                        <option value="${c.campusId}">${c.campusName}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <td class="col1"><label for="Email"><b>Email address</b></label></td>
                            <td><input type="email" id="Email" name="email" placeholder="Enter email" required=""></td>
                        </tr>
                        <tr>
                            <td class="col1"><label for="Password"><b>Password</b></label></td>
                            <td><input type="password" id="Password"name="password" placeholder="Enter Password" required=""></td>
                        </tr>
                    </table>
                    <div class="form-check">
                        <input type="checkbox" id="Check" name="remember">
                        <label for="Check"><b>Remember me</b></label>
                    </div>
                    <div class="form-submit">
                        <button type="submit"><b>Login</b></button> 
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script src="js/authentication/login.js">       
    </script>
        
</html>
