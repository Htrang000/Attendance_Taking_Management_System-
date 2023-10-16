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
        req.setAttribute("listLesson", listLesson);
        req.getRequestDispatcher("../view/student/weeklyTimeTable.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException{
    }
    
}
