/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dao.CampusDBContext;
import dao.IDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Campus;
import service.authentication.LoginService;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDBContext campus = new CampusDBContext();
        ArrayList<Campus> campuses = campus.getList();
        req.setAttribute("campuses", campuses);
        req.getRequestDispatcher("view/authentication/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int campusID = Integer.parseInt(req.getParameter("campus"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        LoginService service = new LoginService();
        Account acc = service.getAccount(email, password, campusID);
        if (acc != null) {
            HttpSession session = req.getSession();
            session.setAttribute("session", acc);
            service.saveToCookie(resp, email, password, remember);
            resp.sendRedirect("");
        } else {
            
        }

    }

}
