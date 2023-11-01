/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.student;

import dao.LessonDBContext;
import dao.StudentAttendanceDBContext;
import dao.StudentDBContext;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import model.Account;
import model.Lesson;
import model.Student;
import model.StudentAttendance;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public class StudentService {

    private StudentDBContext stdb;
    private LessonDBContext ldb;
    private StudentAttendanceDBContext sadb;

    public StudentService(StudentDBContext stdb) {
        this.stdb = stdb;
    }

    public StudentService(LessonDBContext ldb) {
        this.ldb = ldb;
    }

    public StudentService(StudentDBContext stdb, LessonDBContext ldb, StudentAttendanceDBContext sadb) {
        this.stdb = stdb;
        this.ldb = ldb;
        this.sadb = sadb;
    }

    public StudentService(StudentAttendanceDBContext sadb) {
        this.sadb = sadb;
    }

    public StudentService(StudentDBContext stdb, LessonDBContext ldb) {
        this.stdb = stdb;
        this.ldb = ldb;
    }

    public Student getStudentByAcc(Account acc) {
        return stdb.getByAccount(acc);
    }

    public ArrayList<Lesson> getCurrentWeekly(Student s) {
        DateUtil util = new DateUtil();
        Date monday = util.getMondayOfCurrentWeek();
        Date sunday = util.getSundayOfCurrentWeek();
        return ldb.getWeeklyTimeTable(s, monday, sunday);
    }

    public ArrayList<Lesson> getCurrentWeekly(Student s, Date startDate, Date endDate) {
        return ldb.getWeeklyTimeTable(s, startDate, endDate);
    }

    public ArrayList<Student> getListStudentByGroupId(int Group_id) {
        return stdb.getListByGroupId(Group_id);
    }

    public void updateStudentAttendance(int studentId, int lessonId, int status, String comment) {
        stdb.updateStudentAttendance(studentId, lessonId, status, comment);
    }

    public ArrayList<StudentAttendance> getListByLesson(int lid) {
        return sadb.getListByLesson(lid);
    }

    public ArrayList<StudentAttendance> getListByGroupAndStudent(int sid, int gid) {
        return sadb.getListByGroupAndStudent(sid, gid);
    }

    public Map<Student, ArrayList<StudentAttendance>> mapping(ArrayList<Student> students, ArrayList<StudentAttendance> saList, int groupId) {
        return sadb.mapping(students, saList, groupId);

    }

}
