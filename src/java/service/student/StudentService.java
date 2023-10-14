/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.student;

import dao.LessonDBContext;
import dao.StudentDBContext;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import model.Account;
import model.Lesson;
import model.Student;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public class StudentService {

    private StudentDBContext stdb;
    private LessonDBContext ldb;
    public StudentService(StudentDBContext stdb) {
        this.stdb = stdb;
    }

    public StudentService(LessonDBContext ldb) {
        this.ldb = ldb;
    }

    public StudentService(StudentDBContext stdb, LessonDBContext ldb) {
        this.stdb = stdb;
        this.ldb = ldb;
    }
    
    
    

    public Student getStudentByAcc(Account acc) {
        return stdb.getByAccount(acc);
    }
    
    public ArrayList<Lesson> getCurrentWeekly(Student s){
        DateUtil util = new DateUtil();
        Date monday = util.getMondayOfCurrentWeek();
        Date sunday = util.getSundayOfCurrentWeek();
        return ldb.getWeeklyTimeTable(s, monday, sunday);
    }
    
    public ArrayList<Lesson> getCurrentWeekly(Student s, Date startDate, Date endDate){
        return ldb.getWeeklyTimeTable(s, startDate, endDate);
    }
    
    
    
     
}
