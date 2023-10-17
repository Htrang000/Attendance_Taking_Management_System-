<%-- 
    Document   : home
    Created on : Oct 13, 2023, 10:31:21 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <style>
            img {
                display: block;
                margin: 0 auto;
                width: 20%;
            }

            h1,
            .footer {
                background-color: #f27024;
                color: white;
                text-align: center;
                border-radius: 10px;
                padding: 10px;
                margin-top: 20px;
            }

            button {
                width: 400px;
                margin: 30px 100px;
                font-weight: bold;
                display: inline-block;
                padding: 20px 30px;
                background-color: white;
                color: #6b90da;
                text-decoration: none;
                border-radius: 5px;
                border: none;
                font-size: 18px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }

            /* CSS cho phần footer */
            .footer {
                background-color: rgb(242, 112, 36);
                /* Màu nền của footer */
                color: white;
                /* Màu chữ trong footer */
                text-align: center;
                /* Căn giữa nội dung trong footer */
                padding: 20px;
                /* Khoảng cách giữa nội dung và khung của footer */
                margin-top: 40px;
            }

            .material-symbols-outlined {
                font-family: 'Material Symbols Outlined', sans-serif;
                font-size: 30px;
                /* Kích thước của biểu tượng */
                position: fixed;
                top: 40px;
                color: #f27024;
                /* Khoảng cách từ trên xuống */
                right: 100px;
                /* Khoảng cách từ phải sang */
            }
            #logout{
                margin-left: -35px;
                font-size: 14px;

            }

            #logout {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 30px;
            }


        </style>
    </head>

    <body>
        <img src="https://cdn.haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png" alt="fpt university">
        <div class="logout">
            <% String contextPath = request.getContextPath();%>
            <span class="material-symbols-outlined">
                logout <span id="logout"> <a href="<%=contextPath+"/logout"%>" style="color: #f27024;">log out</a></span>
            </span>
        </div>
        <h1> Attendance Taking Management System

        </h1>

        <div class="option">
            <button type="button" value="1" class="option1-button"><a href="home?action=schedule">Weekly timetable</a></button>
            <button type="button" class="option2-button"><a href="home?action=report">Attendance Report</a></button> <br>
            <button type="button" class="option3-button"><a href="home?action=viewInfor">Student Information</a></button>
            <button type="button" class="option4-button"><a href="home?action=help">Help</a></button> <br>
        </div>

        <div class="footer">
            <b>Email: dichvusinhvien@fe.edu.vn</b> <br>
            <b>Phone: (024)7308.13.13</b> <br>
            <b>Powered by: FPT university</b>
        </div>

    </body>
</html>
