/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BasedRequiredAuthenticationController;
import dao.InstructorDBContext;
import dao.LessonDBContext;
import dao.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Account;
import model.Instructor;
import model.Lesson;
import model.Student;
import service.instructor.InstructorService;
import service.student.StudentService;

/**
 *
 * @author Admin
 */
public class WeeklyTimeTableController extends BasedRequiredAuthenticationController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc)throws ServletException, IOException {
        LessonDBContext ldb = new LessonDBContext();
        InstructorDBContext idb = new InstructorDBContext();
        InstructorService is = new InstructorService(idb, ldb);
        Account a = (Account)req.getSession().getAttribute("session");
        Instructor i = is.getInstructorByAcc(a);
        System.out.println(i.getInstructorId());
        ArrayList<Lesson> listLesson = is.getCurrentWeekly(i);
        req.setAttribute("listLesson", listLesson);
        req.getRequestDispatcher("../view/student/weeklyTimeTable.jsp").forward(req, resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException{
        Date monday = Date.valueOf(req.getParameter("monday"));
        Date sunday = Date.valueOf(monday.toLocalDate().plusDays(6));
        LessonDBContext ldb = new LessonDBContext();
        InstructorDBContext idb = new InstructorDBContext();
        InstructorService is = new InstructorService(idb, ldb);
        Account a = (Account)req.getSession().getAttribute("session");
        Instructor i = is.getInstructorByAcc(a);
        System.out.println(i.getInstructorId());
        ArrayList<Lesson> listLesson = is.getCurrentWeekly(i, monday, sunday);
        req.setAttribute("listLesson", listLesson);
        req.getRequestDispatcher("../view/student/weeklyTimeTable.jsp").forward(req, resp);
    }
    
}
