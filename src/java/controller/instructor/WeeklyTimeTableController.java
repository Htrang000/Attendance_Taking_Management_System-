/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BasedAuthorizatedController;
import controller.authentication.BasedRequiredAuthenticationController;
import dao.InstructorDBContext;
import dao.LessonDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feature;
import model.Instructor;
import model.Lesson;
import service.instructor.InstructorService;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public class WeeklyTimeTableController extends BasedAuthorizatedController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        DateUtil util = new DateUtil();
        Date from = util.getMondayOfCurrentWeek();
        Date to = util.getSundayOfCurrentWeek();
        ArrayList<Date> dates = new ArrayList<>();
        try {
            dates = (ArrayList<Date>) DateUtil.getSQLDatesBetween(from.toString(), to.toString());
        } catch (ParseException ex) {
            Logger.getLogger(WeeklyTimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LessonDBContext ldb = new LessonDBContext();
        InstructorDBContext idb = new InstructorDBContext();
        InstructorService is = new InstructorService(idb, ldb);
        Account a = (Account) req.getSession().getAttribute("session");
        Instructor i = is.getInstructorByAcc(a);
        ArrayList<Lesson> listLesson = is.getCurrentWeekly(i, from, to);
        req.setAttribute("listLesson", listLesson);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("dates", dates);
        req.getRequestDispatcher("../view/weeklyTimeTable/weeklyTimeTable.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        ArrayList<Date> dates = new ArrayList<>();
        try {
            dates = (ArrayList<Date>) DateUtil.getSQLDatesBetween(from, to);
        } catch (ParseException ex) {
            Logger.getLogger(WeeklyTimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LessonDBContext ldb = new LessonDBContext();
        InstructorDBContext idb = new InstructorDBContext();
        InstructorService is = new InstructorService(idb, ldb);
        Account a = (Account) req.getSession().getAttribute("session");
        Instructor i = is.getInstructorByAcc(a);
        ArrayList<Lesson> listLesson = is.getCurrentWeekly(i, Date.valueOf(from), Date.valueOf(to));
        req.setAttribute("listLesson", listLesson);
        req.setAttribute("dates", dates);
        req.setAttribute("from", Date.valueOf(from));
        req.setAttribute("to", Date.valueOf(to));
        req.getRequestDispatcher("../view/weeklyTimeTable/weeklyTimeTable.jsp").forward(req, resp);
    }

}
