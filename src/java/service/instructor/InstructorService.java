/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.instructor;

import dao.InstructorDBContext;
import dao.LessonDBContext;
import java.sql.Date;
import java.util.ArrayList;
import model.Account;
import model.Instructor;
import model.Lesson;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public class InstructorService {

    private InstructorDBContext idb;
    private LessonDBContext ldb;

    public InstructorService(InstructorDBContext idb) {
        this.idb = idb;
    }

    public InstructorService(LessonDBContext ldb) {
        this.ldb = ldb;
    }

    public InstructorService(InstructorDBContext idb, LessonDBContext ldb) {
        this.idb = idb;
        this.ldb = ldb;
    }

    public Instructor getInstructorByAcc(Account acc) {
        return idb.getByAccount(acc);
    }


    public ArrayList<Lesson> getCurrentWeekly(Instructor i, Date startDate, Date endDate) {
        return ldb.getWeeklyTimeTable(i, startDate, endDate);
    }
    
    public void updateLessonStatus(int status, int lessonId){
        ldb.updateStatus(1, lessonId);
    }
    
    public String getInstructorCode(int lid){
        return ldb.getInstructorCode(lid);
    }
    

}
