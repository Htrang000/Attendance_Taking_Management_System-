/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function save() {
    var url = "http://localhost:8888/Assign/instructor/scheduleOfWeek";
    window.location.href = url;
}
function edit(groupId, lessonid) {
    var url = `http://localhost:8888/Assign/instructor/takeAttendance?groupId=${groupId}&lessonId=${lessonid}`;
    window.location.href = url;
}
