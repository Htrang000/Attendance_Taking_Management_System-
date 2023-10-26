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
import model.Student;
import model.StudentAttendance;
import service.instructor.InstructorService;
import service.student.StudentService;

/**
 *
 * @author Admin
 */
public class EditAttendaceController extends BasedAuthorizatedController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        StudentAttendanceDBContext sadb = new StudentAttendanceDBContext();
        StudentService ss = new StudentService(sadb);
        LessonDBContext ldb = new LessonDBContext();
        InstructorService is = new InstructorService(ldb);
        int group_id = Integer.parseInt(req.getParameter("groupId"));
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));
        GroupDBContext gdb = new GroupDBContext();
        String groupName = gdb.getGroupName(group_id);
        String instructorCode = is.getInstructorCode(lessonId);
        ArrayList<StudentAttendance> sas = ss.getListStudentAttendances(lessonId);
        req.setAttribute("sas", sas);
        req.setAttribute("groupId", group_id);
        req.setAttribute("groupName", groupName);
        req.setAttribute("lessonId", lessonId);
        req.setAttribute("instructorCode", instructorCode);
        
        req.getRequestDispatcher("../view/editAttendance/editAttendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
