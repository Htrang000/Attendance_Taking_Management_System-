/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DateUtil {

    public Date getMondayOfCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new Date(calendar.getTimeInMillis());
    }

    public Date getSundayOfCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.DAY_OF_MONTH, 7); // Di chuyển đến cuối tuần
        return new Date(calendar.getTimeInMillis());
    }

    public static List<Date> getSQLDatesBetween(String start, String end) throws ParseException {
        List<Date> dates = new ArrayList<>();

        // Define a simple date format, e.g., "2023-01-01"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Parse the start and end dates
        java.util.Date parsedStartDate = dateFormat.parse(start);
        java.util.Date parsedEndDate = dateFormat.parse(end);

        // Set the calendar to start date
        Calendar cal = Calendar.getInstance();
        cal.setTime(parsedStartDate);

        // Loop through the dates and add them to the list
        while (!cal.getTime().after(parsedEndDate)) {
            dates.add(new Date(cal.getTime().getTime())); // Add the SQL date to the list
            cal.add(Calendar.DATE, 1); // Go to the next day
        }

        return dates;
    }

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        return new Date(currentDate.getTime());
    }
    
     public static Time getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTime = calendar.getTime();
        return new Time(currentTime.getTime());
    }
}
