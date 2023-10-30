/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;

/**
 *
 * @author Admin
 */
public class CourseDBContext extends DBContext implements IDBContext<Course> {

    @Override
    public ArrayList<Course> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Course param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Course param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Course param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Course> getListCourse(int deptID, int semID) {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT Course_id, Course_name FROM Course\n"
                + "WHERE Dept_id = ? AND Sem_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, deptID);
            stm.setInt(2, semID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Course c = new Course();
                c.setCourseId(rs.getInt("Course_id"));
                c.setCourseName(rs.getString("Course_name"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }
    
    public static void main(String[] args) {
        CourseDBContext cdb = new CourseDBContext();
        ArrayList<Course> courses = cdb.getListCourse(1, 2);
        System.out.println(courses.size());
    }
}
