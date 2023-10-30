<%-- 
    Document   : editAttendance
    Created on : Oct 26, 2023, 2:05:06 PM
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


            .menu {
                background-color: #f37022;
                padding: 10px;
                color: white;
                text-align: center;
                border-radius: 10px;
                margin-bottom: 20px;
            }

            /* CSS cho các liên kết bên trong thẻ "menu" */
            .menu {
                background-color: #f37022;
                padding: 10px;
                color: white;
                text-align: center;
                border-radius: 10px;
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

            .initial-time {
                font-weight: bold;
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
                width: 10%;
            }
        </style>      
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
        <input type="hidden" value="${requestScope.groupId}" name="groupId">
        <table>
            <thead>
                <tr>
                    <th>Index</th>
                    <th>Class</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>Instructor</th>
                    <th>Time</th>
                    <th>Comment</th>
                </tr>
            </thead>
            <tbody>
            <input type="hidden" name="lessonId" value="${requestScope.lessonId}">
            <c:forEach var="s" items="${requestScope.sas}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                <input type="hidden" value="${loop.index}" readonly name="indexs">
                <input type="hidden" value="${s.student.studentId}" readonly name="studentid${loop.index}">
                <td>${groupName}</td>
                <td>${s.student.studentCode}</td>
                <td>${s.student.name}</td>
                <td><img src="${s.student.img}" alt=""></td>
                <td class="status" style="color: ${s.status eq 1?"green":"red" }">  ${s.status eq 1?"Present":"Absent" }</td>
                <td>${instructorCode}</td>
                <td>${s.recordTime} ${s.recordDate}</td>
                <td>
                    <textarea name="comment${loop.index}" cols="30" rows="4" readonly>${s.comment}</textarea>
                </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <div class="menu">
            <span style="margin-right: 15px;"><button type="button" class="save-button" onclick="save()">Save</button></span>
            <span><button type="button" class="save-button" onclick="edit(${requestScope.groupId}, ${requestScope.lessonId})">Edit</button></span>
        </div>
    <script src="../js/editAttendance/editAttendance.js"></script>
</body>
</html>
