/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.home;

import controller.authentication.BasedRequiredAuthenticationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;

/**
 *
 * @author Admin
 */
public class HomeController extends BasedRequiredAuthenticationController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
    String action = req.getParameter("action");
    if(action==null){
        req.getRequestDispatcher("view/home/home.jsp").forward(req, resp);
    }else if(action.equalsIgnoreCase("schedule")){
        if(acc.getRole().getRoleId()==1){
        resp.sendRedirect(req.getContextPath()+"/instructor/scheduleOfWeek");
        }
        else{
            resp.sendRedirect(req.getContextPath()+"/student/scheduleOfWeek");
        }
    }else if(action.equalsIgnoreCase("report")){
        resp.sendRedirect(req.getContextPath()+"/instructor/report");
    }
    
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
