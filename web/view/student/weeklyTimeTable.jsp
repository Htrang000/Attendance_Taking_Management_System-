<%-- 
    Document   : weeklyTimeTable
    Created on : Oct 13, 2023, 11:25:51 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <style>
            * {
                margin: 10px;
            }
            /* Thêm một font chữ khác */
            body {
                font-family: Arial, sans-serif;
            }

            /* CSS cho header của bảng */
            th,
            .date {
                background-color: #6b90da;
                color: white;
                text-align: left;
                padding: 10px;
                font-weight: bold;
                border-bottom: 1px solid white;
            }

            h1 {
                background-color: white;
                color: #f37022;
                padding: 5px;
                text-align: center;

            }


            table {
                width: 100%;
                margin: 1px auto;
                overflow-x: auto;
                border-collapse: collapse;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
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

            .home-button,
            .logout-button {
                display: inline-block;
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                text-decoration: none;
                /* Loại bỏ đường gạch chân mặc định cho liên kết */
                border-radius: 5px;
                transition: background-color 0.3s;
                /* Hiệu ứng chuyển đổi màu nền khi di chuột qua */
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
            .menu a {
                color: white;
                text-decoration: none;
                margin: 0 10px;
                font-weight: bold;
                transition: color 0.3s;
            }


            select#year,
            select#week {
                padding: 1px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
        </style>
    </head>

    <body>
        <h1>Attendance Taking Management System</h1>
        <div class="menu">
            <a href="home.html" class="home-button">Home</a>
            Time weekly table
            <% String contextPath = request.getContextPath();%>
            <a href="<%=contextPath + "/logout"%>" class="logout-button">Logout</a>

        </div>
        <table border="1px">
            <th rowspan="2">
                <form action="scheduleOfWeek" method="post" id="form">
                    <b>Year </b><select name="year" id="year">
                        <option value="2023">2023</option>
                        <option value="2022">2022</option>
                        <option value="2021">2021</option>
                        <option value="2020">2020</option>
                    </select> <br>
                    <b>Week </b><select name="week" id="week" onchange="submitForm(this)">Week</select>
                    <input type="hidden" id="monday" name="monday">
                </form>     
            </th>
            <th>Mon</th>
            <th>Tue</th>
            <th>Wed</th>
            <th>Thu</th>
            <th>Fri</th>
            <th>Sat</th>
            <th>Sun</th>
            <tr>
                <td class="date">
                </td>
                <td class="date">
                </td>
                <td class="date">
                </td>
                <td class="date">
                </td>
                <td class="date">
                </td>
                <td class="date">
                </td>
                <td class="date">
                </td>
            </tr>
            <c:forEach begin="1" end="6" varStatus="slot">
            <tr>
                <c:set var="current" value="${1}"/>
                <c:set var="follow" value="${1}"/>
                <td>Slot ${slot.index}</td>
                <c:forEach items="${requestScope.listLesson}" var="l">                    
                    <c:if test="${l.slot.slotId eq slot.index}">
                        <c:forEach begin= "${current}" end="7" varStatus="day">
                            <c:if test="${l.date.getDay() eq day.index}">

                                <td>
                                    <div>
                                        ${l.group.course.courseName} - ${l.group.groupName}<br>
                                        at ${l.room.roomName} <br>
                                        <span style="color: ${l.attendanceStatus eq 1 ? 'green' : 'red'};">
                                            ${l.attendanceStatus eq 1 ? 'Present' : (l.attendanceStatus eq 2 ? 'Absent' : 'Not given')}
                                        </span> <br>
                                        (${l.slot.startTime} - ${l.slot.endTime})
                                    </div>
                                </td>
                                <c:set var="current" value="${day.index+1}"/>
                                <c:set var="follow" value="${day.index+1}"/> 
                            </c:if>
                            <c:if test="${l.date.getDay() > day.index}">
                                <td>
                                </td>
                            </c:if>
                        </c:forEach> 
                    </c:if>
                </c:forEach>
                <c:forEach begin="${follow}" end="7">
                    <td></td>
                </c:forEach>

            </tr>
            </c:forEach>
            <!--            <tr>
                            <td><b>Slot5</b>
                            </td>
                            <td>
                                <div>
                                    PRJ301 - SE1763<br>
                                    at BE202 <br>
                                    <span style="color: #28a745;">Attended</span>
                                </div>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>-->
        </table>
        <script src="../js/student/weeklyTimeTable.js" type="text/javascript"></script>
    </body>

</html>
