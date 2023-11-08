package cl.forum.hammer.actualizaTarea.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class DateHelper extends Object {

    /////////////////////
    // Class Variables //
    /////////////////////

    final  public String SHORT_DATE = "MM/dd"; // 5 Done

    final  public String LONG_DATE = "MM/dd/yy"; // 8

    final  public String SHORT_TIME = "hh:mm aa"; // 8

    final  public String LONG_TIME = "hh:mm:ss aa"; // 11

    final  public String SHORT_TIME_MILITARY = "HH:mm"; // 5 Done

    final  public String LONG_TIME_MILITARY = "HH:mm:ss"; // 8

    final  public String SHORT_DATE_TIME = SHORT_DATE + " " + SHORT_TIME;

    final  public String SHORT_DATE_TIME_MILITARY = SHORT_DATE + " " + SHORT_TIME_MILITARY;

    final  public String LONG_DATE_TIME = LONG_DATE + " " + LONG_TIME;

    final  public String LONG_DATE_TIME_MILITARY = LONG_DATE + " " + LONG_TIME_MILITARY;

    final  public String DATE_DELIMITER = "/";

    final  public String TIME_DELIMITER = ":";

    final  public String DATE_TIME_DELIMITER = " ";

    final  protected String MIDNIGHT = "0:00";

     private Date timezoneOffset;

    //////////////////
    // Constructors //
    //////////////////

    public DateHelper() {

        super();
    }

    ////////////////////
    // Public Methods //
    ////////////////////

     public Date parse(String text) throws ParseException {

        String target = text.trim();
        boolean isDate = target.indexOf(DATE_DELIMITER) >= 0;
        boolean isTime = target.indexOf(TIME_DELIMITER) >= 0;
        // Process based on the length
        StringTokenizer tokenizer = new StringTokenizer(target, DATE_TIME_DELIMITER);
        String format = "";
        if (isDate) {
            format = getDateFormat(tokenizer.nextToken());
            if (tokenizer.hasMoreTokens())
                format = format + DATE_TIME_DELIMITER;
        }
        if (isTime) {
            String timeString = tokenizer.nextToken();
            if (tokenizer.hasMoreTokens())
                format = format + getTimeFormat(timeString + " " + tokenizer.nextToken());
            else
                format = format + getMilitaryTimeFormat(timeString);
        }
        // Parse the date with the applicable format
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(text);
        } catch (java.text.ParseException e) {
            throw new ParseException("Unable to parse '" + target + "'", e.getErrorOffset());
        }
    }

     public Date parse(String text, String format) throws ParseException {

        DateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(text.trim());
        } catch (java.text.ParseException e) {
            throw new ParseException("Unable to parse '" + text + "'", e.getErrorOffset());
        }
    }

     public String format(Date date, String format) {

        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

     public static double getDaysBetween(Date first, Date second) {
         if(first ==null || second==null)
             return 0;
       /* double milliElapsed = second.getTime() - first.getTime();
        double daysElapsed = (milliElapsed / 24d / 3600d / 1000d);
        //return (Math.round(daysElapsed * 100d) / 100d);
         * 
         */
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(first);
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(second);
        calendar1.set(Calendar.HOUR,0);
        calendar1.set(Calendar.MINUTE,0);
        calendar1.set(Calendar.SECOND,0);
        calendar1.set(Calendar.MILLISECOND,0);
        calendar1.set(Calendar.AM_PM,0);
        calendar2.set(Calendar.HOUR,0);
        calendar2.set(Calendar.MINUTE,0);
        calendar2.set(Calendar.SECOND,0);
        calendar2.set(Calendar.MILLISECOND,0);
        calendar2.set(Calendar.AM_PM,0);
        return DateDiff(calendar2,calendar1);
        
    }
     
     
     private static int DateDiff(Calendar FechaF, Calendar FechaI){
         double tmpmilisec;
      
         tmpmilisec = FechaF.getTimeInMillis() - FechaI.getTimeInMillis();

         return (int) ((tmpmilisec / 1000 / 60 / 60 / 24) + 0.5);
      }
     
     

     public double getHoursBetween(Date first, Date second) {

        double milliElapsed = second.getTime() - first.getTime();
        double hoursElapsed = (milliElapsed / 3600F / 1000F);
        return (Math.round(hoursElapsed * 100F) / 100F);
    }

     final public Date add(Date first, Date second) throws ParseException {

        if (timezoneOffset == null)
            timezoneOffset = parse(MIDNIGHT);
        return new Date(first.getTime() + second.getTime() - timezoneOffset.getTime());
    }

     final public Date add(String first, String second) throws ParseException {

        return add(parse(first), parse(second));
    }

     final public Date add(Date first, double days, double hours, double minutes) {

        double additionalTime = (days * 24F * 3600F * 1000F) + (hours * 3600F * 1000F) + (minutes * 60 * 1000);
        return new Date(Math.round( first.getTime() + additionalTime));
    }

     public double getCombinedDays(Date first, Date second) {

        double milliCombined = first.getTime() + second.getTime();
        double daysCombined = (milliCombined / 24F / 3600F / 1000F);
        return (Math.round(daysCombined * 100F) / 100F);
    }

     public double getCombinedHours(Date first, Date second) {

        double milliCombined = first.getTime() + second.getTime();
        double hoursCombined = (milliCombined / 3600F / 1000F);
        return (Math.round(hoursCombined * 100F) / 100F);
    }

     public Date getNextDay(Date target) {

        Date next = new Date(target.getTime() + (24 * 3600 * 1000));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(next);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

     public boolean isSameDay(Date first, Date second) {

        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(first);
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(second);
        return ((calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) && (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)) && (calendar1.get(Calendar.DAY_OF_MONTH) == calendar2
                .get(Calendar.DAY_OF_MONTH)));
    }

    public static boolean isGreater(Date first, Date second) {

        Calendar cal= Calendar.getInstance();
        cal.setTime(first);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        first=cal.getTime();
        cal.setTime(second);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        second=cal.getTime();

        return first.after(second);

    }

    public boolean isSmaller(Date first, Date second) {

        Calendar cal= Calendar.getInstance();
        cal.setTime(first);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        first=cal.getTime();
        cal.setTime(second);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        second=cal.getTime();

        return first.before(second);

    }
    
    public static String dateToString(Date date){
    	if(date!=null){
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	    	return NNFormat(cal.get(Calendar.DAY_OF_MONTH))+"-"+ NNFormat(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
    	}
    	return null;    
    }
    
    
    private static String NNFormat(int number){
    	if(number<10)
    		return "0"+number;

    	return String.valueOf(number).toString();
    }


    public static  int compareTo(Date first, Date second) {

        Calendar cal= Calendar.getInstance();        
        if(first==null && second!=null){
        	return -1;
        }
        if(first!=null && second==null){
        	return 1;
        }
        if(first==null && second==null){
        	return 0;
        }        
        cal.setTime(first);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        cal.set(Calendar.AM_PM,0);
        first=cal.getTime();
        cal.setTime(second);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        cal.set(Calendar.AM_PM,0);
        second=cal.getTime();
        int res= first.compareTo(second);
        return res;
    }

    // This algorithm can undoubtedly be made MUCH better

     public boolean isConsecutiveDay(Date first, Date second) {

        return (Math.abs(getDaysBetween(stripTime(first), stripTime(second))) <= 1.0F);
    }

     public Date stripTime(Date dateTime) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /////////////////////
    // Private Methods //
    /////////////////////

     private String getDateFormat(String dateString) throws ParseException {

        int length = dateString.length();
        if (length == SHORT_DATE.length())
            return SHORT_DATE;
        else if (length == LONG_DATE.length())
            return LONG_DATE;
        else
            throw new ParseException("Invalid date format '" + dateString + "'", 0);
    }

     private String getTimeFormat(String timeString) throws ParseException {

        int length = timeString.length();
        if ((length == SHORT_TIME.length()) || (length == SHORT_TIME.length() - 1))
            return SHORT_TIME;
        else if ((length == LONG_TIME.length()) || (length == LONG_TIME.length() - 1))
            return LONG_TIME;
        throw new ParseException("Invalid time format '" + timeString + "'", 0);
    }

     private String getMilitaryTimeFormat(String timeString) throws ParseException {

        int length = timeString.length();
        if ((length == SHORT_TIME_MILITARY.length()) || (length == SHORT_TIME_MILITARY.length() - 1))
            return SHORT_TIME_MILITARY;
        else if ((length == LONG_TIME_MILITARY.length()) || (length == LONG_TIME_MILITARY.length() - 1))
            return LONG_TIME_MILITARY;
        throw new ParseException("Invalid military time format '" + timeString + "'", 0);
    }
     
     
     
     public static String mesLeer(int mes)
     {
     	switch (mes) {
     	case 1:  return "Enero";
     	case 2:  return "Febrero";
     	case 3:  return "Marzo";
     	case 4:  return "Abril";
     	case 5:  return "Mayo";
     	case 6:  return "Junio";
     	case 7:  return "Julio";
     	case 8:  return "Agosto";
     	case 9:  return "Septiembre";
     	case 10: return "Octubre";
     	case 11: return "Noviembre";		
 		default: return "Diciembre";
 		}
     }
     // Incrementa meses
     public static  Date addMonth(Date date, int month)
     { 

     	Calendar cal=Calendar.getInstance(); 
     	cal.setTime(date); 
     	cal.add( Calendar.MONTH, month ); 
     	return cal.getTime(); 
     } 

	 //SE AGREGA METODO PARA CALCULAR DIF. LSP
     public static int DateDiffPrimerVcto( Date FechaI , Date FechaF){
    	 long dif=0;
    	 dif=  (FechaF.getTime() - FechaI.getTime());// MILLSECS_PER_DAY;
    	 double dias = Math.floor(dif / (1000 * 60 * 60 * 24)); 
    	 return ((int)dias);
     }

}

