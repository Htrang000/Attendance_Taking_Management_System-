/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.BasedRequiredAuthenticationController;
import dao.InstructorDBContext;
import dao.LessonDBContext;
import dao.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Instructor;
import model.Lesson;
import model.Student;
import service.instructor.InstructorService;
import service.student.StudentService;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public class WeeklyTimeTableController extends BasedRequiredAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
        DateUtil util = new DateUtil();
        Date from = util.getMondayOfCurrentWeek();
        Date to = util.getSundayOfCurrentWeek();
        ArrayList<Date> dates = new ArrayList<>();
        try {
            dates = (ArrayList<Date>) DateUtil.getSQLDatesBetween(from.toString(), to.toString());
        } catch (ParseException ex) {
            Logger.getLogger(controller.instructor.WeeklyTimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LessonDBContext ldb = new LessonDBContext();
        StudentDBContext stdb = new StudentDBContext();
        StudentService ss = new StudentService(stdb, ldb);
        Account a = (Account) req.getSession().getAttribute("session");
        Student s = ss.getStudentByAcc(a);
        ArrayList<Lesson> listLesson = ss.getCurrentWeekly(s, from, to);
        req.setAttribute("listLesson", listLesson);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("dates", dates);
        req.getRequestDispatcher("../view/weeklyTimeTable/weeklyTimeTable.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        ArrayList<Date> dates = new ArrayList<>();
        try {
            dates = (ArrayList<Date>) DateUtil.getSQLDatesBetween(from, to);
        } catch (ParseException ex) {
            Logger.getLogger(WeeklyTimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LessonDBContext ldb = new LessonDBContext();
        StudentDBContext stdb = new StudentDBContext();
        StudentService ss = new StudentService(stdb, ldb);
        Account a = (Account) req.getSession().getAttribute("session");
        Student s = ss.getStudentByAcc(a);
        ArrayList<Lesson> listLesson = ss.getCurrentWeekly(s, Date.valueOf(from), Date.valueOf(to));
        req.setAttribute("listLesson", listLesson);
        req.setAttribute("dates", dates);
        req.setAttribute("from", Date.valueOf(from));
        req.setAttribute("to", Date.valueOf(to));
        req.getRequestDispatcher("../view/weeklyTimeTable/weeklyTimeTable.jsp").forward(req, resp);
    }
    

}
