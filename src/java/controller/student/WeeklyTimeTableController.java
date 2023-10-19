/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.BasedRequiredAuthenticationController;
import dao.LessonDBContext;
import dao.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Account;
import model.Lesson;
import model.Student;
import service.student.StudentService;

/**
 *
 * @author Admin
 */
public class WeeklyTimeTableController extends BasedRequiredAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
        LessonDBContext ldb = new LessonDBContext();
        StudentDBContext stdb = new StudentDBContext();
        StudentService ss = new StudentService(stdb, ldb);
        Account a = (Account) req.getSession().getAttribute("session");
        Student s = ss.getStudentByAcc(a);
        ArrayList<Lesson> listLesson = ss.getCurrentWeekly(s);
        req.setAttribute("listLesson", listLesson);
        req.getRequestDispatcher("../view/student/weeklyTimeTable.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
        Date from = Date.valueOf(req.getParameter("from"));
        Date to = Date.valueOf(req.getParameter("to"));
        LessonDBContext ldb = new LessonDBContext();
        StudentDBContext stdb = new StudentDBContext();
        StudentService ss = new StudentService(stdb, ldb);
        Account a = (Account) req.getSession().getAttribute("session");
        Student s = ss.getStudentByAcc(a);
        ArrayList<Lesson> listLesson = ss.getCurrentWeekly(s, from, to);
        req.setAttribute("listLesson", listLesson);
        req.getRequestDispatcher("../view/student/weeklyTimeTable.jsp").forward(req, resp);
    }
    

}
