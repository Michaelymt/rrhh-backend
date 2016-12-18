package pe.com.tss.runakuna.gateway.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by josediaz on 28/10/2016.
 */
public class ValidationUtils {
    private static final Logger log = LoggerFactory.getLogger(ValidationUtils.class);

    public static final String DEFAULT_DATE_FORMAT = "ddMMyyyy";

    public static boolean isValidDateFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            log.error("Error parsing date", ex);
        }
        return date != null;
    }
    
    public static boolean isWithinRange(Date fecha, Date fechaInicio, Date fechaFin) {
		   return !(fecha.before(fechaInicio) || fecha.after(fechaFin));
	}
    
    public static Date parseDate(String date) {
    	
    	final String inputFormat = "HH:mm";
    	SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
	    try {
	        return inputParser.parse(date);
	    } catch (java.text.ParseException e) {
	        return new Date(0);
	    }
	}
    
    public static boolean isWithinRangeHours(String horaSalidaSolicitado, String hour1, String hour2){

    	Date datehoraSalidaSolicitado;
    	Date dateCompareOne;
    	Date dateCompareTwo;
    	Calendar now = Calendar.getInstance();

	    int hour = now.get(Calendar.HOUR);
	    int minute = now.get(Calendar.MINUTE);
	    
	    datehoraSalidaSolicitado = parseDate(horaSalidaSolicitado);
//	    datehoraSalidaSolicitado = parseDate(hour + ":" + minute);
	    dateCompareOne = parseDate(hour1);
	    dateCompareTwo = parseDate(hour2);

	    return !(dateCompareOne.before(datehoraSalidaSolicitado) || dateCompareTwo.after(datehoraSalidaSolicitado));
    }
}
