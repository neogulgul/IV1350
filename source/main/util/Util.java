package se.kth.iv1350.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import se.kth.iv1350.constants.Constants;

public class Util
{
	private Util()
	{}

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

	public static String timeInt(int i)
	{
		String s = String.valueOf(i);
		if (s.length() == 1)
		{
			s = "0" + s;
		}
		return s;
	}

	public static String standardizeDouble(double doubleToStandardize)
	{
		double rounded = roundDouble(doubleToStandardize, Constants.DECIMAL_PLACE_PRECISION);
		return replaceDecimalOfDouble(rounded, Constants.DECIMAL_MARK);
	}

	public static double roundDouble(double numberToRound, int decimalPlace)
	{
		double scaleFactor = Math.pow(10, decimalPlace);
		return Math.round(numberToRound * scaleFactor) / scaleFactor;
	}

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

	public static int lengthOfInt(int i)
	{
		return String.valueOf(i).length();
	}

	public static int lengthOfDoubleBeforeDecimal(double d)
	{
		return String.valueOf(d).split(Constants.REGEX_DOT)[0].length();
	}

	public static int lengthOfDoubleAfterDecimal(double d)
	{
		return String.valueOf(d).split(Constants.REGEX_DOT)[1].length();
	}

	public static String stringReplaceAtIndex(String stringToModify, int index, char replacement)
	{
		return stringToModify.substring(0, index) + replacement + stringToModify.substring(index + 1);
	}

	public static String replaceDecimalOfDouble(double d, char replacement)
	{
		String str = String.valueOf(d);

		str = stringReplaceAtIndex(str, str.indexOf('.'), replacement);

		return str;
	}

	public static String readFromFile(String filepath)
	{
		try
		{
			String contents = "";
			File file = new File(filepath);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine())
			{
				contents += scanner.nextLine() + "\n";
			}
			return contents;
		}
		catch (FileNotFoundException e)
		{
			return "";
		}
	}
};
