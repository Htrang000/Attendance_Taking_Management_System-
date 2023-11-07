<%-- 
    Document   : SelectGroupToView
    Created on : Oct 29, 2023, 3:48:30 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                font-weight: bold;
            }
            .menu button {
                color: white;
                text-decoration: none;
                margin: 0 10px;
                font-weight: bold;
                transition: color 0.3s;
                border: none;
            }

            .home-button,
            .logout-button{
                display: inline-block;
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            table {
                width: 98%;
                margin: 1px auto;
                overflow-x: auto;
                border-collapse: collapse;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }

            th {
                background-color: #6b90da;
                color: white;
                text-align: left;
                padding: 10px;
                font-weight: bold;
                border-bottom: 1px solid white;
            }


            td {
                padding: 5px;
                border-bottom: 1px solid white;
                font-size: 14px;
                border: 1px solid #ccc;
                text-align: center;
            }

            tr:nth-child(even) {
                background-color: rgb(107, 144, 218, 0.2);
            }
            .status{
                width: 20%;
            }

            a {
                display: inline-block;
                margin-right: 20px;
                margin-bottom: 20px;
                padding: 10px 20px;
                background-color: #6b90da;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            img {
                width: 130px;
                height: 150px;

            }

            .statistics {
                text-align: center;
                margin: 20px auto;
                padding: 10px;
                background-color: white;
                color: black;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
                font-weight: bold;
                font-size: 17px;
                max-width: 500px;
            }

        </style>
    </head>
    <body>
        <h1>Attendance Taking Management System</h1>
        <div class="menu">
            <% String contextPath = request.getContextPath();%>
            <a href="<%=contextPath + "/home"%>" class="home-button">Home</a> 
            <span>View Attendance Report</span>  
            <a href="<%=contextPath + "/logout"%>" class="logout-button">Logout</a>
        </div>
        <div>
            <div>SEMESTER:
                <c:forEach var="se" items="${requestScope.sems}">
                    <a style="font-size: small;" href="report?semesterId=${se.semesterId}">${se.semesterName}</a>
                </c:forEach>
            </div>
            <div>DEPARTMENT:
                <c:forEach var="d" items="${requestScope.depts}">
                    <a style="font-size: small;" href="report?semesterId=${sessionScope.semesterId}&departmentId=${d.departmentId}">${d.departmentName}</a>
                </c:forEach>
            </div>
            <div>COURSE:
                <c:forEach var="c" items="${requestScope.courses}">
                    <a style="font-size: small;" href="report?semesterId=${sessionScope.semesterId}&departmentId=${sessionScope.departmentId}&courseId=${c.courseId}">${c.courseName}</a>
                </c:forEach>
            </div>
            <c:if test="${requestScope.groups!=null and requestScope.groups.size()>0}"> 
                <div>GROUP:
                    <c:forEach var="g" items="${requestScope.groups}">
                        <a style="font-size: small;" href="report?groupId=${g.groupId}">${g.groupName}</a>
                    </c:forEach>
                </div>
            </c:if>
        </div>

        <c:if test="${requestScope.students!=null and requestScope.students.size()>0}">
            <div class="statistics">
                Course: ${requestScope.courseName} <br>
                Instructor: ${requestScope.iName} <br>
                Class Name: ${requestScope.groupName} <br>
            </div>
            <table border="1">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Student Code</th>
                        <th>Student Name</th>
                        <th>Class</th>
                        <th>Image</th>
                        <th>Percentage Attendance</th>
                            <c:forEach begin="1" end="${requestScope.saListSize}" varStatus="loop">
                            <th>Slot ${loop.index}</th>
                            </c:forEach>                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="sa" items="${requestScope.mapping}" varStatus="loop"> 
                        <tr >                       
                            <td>${loop.index + 1}</td>
                            <td>${sa.key.studentCode}</td>
                            <td>${sa.key.name}</td>
                            <td>${requestScope.groupName}</td>
                            <td><img src="${sa.key.img}" alt="student image"></td>
                            <td id="percentage" style="color: ${sa.key.percentageAttendance>20?"red" : sa.key.percentageAttendance>=15 and sa.key.percentageAttendance<=20?"orange":""};" ><b>${sa.key.percentageAttendance}%</b></td>
                                <c:forEach var="a" items="${sa.value}" >
                                <td style="color: ${a.status eq 1?"green": a.status eq 0 ?"red":""};">
                                    ${a.status eq 1 ? "present" : a.status eq 0 ? "absent" : "not yet"}
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </body>
</html>
