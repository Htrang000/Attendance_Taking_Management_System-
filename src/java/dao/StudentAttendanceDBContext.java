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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Instructor;
import model.Lesson;
import model.Student;
import model.TimeSlot;
import service.student.StudentService;

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
            while (rs.next()) {
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

    public ArrayList<StudentAttendance> getListByGroupAndStudent(int sid, int gid) {
        ArrayList<StudentAttendance> list = new ArrayList<>();
        String sql = "SELECT s.Student_code, s.First_name + ' ' + s.Last_name AS [Name],\n"
                + "s.[Image], sa.[Status], l.Session_no, l.[Date], l.Slot_id, i.Instructor_code FROM Student s \n"
                + "JOIN Student_attendance sa ON sa.Student_id = s.Student_id\n"
                + "JOIN Lesson l ON sa.Lesson_id = l.Lesson_id JOIN Instructor i ON l.Instructor_id = i.Instructor_id\n"
                + "WHERE s.Student_id = ? AND l.Group_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StudentAttendance sa = new StudentAttendance();
                Student s = new Student();
                s.setStudentCode(rs.getString("Student_code"));
                s.setName(rs.getString("Name"));
                s.setImg(rs.getString("Image"));
                sa.setStudent(s);
                sa.setStatus(rs.getInt("Status"));
                Lesson l = new Lesson();
                l.setSessionNo(rs.getInt("Session_no"));
                l.setDate(rs.getDate("Date"));
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setSlotId(rs.getInt("Slot_id"));
                l.setSlot(timeSlot);
                Instructor i = new Instructor();
                i.setInstructorCode(rs.getString("Instructor_code"));
                sa.setLesson(l);
                l.setInstructor(i);
                list.add(sa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Map<Student, ArrayList<StudentAttendance>> mapping(ArrayList<Student> students, ArrayList<StudentAttendance> saList, int groupId) {
        Map<Student, ArrayList<StudentAttendance>> mapping = new LinkedHashMap<>();
        ArrayList<StudentAttendance> sa = new ArrayList<>();
        for (Student student : students) {
            sa = getListByGroupAndStudent(student.getStudentId(), groupId);
            int count = 0;
            for (StudentAttendance studentAttendance : sa) {
                if (studentAttendance.getStatus() == 0) {
                    count++;
                }
            }
            student.setPercentageAttendance(count * 100 / sa.size());
            mapping.put(student, sa);
        }
        saList.addAll(sa);
        return mapping;
    }

}
