/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BasedAuthorizatedController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import model.Account;
import model.Feature;

/**
 *
 * @author Admin
 */
public class ViewInformationController extends BasedAuthorizatedController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
