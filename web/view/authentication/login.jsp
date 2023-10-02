<%-- 
    Document   : Login
    Created on : Oct 2, 2023, 12:26:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .login{
                width: 70%;
                margin-left: 50%;
                transform: translateX(-50%);

            }
            img{
                margin-top: 1.5%;
                width: 60%;
                margin-left: 50%;
                transform: translateX(-50%);
            }
            h1 {
                background-color: #f27024;
                color: white;
                text-align: center;
                border-radius: 50px;
                padding: 1%;
            }

            form {
                border-style: ridge;
                border-radius: 10px;

            }

            .form-wrap {
                padding-top: 15px;
                width: fit-content;
                margin-left: 50%;
                transform: translateX(-50%);
            }


            select,
            #Email,
            #Password {
                margin-bottom: 5px;
                padding: 5px;
            }

            .col1{
                padding-right: 15px;
            }

            button {
                background-color: #f27024;
                width: 50%;
                color: white;
                border-radius: 50px;
                padding: 8px;
                border: none;
                margin: 15px 30%;
            }
        </style>
    </head>

    <body>
        <div class="login">
            <img src="https://cdn.haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png" alt="fpt university">
            <h1> Attendance Taking Management System  </h1>
            <form>
                <div class="form-wrap">
                    <table>
                        <tr>
                            <td class="col1"><label for="Campus"><b>Select campus</b></label></td>
                            <td><select>
                                    <option value="">FU_Hoa Lac</option>
                                    <option value="">FU_Ho Chi Minh</option>
                                    <option value="">FU_Da Nang</option>
                                    <option value="">FU_Can Tho</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td class="col1"><label for="Email"><b>Email address</b></label></td>
                            <td><input type="email" id="Email" placeholder="Enter email"></td>
                        </tr>
                        <tr>
                            <td class="col1"><label for="Password"><b>Password</b></label></td>
                            <td><input type="password" id="Password" placeholder="Enter Password"></td>
                        </tr>
                    </table>
                    <div class="form-check">
                        <input type="checkbox" id="Check">
                        <label for="Check"><b>Check me out</b></label>
                    </div>
                    <div class="form-submit">
                        <button type="submit"><b>Login</b></button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
