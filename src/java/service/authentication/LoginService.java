/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.authentication;

import dao.AccountDBContext;
import dao.IDBContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Admin
 */
public class LoginService {
    private AccountDBContext accountDBContext;

    public LoginService(AccountDBContext accountDBContext) {
        this.accountDBContext = accountDBContext;
    }
    
    public Account getAccount(String email, String password, int campusId){
        return  accountDBContext.get(email, password, campusId);
    }

    public void saveToCookie(HttpServletResponse resp, String email, String password, String remember, int campusId) {
        if (remember!=null) {
            Cookie email_c = new Cookie("email", email);
            Cookie password_c = new Cookie("password", password);
            Cookie campus_c = new Cookie("campus", campusId + "");
            email_c.setMaxAge(8*24*3600);
            password_c.setMaxAge(8*24*3600);
            campus_c.setMaxAge(3*24*3600);
            resp.addCookie(email_c);
            resp.addCookie(password_c);
            resp.addCookie(campus_c);
        }
    }

}