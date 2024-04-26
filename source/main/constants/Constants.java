package se.kth.iv1350.constants;

public class Constants
{
	/**
	 * The standard decimal precision used when rounding a double.
	 */
	public static final int DECIMAL_PLACE_PRECISION = 2;
	/**
	 * The character that replaces the default decimal '.' of any double converted to a string.
	 */
	public static final char DECIMAL_MARK = '.';
	/**
	 * The code that goes after a currency to indicate what type it is.
	 */
	public static final String CURRENCY_CODE = "SEK";
	/**
	 * Since the the '.' is a metacharacter in regex this is used instead when looking specifically for a '.' with regex.
	 */
	public static final String REGEX_DOT = "[.]";
	/**
	 * An arbitrary amount of spaces in regex.
	 */
	public static final String REGEX_SEQUENCE_OF_SPACES = "[ ]+";
	/**
	 * An array of 3 letter names representing all the months of a year.
	 */
	public static final String[] MONTHS = {
		"Jan", "Feb", "Mar", "Apr",
		"May", "Jun", "Jul", "Aug",
		"Sep", "Oct", "Nov", "Dec"
	};
}
