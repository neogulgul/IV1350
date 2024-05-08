package se.kth.iv1350.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import se.kth.iv1350.constants.Constants;

/**
 * {@link Util} contains utility functions.
 */
public class Util
{
	private Util() {}

	/**
	 * Finds the biggest <code>int</code> from a variable number of integers.
	 * For example, biggestInt(-100, 50, 0, 50, 100) should return 100;
	 *
	 * @param intsToCompare A variable number of integers to compare with each other.
	 *
	 * @return The biggest <code>int</code> from the integers it compared.
	 */
	public static int biggestInt(int... intsToCompare)
	{
		if (intsToCompare.length == 0) return 0;

		int largestInt = intsToCompare[0];

		for (int index = 1; index < intsToCompare.length; index++)
		{
			int currentInt = intsToCompare[index];
			largestInt = Math.max(largestInt, currentInt);
		}

		return largestInt;
	}

	/**
	 * Finds the lengnth of an <code>int</code>.
	 * For example, lengthOfInt(123) should return 3.
	 *
	 * @param i The <code>int</code> to find the length of.
	 *
	 * @return The length of the integer passed in.
	 */
	public static int lengthOfInt(int i)
	{
		return String.valueOf(i).length();
	}

	/**
	 * Finds the number of digits before a <code>double</code>s decimal mark.
	 * For example, lengthOfDoubleBeforeDecimal(1234.56789) should return 4.
	 *
	 * @param d The <code>double</code> to check.
	 *
	 * @return Number of digits before a <code>double</code>s decimal mark.
	 */
	public static int lengthOfDoubleBeforeDecimal(double d)
	{
		return String.valueOf(d).split(Constants.REGEX_DOT)[0].length();
	}

	/**
	 * Finds the number of digits after a <code>double</code>s decimal mark.
	 * For example, lengthOfDoubleAfterDecimal(1234.56789) should return 5.
	 *
	 * @param d The <code>double</code> to check.
	 *
	 * @return Number of digits after a <code>double</code>s decimal mark.
	 */
	public static int lengthOfDoubleAfterDecimal(double d)
	{
		return String.valueOf(d).split(Constants.REGEX_DOT)[1].length();
	}

	/**
	 * Rounds <code>numberToRound</code> to the nearest specified <code>decimalPlace</code>.
	 * For example, roundDouble(1234.56789, 2) should return 1234.57.
	 *
	 * @param numberToRound The number to be rounded.
	 * @param decimalPlace  The decimal place precision for the number to be rounded.
	 *
	 * @return The rounded <code>numberToRound</code>.
	 */
	public static double roundDouble(double numberToRound, int decimalPlace)
	{
		double scaleFactor = Math.pow(10, decimalPlace);
		return Math.round(numberToRound * scaleFactor) / scaleFactor;
	}

	/**
	 * Replaces a <code>char</code> of a string at a specified index with a specified replacement <code>char</code>.
	 * If the index is out of range it loops around.
	 * For example, stringReplaceAtIndex("BEST", 0, 'T') should return "TEST".
	 * Another example, stringReplacedAtIndex(":(", -1, ')') should return ":)".
	 * Another example, stringReplaceAtIndex("01234", 5, '5') should return "51234".
	 *
	 * @param stringToModify The string to modify.
	 * @param index          The index of the <code>char</code> to be replaced.
	 * @param replacement    The <code>char</code> to take over at the given index.
	 *
	 * @return The modified string.
	 */
	public static String stringReplaceAtIndex(String stringToModify, int index, char replacement)
	{
		index %= stringToModify.length();

		if (index < 0)
		{
			index += stringToModify.length();
		}
		else if (index >= stringToModify.length())
		{
			index -= stringToModify.length();
		}

		return stringToModify.substring(0, index) + replacement + stringToModify.substring(index + 1);
	}

	/**
	 * Replaces the decimal mark of a <code>double</code>.
	 * For example, replaceDecimalMarkOfDouble(9.99, ':') should return "9:99";
	 *
	 * @param doubleToModify The <code>double</code> to modify.
	 * @param replacement    The <code>char</code> to which will replaced the <code>doubleToModify</code>s decimal mark.
	 *
	 * @return Returns the <code>doubleToModify</code> as a <code>String</code> with its decimal mark replaced with <code>replacement</code>.
	 */
	public static String replaceDecimalMarkOfDouble(double doubleToModify, char replacement)
	{
		String doubleString = String.valueOf(doubleToModify);
		doubleString = stringReplaceAtIndex(doubleString, doubleString.indexOf('.'), replacement);
		return doubleString;
	}

	/**
	 * Converts a <code>double</code> to a <code>String</code> with a standard decimal place precision and decimal mark
	 * specified in {@link se.kth.iv1350.constants.Constants#DECIMAL_PLACE_PRECISION} and {@link se.kth.iv1350.constants.Constants#DECIMAL_MARK} respectively.
	 *
	 * @param doubleToStandardize The double to standardize.
	 *
	 * @return <code>String</code> representing the standardized double.
	 */
	public static String standardDoubleString(double doubleToStandardize)
	{
		double rounded = roundDouble(doubleToStandardize, Constants.DECIMAL_PLACE_PRECISION);
		String corrected = replaceDecimalMarkOfDouble(rounded, Constants.DECIMAL_MARK);

		int lengthAfterDecimal = lengthOfDoubleAfterDecimal(rounded);
		int zerosToAppend = Constants.DECIMAL_PLACE_PRECISION - lengthAfterDecimal;
		for (int i = 0; i < zerosToAppend; i++)
		{
			corrected += '0';
		}

		return corrected;
	}

	/**
	 * Appends an additional zero to the front of an <code>int</code> if it is only one digit by itself.
	 * For example, intToStringOfAtLeastTwoDigits(5) should return "05".
	 * Useful when representing time like 07:05.
	 *
	 * @param i The <code>int</code> in question.
	 *
	 * @return The integer in question, perhaps with an appended zero in the front.
	 */
	public static String intToStringOfAtLeastTwoDigits(int i)
	{
		String s = String.valueOf(i);
		if (s.length() == 1)
		{
			s = "0" + s;
		}
		return s;
	}

	/**
	 * Appends the standard currency code to a given <code>String</code>.
	 *
	 * @param str <code>String</code> to append to.
	 *
	 * @return <code>str</code> with {@link Constants#CURRENCY_CODE} appended.
	 */
	public static String asCurrency(String str)
	{
		return str + " " + Constants.CURRENCY_CODE;
	}

	/**
	 * Creates a <code>String</code> of repeating <code>char</code>s.
	 *
	 * @param character The <code>char</code> to repeat itself.
	 * @param length    The number of times the <code>char</code> will repeat.
	 *
	 * @return A repeating sequence of <code>char</code>s as a <code>String</code> from a given <code>character</code> and <code>length</code>.
	 */
	public static String charRepeat(char character, int length)
	{
		if (length <= 0) return "";

		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++)
		{
			builder.append(character);
		}
		return builder.toString();
	}

	/**
	 * Reads the contents of a text file from a given <code>filepath</code>.
	 *
	 * @param filepath The path to the text file to read from.
	 *
	 * @return <code>string</code> containing the contents of the textfile.
	 *
	 * @throws FileNotFoundException If the file is not found.
	 */
	public static String readFromFile(String filepath)
	throws FileNotFoundException
	{
		StringBuilder contents = new StringBuilder();
		File file = new File(filepath);
		Scanner scanner = new Scanner(file);

		if (scanner.hasNextLine())
		{
			while (scanner.hasNextLine())
			{
				contents.append(scanner.nextLine() + "\n");
			}

			contents.deleteCharAt(contents.length() - 1);
		}

		return contents.toString();
	}
};
