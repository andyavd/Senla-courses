package eu.senla.andyavd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	 private static final String DATE_PATTERN = "yyyy-MM-dd";
		private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		private static DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
		static {
			simpleDateFormat.applyPattern(DATE_PATTERN);
		}
		public static final Date dateFromString(String stringDate) {
			try {
				return simpleDateFormat.parse(stringDate);
			} catch (ParseException e) {
				return null;
			}
		}
		public static final String stringFromDate(Date date) {
			return dateFormat.format(date);
		}
		private DateFormatter() {
		}
}