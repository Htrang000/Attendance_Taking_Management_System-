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
import model.Instructor;


/**
 *
 * @author Admin
 */
public class InstructorDBContext extends DBContext implements IDBContext<Instructor> {

    @Override
    public ArrayList<Instructor> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Instructor get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Instructor param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Instructor param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Instructor param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Instructor getByAccount(Account acc) {
        String sql = "SELECT i.Instructor_id, i.Instructor_code\n"
                + "  FROM [Account] a join [Instructor] i\n"
                + "  On a.Account_id = i.Account_id\n"
                + "  WHERE a.Account_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, acc.getAccountID());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Instructor i = new Instructor();
                i.setInstructorId(rs.getInt("Instructor_id"));
                i.setInstructorCode(rs.getString("Instructor_code"));
                i.setAccount(acc);
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Instructor getInfInstructor(Account acc) {
        String sql = "SELECT i.Instructor_code, i.First_name + ' ' + i.Last_name as [Name], i.[Image],\n"
                + "i.Gender,i.Dob,i.Phone, i.Email FROM Account a JOIN Instructor i\n"
                + "ON a.Account_id = i.Account_id WHERE a.Account_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, acc.getAccountID());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Instructor i = new Instructor();
                i.setInstructorCode(rs.getString("Instructor_code"));
                i.setName(rs.getString("Name"));
                i.setImg(rs.getString("Image"));
                i.setGender(rs.getBoolean("Gender"));
                i.setPhone(rs.getString("Phone"));
                i.setEmail(rs.getString("Email"));
                i.setDob(rs.getDate("Dob"));
                return i;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

    public static void main(String[] args) {
        InstructorDBContext idbc = new InstructorDBContext();
        Account a = new Account();
        a.setAccountID(1);
        System.out.println(idbc.getInfInstructor(a).getInstructorCode());
    }

}
