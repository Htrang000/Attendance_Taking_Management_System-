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
    public Account getAccount(String email, String password, int campusId){
        AccountDBContext account = new AccountDBContext();
        return  account.get(email, password, campusId);
    }

    public void saveToCookie(HttpServletResponse resp, String email, String password, String remember) {
        if (remember!=null) {
            Cookie email_c = new Cookie("email", email);
            Cookie password_c = new Cookie("password", email);
            email_c.setMaxAge(8*24*3600);
            password_c.setMaxAge(8*24*3600);
            resp.addCookie(email_c);
            resp.addCookie(password_c);
        }
    }
    

}