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
import model.Group;
import model.Student;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public class StudentDBContext extends DBContext implements IDBContext<Student> {

    @Override
    public ArrayList<Student> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody  
    }

    @Override
    public void insert(Student param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Student getByAccount(Account acc) {
        String sql = "SELECT s.Student_id, s.Student_code\n"
                + "FROM Account a JOIN Student s\n"
                + "ON a.Account_id = s.Account_id WHERE s.Account_id = ?;";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, acc.getAccountID());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getInt("Student_id"));
                s.setStudentCode(rs.getString("Student_code"));
                s.setAccount(acc);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Student> getListByGroupId(int group_id) {
        String sql = "SELECT s.Student_id, s.Student_code, s.First_name + ' ' + s.Last_name AS [Student_name], "
                + "s.Image FROM Student s JOIN Student_Group sg ON s.Student_id = sg.Student_id\n"
                + "WHERE Group_id = ?";
        ArrayList<Student> students = new ArrayList<>();
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, group_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getInt("Student_id"));
                s.setStudentCode(rs.getString("Student_code"));
                s.setName(rs.getString("Student_name"));
                s.setImg(rs.getString("Image"));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public void updateStudentAttendance(int studentId, int lessonId, int status, String comment) {
        try {
            String sql = "UPDATE [Student_Attendance]\n"
                    + "   SET [Status] = ?\n"
                    + "      ,[Recordtime] = ?\n"
                    + "      ,[Recordday] = ?\n"
                    + "      ,[Comment] = ?\n"
                    + " WHERE [Student_id] = ? AND [Lesson_id] = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setTime(2, DateUtil.getCurrentTime());
            stm.setDate(3, DateUtil.getCurrentDate());
            stm.setString(4, comment);
            stm.setInt(5, studentId);
            stm.setInt(6, lessonId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public static void main(String[] args) {
        StudentDBContext sdb = new StudentDBContext();
        ArrayList<Student> students = sdb.getListByGroupId(19);
        System.out.println(students.size());
    }
}
