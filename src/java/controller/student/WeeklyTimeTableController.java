/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.BasedRequiredAuthenticationController;
import dao.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Lesson;
import model.Student;
import service.student.StudentService;

/**
 *
 * @author Admin
 */
public class WeeklyTimeTableController extends BasedRequiredAuthenticationController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc)throws ServletException, IOException {
        StudentDBContext sdbc = new StudentDBContext();
        StudentService ss = new StudentService(sdbc);
        Account a = (Account)req.getSession().getAttribute("session");
        System.out.println(a.getAccountID());
        Student s = ss.getStudentByAcc(acc);
        ArrayList<Lesson> listLesson = ss.getCurrentWeekly(s);
        req.setAttribute("listLesson", listLesson);
        req.getRequestDispatcher("../view/student/weeklyTimeTable.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException{
    }
    
}
