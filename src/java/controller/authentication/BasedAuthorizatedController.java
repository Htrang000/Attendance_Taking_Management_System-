/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dao.FeatureDBContext;
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
public abstract class BasedAuthorizatedController extends BasedRequiredAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
        if (isAuthenticated(req, acc)) {
            doGet(req, resp, acc, acc.getRole().getFeatures());
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    private boolean isAuthenticated(HttpServletRequest req, Account acc) {
        FeatureDBContext fdb = new FeatureDBContext();
        Set<Feature> features = fdb.getListByRoleID(acc.getRole().getRoleId(), req.getServletPath());
        acc.getRole().setFeatures(features);
        return !features.isEmpty();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc) throws ServletException, IOException {
        if (isAuthenticated(req, acc)) {
            doPost(req, resp, acc, acc.getRole().getFeatures());
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account acc, Set<Feature> features) throws ServletException, IOException;

}
