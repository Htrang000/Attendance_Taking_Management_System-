/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Admin
 */
public class DateUtil {

    public  Date getMondayOfCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new Date(calendar.getTimeInMillis());
    }

    public  Date getSundayOfCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.DAY_OF_MONTH, 7); // Di chuyển đến cuối tuần
        return new Date(calendar.getTimeInMillis());
    }

}
