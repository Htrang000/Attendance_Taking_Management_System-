/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BasedAuthorizatedController;
import dao.GroupDBContext;
import dao.LessonDBContext;
import dao.StudentAttendanceDBContext;
import dao.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import model.Account;
import model.Feature;
import model.StudentAttendance;
import service.instructor.InstructorService;
import service.student.StudentService;

/**
 *
 * @author Admin
 */
public class TakeAttendanceController extends BasedAuthorizatedController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        StudentAttendanceDBContext sadb = new StudentAttendanceDBContext();
        StudentService ss = new StudentService(sadb);
        int group_id = Integer.parseInt(req.getParameter("groupId"));
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));
        GroupDBContext gdb = new GroupDBContext();
        String groupName = gdb.getGroupName(group_id);
        ArrayList<StudentAttendance> sas = ss.getListByLesson(lessonId);
        req.setAttribute("sas", sas);
        req.setAttribute("groupId", group_id);
        req.setAttribute("groupName", groupName);
        req.setAttribute("lessonId", lessonId);
        req.getRequestDispatcher("../view/takeAttendance/takeAttendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        String[] indexs = req.getParameterValues("indexs");
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));
        int group_id = Integer.parseInt(req.getParameter("groupId"));
        LessonDBContext ldb = new LessonDBContext();
        StudentDBContext sdb = new StudentDBContext();
        InstructorService is = new InstructorService(ldb);
        StudentService ss = new StudentService(sdb);
        for (String index : indexs) {
            int studentID = Integer.parseInt(req.getParameter("studentid" + index));
            int status = Integer.parseInt(req.getParameter("status" + index));
            String comment = req.getParameter("comment" + index);
            is.updateLessonStatus(1, lessonId);
            ss.updateStudentAttendance(studentID, lessonId, status, comment);
        }
        resp.sendRedirect(req.getContextPath() + "/instructor/editAttendance?lessonId=" + lessonId + "&groupId=" + group_id);
    }

}