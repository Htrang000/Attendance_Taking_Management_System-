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
                margin: 5px;
            }
            /* Thêm một font chữ khác */
            body {
                font-family: Arial, sans-serif;
            }

            /* CSS cho header của bảng */


            h1 {
                background-color: white;
                color: #f37022;
                padding: 5px;
                text-align: center;
            }


            #timeTable {
                width: 100%;
                margin: 1px auto;
                overflow-x: auto;
                border-collapse: collapse;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }


            /* CSS cho các ô dữ liệu */
            #timeTable td {
                padding: 5px;
                border-bottom: 1px solid white;
                font-size: 14px;
                border: 1px solid #ccc;
                text-align: center;
            }

            .fixed-column {
                min-width: 150px; /* Điều chỉnh kích thước theo ý muốn */
            }

            /* CSS cho các hàng chẵn */
            #timeTable tr:nth-child(even) {
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
            .menu {
                background-color: #f37022;
                padding: 10px;
                color: white;
                text-align: center;
                border-radius: 10px;
                font-weight: bold;
            }

            th,
            .date {
                background-color: #6b90da;
                color: white;
                text-align: left;
                padding: 10px;
                font-weight: bold;
                border-bottom: 1px solid white;
            }

            #table-container {
                width: 100%;
                overflow-x: auto;
                max-width: 95%; /* Điều chỉnh chiều rộng tối đa theo ý của bạn */
                margin: 0 auto;
            }

            #table-container {
                width: 100%;
                overflow-x: auto;
                margin: 0 auto;
            }

            #timeTable {
                width: auto;
                border-collapse: collapse;
            }

            /* Cố định kích thước của 7 cột đầu tiên */
            #timeTable th:nth-child(-n+7),
            #timeTable td:nth-child(-n+7) {
                width: 150px; /* Điều chỉnh kích thước theo ý muốn */
            }

            /* Thanh cuộn ngang cho các cột còn lại */
            #timeTable th:nth-child(n+8),
            #timeTable td:nth-child(n+8) {
                min-width: 150px; /* Điều chỉnh kích thước theo ý muốn */
            }




        </style>
    </head>

    <body>
        <h1>Attendance Taking Management System</h1>
        <div class="menu">
            <% String contextPath = request.getContextPath();%>
            <a href="<%=contextPath + "/home"%>" class="home-button">Home</a>
            <span>Time weekly table</span>       
            <a href="<%=contextPath + "/logout"%>" class="logout-button">Logout</a>
        </div>
        <div id="table-container">
            <table id="timeTable" border="1px">
                <th rowspan="2">
                    <form action="scheduleOfWeek" method="Post" id="my-form">
                        <table class="select">
                            <tr class="timeSelect">
                                <td style="border:none;">From</td>
                                <td style="border:none;"><input type="date" value="${requestScope.from}" id="from" name="from" onchange="submitForm()"/></td>
                            </tr>
                            <tr class="timeSelect">
                                <td style="border:none;">To</td>
                                <td style="border:none;"><input type="date" value="${requestScope.to}" id="to" name="to" onchange="submitForm()"/> </td>
                            </tr>
                        </table>
                    </form>
                </th>

                <c:forEach items="${requestScope.dates}" var="d">
                    <c:if test="${d.getDay()==1}">
                        <th class="fixed-column">Mon</th>
                        </c:if>
                        <c:if test="${d.getDay()==2}">
                        <th class="fixed-column">Tue</th>
                        </c:if>
                        <c:if test="${d.getDay()==3}">
                        <th class="fixed-column">Wed</th>
                        </c:if>
                        <c:if test="${d.getDay()==4}">
                        <th class="fixed-column">Thu</th> 
                        </c:if>
                        <c:if test="${d.getDay()==5}">
                        <th class="fixed-column">Fri</th>  
                        </c:if>
                        <c:if test="${d.getDay()==6}">
                        <th class="fixed-column">Sat</th> 
                        </c:if>
                        <c:if test="${d.getDay()==0}">
                        <th class="fixed-column">Sun</th> 
                        </c:if>

                </c:forEach>
                <tr>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td class="date"> ${d} </td>
                    </c:forEach>
                </tr>

                <c:forEach begin="1" end="6" varStatus="slot">
                    <td>Slot ${slot.index}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.listLesson}" var="l">
                                <c:if test="${l.date eq d and (l.slot.slotId eq slot.index or l.slot.slotId eq slot.index+5) }">
                                    <div>
                                        ${l.group.course.courseName   } -<a href="<%=contextPath + "/instructor/takeAttendance?groupId="%>${l.group.groupId}&lessonId=${l.lessonId}">${l.group.groupName}</a><br>
                                        at ${l.room.roomName} <br>
                                        <span style="color: ${l.attendanceStatus eq 1 ? 'green' : 'red'};">
                                            ${l.attendanceStatus eq 1 ? 'Present' : (l.attendanceStatus eq 2 ? 'Absent' : 'Not given')}
                                        </span> <br>
                                        (${l.slot.startTime} - ${l.slot.endTime})
                                    </div>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                    </tr>

                </c:forEach>

            </table>
        </div>

        <script src="../js/weeklyTimeTable/WeeklyTimeTable.js" type="text/javascript"></script>
    </body>

</html>