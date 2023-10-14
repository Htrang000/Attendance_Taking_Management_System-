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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Lesson;
import model.Room;
import model.Student;
import model.TimeSlot;
import org.apache.catalina.ha.ClusterSession;

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
            String sql = "SELECT l.[Date], t.Slot_id, t.Start_time, t.End_time,"
                    + " s.Course_name, g.Group_name, r.Room_name, l.Attendance_Status "
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
            while(rs.next()){
                Lesson lesson = new Lesson();
                lesson.setDate(rs.getDate("Date"));
                lesson.setSlot(new TimeSlot(rs.getInt("Slot_id"),
                        rs.getTime("Start_time"), rs.getTime("End_time")));
                Course course = new Course();
                course.setCourseName(rs.getString("Course_name"));
                Group g = new Group();
                g.setGroupName(rs.getString("Group_name"));
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

}
