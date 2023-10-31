<%-- 
    Document   : takeAttendance.jsp
    Created on : Oct 22, 2023, 9:19:33 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            * {
                margin: 10px;
            }

            body {
                font-family: Arial, sans-serif;
            }

            h1 {
                background-color: white;
                color: #f37022;
                padding: 5px;
                text-align: center;
            }

            th,
            td {
                border: 1px solid #dddddd;
            }

            /* CSS cho các liên kết bên trong thẻ "menu" */
            .menu {
                background-color: #f37022;
                padding: 10px;
                color: white;
                text-align: center;
                border-radius: 10px;
                margin-bottom: 20px;
                font-weight: bold;
            }
            /* CSS cho các liên kết bên trong thẻ "menu" */
            .menu button {
                color: white;
                text-decoration: none;
                margin: 0 10px;
                /* Tạo khoảng cách giữa các liên kết */
                font-weight: bold;
                transition: color 0.3s;
                border: none;
                /* Hiệu ứng chuyển đổi màu chữ khi di chuột qua */
            }

            .home-button,
            .logout-button, .save-button{
                display: inline-block;
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }


            img {
                width: 130px;
                height: 150px;

            }
            
            table {
                width: 98%;
                margin: 1px auto;
                overflow-x: auto;
                border-collapse: collapse;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }

            /* CSS cho header của bảng */
            th {
                background-color: #6b90da;
                color: white;
                text-align: left;
                padding: 10px;
                font-weight: bold;
                border-bottom: 1px solid white;
            }


            /* CSS cho các ô dữ liệu */
            td {
                padding: 5px;
                border-bottom: 1px solid white;
                font-size: 14px;
                border: 1px solid #ccc;
                text-align: center;
            }

            /* CSS cho các hàng chẵn */
            tr:nth-child(even) {
                background-color: rgb(107, 144, 218, 0.2);
            }
            .status{
                width: 20%;
            }

        </style>
    </head>
    <body>
        <h1>Attendance Taking Management System</h1>
        <div class="menu">
            <% String contextPath = request.getContextPath();%>
            <a href="<%=contextPath + "/home"%>" class="home-button">Home</a> 
            <span>Take attendance</span>  
            <a href="<%=contextPath + "/logout"%>" class="logout-button">Logout</a>

        </div>
        <form action="takeAttendance" method="post">
            <input type="hidden" value="${requestScope.groupId}" name="groupId">
            <table>
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Class</th>
                        <th>Student Code</th>
                        <th>Student Name</th>
                        <th>Image</th>
                        <th>Status Attendance</th>
                        <th>Comment</th>
                    </tr>
                </thead>
                <tbody>
                <input type="hidden" name="lessonId" value="${requestScope.lessonId}">
                <c:forEach var="s" items="${sas}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                    <input type="hidden" value="${loop.index}" readonly name="indexs">
                    <input type="hidden" value="${s.student.studentId}" readonly name="studentid${loop.index}">
                    <td>${groupName}</td>
                    <td>${s.student.studentCode}</td>
                    <td>${s.student.name}</td>
                    <td><img src="${s.student.img}" alt=""></td>
                    <td class="status">
                        <label>
                            <input type="radio" name="status${loop.index}" required value="1"
                                ${s.status eq 1?"checked":""}  
                                   > Present
                        </label>
                        <label>
                            <input type="radio" name="status${loop.index}" required value="0"
                                  ${s.status ne 1?"checked":""}   > Absent
                        </label>
                    </td>
                    <td>
                        <textarea name="comment${loop.index}" cols="30" rows="4" >${s.comment}</textarea>
                    </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <div class="menu">
                <button type="submit" class="save-button">Save</button>
            </div>
        </form>


    </body>
</html>
