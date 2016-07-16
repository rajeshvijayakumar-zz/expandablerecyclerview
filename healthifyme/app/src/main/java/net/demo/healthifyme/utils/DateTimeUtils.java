package net.demo.healthifyme.utils;

import net.demo.healthifyme.constants.HMConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by rajesh5kumar on 14/7/16.
 */

public class DateTimeUtils {

    public static Calendar initCalendar(String format, String date) {
        DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cal;
    }

    public static String getMonthName(String date) {
        Calendar cal = initCalendar(HMConstants.FORMAT_YYYY_MM_DD, date);

        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }

    public static int getDate(String date) {
        Calendar cal = initCalendar(HMConstants.FORMAT_YYYY_MM_DD, date);

        return cal.get(Calendar.DATE);
    }

    public static String getDay(String date) {
        Calendar cal = initCalendar(HMConstants.FORMAT_YYYY_MM_DD, date);

        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
    }

    public static String getFormattedTime(String dateTime) {

        Calendar cal = initCalendar(HMConstants.FORMAT_YYYY_MM_DD_HH_MM_SS, dateTime);

        int hourValue = cal.get(Calendar.HOUR);
        int minuteValue = cal.get(Calendar.MINUTE);

        String hour = (hourValue < 10) ? "0" + String.valueOf(hourValue) :
                String.valueOf(hourValue);
        String minute = (minuteValue < 10) ? "0" + String.valueOf(minuteValue) :
                String.valueOf(minuteValue);
        return hour + ":" + minute + " " +
                cal.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.ENGLISH);
    }
}
