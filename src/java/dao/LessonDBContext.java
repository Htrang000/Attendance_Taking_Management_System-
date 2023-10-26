/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Instructor;
import model.Lesson;
import model.Room;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author Admin
 */
public class LessonDBContext extends DBContext implements IDBContext<LessonDBContext> {

    @Override
    public ArrayList<LessonDBContext> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LessonDBContext get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(LessonDBContext param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(LessonDBContext param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(LessonDBContext param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Lesson> getWeeklyTimeTable(Student s, Date startDate, Date endDate) {
        ArrayList<Lesson> listLesson = new ArrayList<>();
        try {
            String sql = "SELECT l.Lesson_id,l.[Date], t.Slot_id, t.Start_time, t.End_time,"
                    + " s.Course_name, g.Group_id, g.Group_name, r.Room_name, l.Attendance_Status "
                    + "FROM Student st JOIN Student_group sg\n"
                    + "ON st.Student_id = sg.Student_id\n"
                    + "JOIN [Group] g ON sg.Group_id = g.Group_id\n"
                    + "JOIN Course s ON g.Course_id = s.Course_id\n"
                    + "JOIN Lesson l ON l.Group_id = g.Group_id\n"
                    + "JOIN Time_slot t ON t.Slot_id = l.Slot_id\n"
                    + "JOIN Room r ON r.Room_id = l.Room_id\n"
                    + "WHERE st.Student_id = ? AND l.[Date] >= ? AND l.[Date] <=?;";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, s.getStudentId());
            stm.setDate(2, startDate);
            stm.setDate(3, endDate);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setLessonId(rs.getInt("Lesson_id"));
                lesson.setDate(rs.getDate("Date"));
                lesson.setSlot(new TimeSlot(rs.getInt("Slot_id"),
                        rs.getTime("Start_time"), rs.getTime("End_time")));
                Course course = new Course();
                course.setCourseName(rs.getString("Course_name"));
                Group g = new Group();
                g.setGroupName(rs.getString("Group_name"));
                g.setGroupId(rs.getInt("Group_id"));
                g.setCourse(course);
                lesson.setGroup(g);
                Room r = new Room();
                r.setRoomName(rs.getString("Room_name"));
                lesson.setRoom(r);
                lesson.setAttendanceStatus(rs.getInt("Attendance_Status"));
                listLesson.add(lesson);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLesson;
    }

    public ArrayList<Lesson> getWeeklyTimeTable(Instructor i, Date startDate, Date endDate) {
        ArrayList<Lesson> listLesson = new ArrayList<>();
        try {
            String sql = "SELECT l.Lesson_id,l.Date, t.Slot_id, t.Start_time, t.End_time, "
                    + "c.Course_name, g.Group_name, g.Group_id,r.Room_name, l.Attendance_Status\n"
                    + "FROM Instructor i JOIN Lesson l ON i.Instructor_id = l.Instructor_id\n"
                    + "JOIN Room r ON l.Room_id = r.Room_id JOIN Time_slot t ON l.Slot_id = t.Slot_id\n"
                    + "JOIN [Group] g ON g.Group_id = l.Group_id\n"
                    + "JOIN Course c ON g.Course_id = c.Course_id\n"
                    + "WHERE i.Instructor_id = ? AND l.[Date] >= ? AND l.[Date] <= ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, i.getInstructorId());
            stm.setDate(2, startDate);
            stm.setDate(3, endDate);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setLessonId(rs.getInt("Lesson_id"));
                lesson.setDate(rs.getDate("Date"));
                lesson.setSlot(new TimeSlot(rs.getInt("Slot_id"),
                        rs.getTime("Start_time"), rs.getTime("End_time")));
                Course course = new Course();
                course.setCourseName(rs.getString("Course_name"));
                Group g = new Group();
                g.setGroupName(rs.getString("Group_name"));
                g.setGroupId(rs.getInt("Group_id"));
                g.setCourse(course);
                lesson.setGroup(g);
                Room r = new Room();
                r.setRoomName(rs.getString("Room_name"));
                lesson.setRoom(r);
                lesson.setAttendanceStatus(rs.getInt("Attendance_Status"));
                listLesson.add(lesson);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLesson;
    }

    public void updateStatus(int status, int lessonId) {
        String sql = "UPDATE [Lesson]\n"
                + "   SET [Attendance_Status] = ?\n"
                + " WHERE Lesson_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setInt(2, lessonId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getInstructorCode(int Lid) {
        String sql = "SELECT i.Instructor_code FROM Lesson l JOIN Instructor i ON "
                + "l.Instructor_id = i.Instructor_id\n"
                + "WHERE Lesson_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, Lid);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getString("Instructor_code");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static void main(String[] args) {
        LessonDBContext ldb = new LessonDBContext();
        Instructor i = new Instructor();
        i.setInstructorId(1);
        ArrayList<Lesson> lessons = ldb.getWeeklyTimeTable(i, Date.valueOf("2023-10-16"), Date.valueOf("2023-10-22"));
        System.out.println(lessons.size());
    }

}
