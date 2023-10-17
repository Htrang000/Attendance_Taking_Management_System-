/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dao.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;

/**
 *
 * @author Admin
 */
public abstract class BasedRequiredAuthenticationController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest req) {
        Account acc = (Account) req.getSession().getAttribute("session");
        if (acc != null) {
            return true;
        } else {
            String email = null;
            String password = null;
            String campus = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (email != null && password != null) {
                        break;
                    }
                    if (c.getName().equals("email")) {
                        email = c.getValue();
                    }
                    if (c.getName().equals("password")) {
                        password = c.getValue();
                    }
                    if (c.getName().equals("campus")) {
                        campus = c.getValue();

                    }
                }
            }
            if (email != null && password != null && campus != null) {
                AccountDBContext accDB = new AccountDBContext();
                Account account = accDB.get(email, password, Integer.parseInt(campus));
                return account != null;
            } else {
                return false;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            doGet(req, resp, (Account) req.getSession().getAttribute("session"));
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
    
    
    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            doPost(req, resp, (Account) req.getSession().getAttribute("session"));
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
    
    
    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc)throws ServletException, IOException;
}
