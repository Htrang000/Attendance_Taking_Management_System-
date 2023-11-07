<%-- 
    Document   : ViewStudentAttendanceReport
    Created on : Nov 6, 2023, 4:43:07 PM
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

            table img {
                width: 50px;
                display: block;
                margin: 0 auto;
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
            <div>COURSE:
                <c:forEach var="g" items="${requestScope.groups}">                    
                    <a style="font-size: small;" href="report?groupId=${g.groupId}">${g.course.courseName}</a>
                </c:forEach>
            </div>
            
            
<c:if test="${requestScope.saList != null}">
    <div class="statistics">
        Course: ${requestScope.courseName} <br>
        Instructor: ${requestScope.iName} <br>
        Class Name: ${requestScope.groupName} <br>
    </div>
    <table border="1">
        <thead>
            <tr>
                <c:forEach var="sa" items="${requestScope.saList}" varStatus="loop">
                    <th>Lesson ${sa.lesson.sessionNo}</th>
                    <c:if test="${loop.index % 10 == 9}">
                        </tr><tr>
                    </c:if>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="sa" items="${requestScope.saList}" varStatus="loop">
                <c:if test="${loop.index % 10 == 0}">
                    <tr>
                </c:if>
                <td>
                    <div>
                        <c:if test="${sa.status == 1}">
                            <img src="https://i.pinimg.com/564x/f1/1b/03/f11b0327d757c48a52dd915238e78758.jpg" alt=""> <br>
                        </c:if>
                        <c:if test="${sa.status == 0}">
                            <img src="https://i.pinimg.com/736x/e3/99/8e/e3998e881e5ab00c873e7c2491e6c607.jpg" alt=""> <br>
                        </c:if>
                        <c:if test="${sa.status == 2}">
                            <img src="https://i.pinimg.com/564x/3d/55/b4/3d55b490ecaed8df5631eed17a5e7abe.jpg" alt=""> <br>
                        </c:if>
                        Date: ${sa.lesson.date} <br>
                        Slot: ${sa.lesson.slot.slotId} <br>
                        Lecturer: ${sa.lesson.instructor.instructorCode}  <br>
                    </div>
                </td>
                <c:if test="${loop.index % 10 == 9 || loop.last}">
                    </tr>
                </c:if>
            </c:forEach>
        </tbody>
    </table>
</c:if>



    </body>
</html>
