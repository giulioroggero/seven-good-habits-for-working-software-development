package eu.miaplatform.dbsqlreader.util;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import oracle.sql.TIMESTAMP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlDateManager {
	public static final String DEFAULT_TIMEZONE = "UTC+0";
	public static final String ITALY_TIMEZONE = "Europe/Rome";
	public final static String DEFAULT_DATEFORMAT = "yyyy-MM-dd";
	public final static String DEFAULT_DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String PLUS_DATETIMEFORMAT = "dd-MM-yyyy HH:mm:ss";

	public final static String DEFAULT_DATEFORMAT_OUTPUT = "dd-MM-yy HH:mm:ss";

	public static final Integer DEFAULT_RANGE_MINUTES = 15;
	final static Logger logger = LoggerFactory.getLogger(SqlDateManager.class);

	public static String fromDatetimeToString(Date from) {
		if (from == null) return "";
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_DATETIMEFORMAT);
		dateFormatter.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
		return dateFormatter.format(from);
	}
	
	public static java.util.Date fromStringToDatetime(String from) {
		if (from == null) return null;
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_DATETIMEFORMAT);
		dateFormatter.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
		java.util.Date theDate = null;
		try {
			theDate = dateFormatter.parse(from);
		} catch (ParseException e) {
			logger.warn(e.getMessage());
		}
		return theDate;
	}

	public static String subtractMinutesToString(String value, Integer minutes) throws ParseException {
		if (value == null) return "";
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_DATETIMEFORMAT);
		dateFormatter.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));

		Calendar cal = Calendar.getInstance();

		if (value != null){
			cal.setTime(dateFormatter.parse(value));
		}
		cal.add(Calendar.MINUTE, -1 * minutes);

		return dateFormatter.format(cal.getTime());
	}

	public static String from_ddmmyyyy_to_yyyymmdd(String value) {
		
		if (value == null) return "";

		SimpleDateFormat datetimeFormatter = new SimpleDateFormat(PLUS_DATETIMEFORMAT);
		datetimeFormatter.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
		String res = value;

		if (value.length() > 0) {
			java.util.Date from = null;
			try {
				from = datetimeFormatter.parse(value);
			} catch (ParseException e) {
				logger.warn(e.getMessage() + " in url filter - use " + value + " instead!");
			}
			if (from != null) {
				SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_DATEFORMAT);
				dateFormatter.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
				res = dateFormatter.format(from);
			}
		}
		return res;
	}
	
	public static String fillWith0TimeIfneeded(String value) {
		
		if (value == null) return "";

		SimpleDateFormat datetimeFormatter = getFormatter(value);
		String res = value;

		if (value.length() > 0) {
			java.util.Date from = null;
			try {
				from = datetimeFormatter.parse(value);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
			}
			if (from != null) {
				SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_DATETIMEFORMAT);
				dateFormatter.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
				res = dateFormatter.format(from);
			}
		}
		return res;
	}
	
	private static SimpleDateFormat getFormatter(String value){
		
		SimpleDateFormat datetimeFormatter = null;
		// if is just date
		if (value == null || value.length() == 10){
			datetimeFormatter = new SimpleDateFormat(DEFAULT_DATEFORMAT);
		}else{
			datetimeFormatter = new SimpleDateFormat(DEFAULT_DATETIMEFORMAT);
		}
		datetimeFormatter.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
		return datetimeFormatter;
	}
	
	public static SimpleDateFormat getFormatterFromFormatString(String format){
		String sourceDateFormat = (format != null && format.length() > 0) ? format : SqlDateManager.DEFAULT_DATEFORMAT;
		SimpleDateFormat dateFormatter = new SimpleDateFormat(sourceDateFormat);
		dateFormatter.setTimeZone(TimeZone.getTimeZone(SqlDateManager.DEFAULT_TIMEZONE));
		return dateFormatter;
	}
	
	public static long getNowTimeT(){
		ZoneId z = ZoneId.of( ITALY_TIMEZONE );
		ZonedDateTime zdt = ZonedDateTime.of(LocalDate.now(z), LocalTime.now(z), ZoneId.of("Europe/Rome"));
		return zdt.toInstant().getEpochSecond();
	}

	public static String fromTimestampToString(Timestamp timestamp) {
		if (timestamp == null){
			return "";
		}
		DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATEFORMAT_OUTPUT);
		return dateFormat.format(timestamp);
	}

    public static Object fromTimestampToString(TIMESTAMP timestamp) {
        if (timestamp == null){
            return "";
        }
		try {
			return fromTimestampToString(timestamp.timestampValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
