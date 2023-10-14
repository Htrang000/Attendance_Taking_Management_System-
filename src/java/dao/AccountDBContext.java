/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Campus;
import model.Role;

/**
 *
 * @author Admin
 */
public class AccountDBContext extends DBContext implements IDBContext<Account> {

    @Override
    public ArrayList<Account> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Account get(String email, String password, int campusID) {
        String sql = "SELECT a.[Account_id], Email, [Password], r.Role_id, r.Role_name, a.Campus_id FROM Account\n"
                + "a JOIN Role r ON a.Role_id = r.Role_id\n"
                + "JOIN Campus c ON c.Campus_id = a.Campus_id\n"  
                + "WHERE Email = ? AND [Password] = ? AND c.Campus_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            stm.setInt(3, campusID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setAccountID(rs.getInt("Account_id"));
                acc.setEmail(email);
                Role role = new Role();
                role.setRoleId(rs.getInt("Role_id"));
                role.setRoleName(rs.getString("Role_name"));
                acc.setRole(role);
                Campus campus = new Campus();
                campus.setCampusId(campusID);
                acc.setCampus(campus);
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Account param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
