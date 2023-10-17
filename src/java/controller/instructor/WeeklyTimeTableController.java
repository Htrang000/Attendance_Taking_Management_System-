/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BasedRequiredAuthenticationController;
import dao.InstructorDBContext;
import dao.LessonDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Account;
import model.Instructor;
import model.Lesson;
import service.instructor.InstructorService;

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
        for (int j = 0; j < listLesson.size(); j++) {
            for (int k = 0; k < listLesson.size() - j - 1; k++) {
                if (listLesson.get(k).getDate().getDay() > listLesson.get(k + 1).getDate().getDay()) {
                    swap(listLesson, k, k + 1);
                }
            }
        }
        req.setAttribute("listLesson", listLesson);
        req.getRequestDispatcher("../view/student/weeklyTimeTable.jsp").forward(req, resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException{
        int week = Integer.parseInt(req.getParameter("week"));
        Date monday = Date.valueOf(req.getParameter("monday"));
        Date sunday = Date.valueOf(monday.toLocalDate().plusDays(6));
        LessonDBContext ldb = new LessonDBContext();
        InstructorDBContext idb = new InstructorDBContext();
        InstructorService is = new InstructorService(idb, ldb);
        Account a = (Account)req.getSession().getAttribute("session");
        Instructor i = is.getInstructorByAcc(a);
        System.out.println(i.getInstructorId());
        ArrayList<Lesson> listLesson = is.getCurrentWeekly(i, monday, sunday);
        for (int j = 0; j < listLesson.size(); j++) {
            for (int k = 0; k < listLesson.size() - j - 1; k++) {
                if (listLesson.get(k).getDate().getDay() > listLesson.get(k + 1).getDate().getDay()) {
                    swap(listLesson, k, k + 1);
                }
            }
        }
        req.setAttribute("week", week);
        req.setAttribute("listLesson", listLesson);
        req.getRequestDispatcher("../view/student/weeklyTimeTable.jsp").forward(req, resp);
    }
    
    private static void swap(ArrayList<Lesson> list, int i, int j) {
        Lesson tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
    
}