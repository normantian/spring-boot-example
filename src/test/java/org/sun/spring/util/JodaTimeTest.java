package org.sun.spring.util;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

/**
 * Created by tianfei on 17/3/7.
 */
public class JodaTimeTest {
    @Test
    public void transfer(){
        java.util.Date juDate = new Date();
        DateTime dt = new DateTime(juDate);
        int year = dt.getYear();
        int month = dt.getMonthOfYear();
        int dateOfMonth = dt.getDayOfMonth();
        int dateOfWeek = dt.getDayOfWeek();
        int dateOfYear = dt.getDayOfYear();

        System.out.println("year="+year);
        System.out.println("month="+month);
        System.out.println("dateOfMonth="+dateOfMonth);
        System.out.println("dateOfWeek="+dateOfWeek);
        System.out.println("dateOfYear="+dateOfYear);

        String monthName = dt.monthOfYear().getAsShortText();
        boolean isLeapYear = dt.year().isLeap();
        DateTime rounded = dt.dayOfMonth().roundCeilingCopy();

        System.out.println(monthName);
        System.out.println(isLeapYear);
        System.out.println(rounded);
    }
}
