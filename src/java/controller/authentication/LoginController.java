/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dao.AccountDBContext;
import dao.CampusDBContext;
import dao.IDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.CookieStore;
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
        Cookie[] cookies = req.getCookies();
        String email = null;
        String password = null;
        String campusID = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (email != null && password != null && campusID != null) {
                    break;
                }
                if (c.getName().equals("email")) {
                    email = c.getValue();
                }
                if (c.getName().equals("password")) {
                    password = c.getValue();
                }
                if (c.getName().equals("campus")) {
                    campusID = c.getValue();

                }
            }
        }
        
        if (email != null && password != null && campusID != null) {
            AccountDBContext accDB = new AccountDBContext();
            Account account = accDB.get(email, password, Integer.parseInt(campusID));
            if (account != null) {
                HttpSession session = req.getSession();
                session.setAttribute("session", account);
                resp.sendRedirect("view/home/home.jsp");
            }
        } else {
            IDBContext campus = new CampusDBContext();
            ArrayList<Campus> campuses = campus.getList();
            req.setAttribute("campuses", campuses);
            req.getRequestDispatcher("view/authentication/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int campusID = Integer.parseInt(req.getParameter("campus"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        AccountDBContext accountDBContext = new AccountDBContext();
        LoginService service = new LoginService(accountDBContext);
        Account acc = service.getAccount(email, password, campusID);
        if (acc != null) {
            HttpSession session = req.getSession();
            session.setAttribute("session", acc);
            service.saveToCookie(resp, email, password, remember, campusID);
            resp.sendRedirect("view/home/home.jsp");
        } else {
            String error = "Your account is invalid. Please try again";
            req.setAttribute("error", error);
            IDBContext campus = new CampusDBContext();
            ArrayList<Campus> campuses = campus.getList();
            req.setAttribute("campuses", campuses);
            req.getRequestDispatcher("view/authentication/login.jsp").forward(req, resp);
        }

    }

}
