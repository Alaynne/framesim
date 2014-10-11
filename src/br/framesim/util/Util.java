package br.framesim.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilities.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class Util {
		
	/**
	 * It formats the specified date.
	 * 
	 * @param date
	 * 
	 * @return the specified date in the format: dd/MM/yyyy.
	 */
	public static String formatDate(Date date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date.getTime());
			
	}
	
	/**
	 * It rounds off the specified value.
	 * Example: value = 0.389499
	 *          rounded value = 0.39
	 *          
	 * @param value
	 * 
	 * @throws ParseException, if a parse error occurs.
	 */
	public static double roundOffValue(double value) throws ParseException {
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		Double d = new Double(value);
		
		if (d == d.intValue()) {
			return d;
		}
		
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		
		return nf.parse(nf.format(value)).doubleValue();
		
	}
	
}
