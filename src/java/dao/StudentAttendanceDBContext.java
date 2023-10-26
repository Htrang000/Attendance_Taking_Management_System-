/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.StudentAttendance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentAttendanceDBContext extends DBContext implements IDBContext<StudentAttendance> {

    @Override
    public ArrayList<StudentAttendance> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StudentAttendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(StudentAttendance param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(StudentAttendance param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(StudentAttendance param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<StudentAttendance> getListByLesson(int lid) {
        ArrayList<StudentAttendance> list = new ArrayList<>();
        String sql = "SELECT s.Student_id, s.Student_code, s.First_name + ' '+ s.Last_name AS [name], s.[Image],\n"
                + "a.[Status], a.Recordtime, a.Recordday, a.Comment FROM Student_Attendance a\n"
                + "JOIN Student s ON a.Student_id = s.Student_id\n"
                + "WHERE Lesson_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, lid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                StudentAttendance sa = new StudentAttendance();
                Student s = new Student();
                s.setStudentId(rs.getInt("Student_id"));
                s.setStudentCode(rs.getString("Student_code"));
                s.setName(rs.getString("name"));
                s.setImg(rs.getString("Image"));
                sa.setStudent(s);
                sa.setStatus(rs.getInt("Status"));
                sa.setRecordTime(rs.getTime("Recordtime"));
                sa.setRecordDate(rs.getDate("Recordday"));
                sa.setComment(rs.getString("Comment"));
                list.add(sa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
