/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BasedAuthorizatedController;
import dao.CourseDBContext;
import dao.DepartmentDBContext;
import dao.GroupDBContext;
import dao.InstructorDBContext;
import dao.SemesterDBContext;
import dao.StudentAttendanceDBContext;
import dao.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import model.Account;
import model.Course;
import model.Department;
import model.Feature;
import model.Group;
import model.Semester;
import model.Student;
import model.StudentAttendance;
import service.instructor.InstructorService;
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
        DepartmentDBContext ddb = new DepartmentDBContext();
        ArrayList<Department> depts = ddb.getList();
        CourseDBContext cdb = new CourseDBContext();

        HttpSession session = req.getSession();

        int semID = req.getParameter("semesterId") != null ? (int) Integer.parseInt(req.getParameter("semesterId")) : 1;
        int deptID = req.getParameter("departmentId") != null ? Integer.parseInt(req.getParameter("departmentId")) : 1;
        System.out.println(semID + " " + deptID);
        // Lưu giá trị semesterId và departmentId vào session
        session.setAttribute("semesterId", semID);
        session.setAttribute("departmentId", deptID);
        ArrayList<Course> courses = cdb.getListCourse(deptID, semID);

        int courseID = req.getParameter("courseId") != null ? Integer.parseInt(req.getParameter("courseId")) : 1;
        GroupDBContext gdb = new GroupDBContext();

        InstructorDBContext idb = new InstructorDBContext();
        InstructorService is = new InstructorService(idb);

        //ArrayList<Group> groups = gdb.getListGroup(courseID);
        ArrayList<Group> groups = gdb.getListGroupInstructor(courseID, is.getInstructorByAcc(acc).getInstructorId());
        req.setAttribute("sems", sems);
        req.setAttribute("depts", depts);
        req.setAttribute("courses", courses);

        req.setAttribute("groups", groups);

        if (req.getParameter("groupId") != null) {
            int groupId = Integer.parseInt(req.getParameter("groupId"));
            StudentAttendanceDBContext sadb = new StudentAttendanceDBContext();
            StudentDBContext stdb = new StudentDBContext();
            StudentService ss = new StudentService(stdb, null, sadb);
            ArrayList<Student> students = ss.getListStudentByGroupId(groupId);
            ArrayList<StudentAttendance> saList = new ArrayList<>();
            Map<Student, ArrayList<StudentAttendance>> mapping = ss.mapping(students, saList, groupId);
            
            req.setAttribute("groupName", gdb.getGroupName(groupId));
            req.setAttribute("saListSize", saList.size());
            req.setAttribute("students", students);
            req.setAttribute("mapping", mapping);
            req.setAttribute("courseName", cdb.getCourseName(Integer.parseInt(req.getParameter("groupId"))));
            req.setAttribute("iName", idb.getByAccount(acc).getInstructorCode());
        }
        req.getRequestDispatcher("../view/attendanceReport/ViewGroupAttendanceReport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
