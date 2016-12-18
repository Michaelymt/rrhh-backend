package pe.com.tss.runakuna.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by josediaz on 28/10/2016.
 */
public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static String fmtDt(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    public static String fmtFull(Date date) {
        if (date == null)
            return "";
        return new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").format(date);
    }
    
    public static Date formatoFechaArchivoMarcacion(String date) {
        try {
        	if(date == null || date.equals("")){
        		return null;
        	}
        	
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            LOGGER.error("Error parseando fecha", e);
        }
        return null;
    }

    public static Date parse(SimpleDateFormat sdf, String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            LOGGER.error("Error parseando fecha", e);
        }
        return null;
    }

    public static Date truncDay(Date date) {
        if (date == null)
            return null;
        Calendar cal = buildCal(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private static Calendar buildCal(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String getPeriod(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String format = sdf.format(date);
        String month = format.substring(0, 2);
        String day = format.substring(3, 5);
        String year = format.substring(6, 10);
        return year + month + day;
    }



    public static Date getCalculateDaysDate(Date dateCal, int days) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dateCal);

        calendar.add(Calendar.DAY_OF_MONTH, days);

        return calendar.getTime();

    }


    public static Date getCleanTimeDate(Date dateCal) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dateCal);

        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTime();

    }


    public static Date addDays(Date bidDate, Integer addDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(bidDate);
        cal.add(Calendar.DAY_OF_YEAR, addDays);
        return cal.getTime();
    }

    public static Date addHours(Date date, int addHours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, addHours);
        return cal.getTime();
    }

    
    public static Date addMinutes(Date date, int addMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, addMinutes);
        return cal.getTime();
    }

    public static Date truncMonth(Date bidDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(bidDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date addMonths(Date starCurMonth, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(starCurMonth);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }


    public static int getDayOfMonth(Date bidDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(bidDate);
        return cal.get(Calendar.DAY_OF_MONTH);
    }


    public static Date getFirstMonday(int year, int month) {
        Calendar cacheCalendar = Calendar.getInstance();

        cacheCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cacheCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        cacheCalendar.set(Calendar.MONTH, month);
        cacheCalendar.set(Calendar.YEAR, year);
        Date time = cacheCalendar.getTime();
        time = truncDay(time);
        return time;
    }

    public static Date getFirstMondayOnMonth(Date pivotDate) {
        Calendar cacheCalendar = Calendar.getInstance();
        cacheCalendar.setTime(pivotDate);
        int month = cacheCalendar.get(Calendar.MONTH);
        int year = cacheCalendar.get(Calendar.YEAR);
        Date retVal = getFirstMonday(year, month);
        return retVal;
    }

}
