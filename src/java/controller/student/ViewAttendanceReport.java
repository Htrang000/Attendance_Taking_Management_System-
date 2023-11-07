/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.BasedAuthorizatedController;
import dao.CourseDBContext;
import dao.GroupDBContext;
import dao.SemesterDBContext;
import dao.StudentAttendanceDBContext;
import dao.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import model.Account;
import model.Feature;
import model.Group;
import model.Semester;
import model.Student;
import model.StudentAttendance;
import service.student.StudentService;

/**
 *
 * @author Admin
 */
public class ViewAttendanceReport extends BasedAuthorizatedController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        SemesterDBContext sdb = new SemesterDBContext();
        ArrayList<Semester> sems = sdb.getList();
        req.setAttribute("sems", sems);
        GroupDBContext gdb = new GroupDBContext();
        StudentDBContext stdb = new StudentDBContext();
        StudentService ss = new StudentService(stdb);
        HttpSession session = req.getSession();
        int semID = req.getParameter("semesterId") != null ? (int) Integer.parseInt(req.getParameter("semesterId")) : 1;
        session.setAttribute("semesterId", semID);
        ArrayList<Group> groups = gdb.getListGroupStudent(semID, ss.getStudentByAcc(acc).getStudentId());
        req.setAttribute("groups", groups);
        if (req.getParameter("groupId") != null) {
            int groupId = Integer.parseInt(req.getParameter("groupId"));
            StudentAttendanceDBContext sadb = new StudentAttendanceDBContext();
            ss = new StudentService(stdb, null, sadb);
            ArrayList<StudentAttendance> saList = ss.getListByGroupAndStudent(ss.getStudentByAcc(acc).getStudentId(), groupId);
            
            req.setAttribute("saList", saList);
            String iName = saList.get(0).getLesson().getInstructor().getInstructorCode();
            req.setAttribute("iName", iName);
            CourseDBContext cdb = new CourseDBContext();
            req.setAttribute("courseName", cdb.getCourseName(Integer.parseInt(req.getParameter("groupId"))));
            req.setAttribute("groupName", gdb.getGroupName(groupId));
        }
        req.getRequestDispatcher("../view/attendanceReport/ViewStudentAttendanceReport.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        req.getRequestDispatcher("../view/attendanceReport/ViewStudentAttendanceReport.jsp").forward(req, resp);
    }

}
