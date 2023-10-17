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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
        Date monday = Date.valueOf(req.getParameter("monday"));
        Date sunday = Date.valueOf(monday.toLocalDate().plusDays(6));
        LessonDBContext ldb = new LessonDBContext();
        StudentDBContext stdb = new StudentDBContext();
        StudentService ss = new StudentService(stdb, ldb);
        Account a = (Account) req.getSession().getAttribute("session");
        Student s = ss.getStudentByAcc(a);
        ArrayList<Lesson> listLesson = ss.getCurrentWeekly(s, monday, sunday);
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
    
        private static void swap(ArrayList<Lesson> list, int i, int j) {
        Lesson tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

}
