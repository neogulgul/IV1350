package se.kth.iv1350.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Util
{
	public static final String REGEX_DOT                = "\\.";
	public static final String REGEX_SEQUENCE_OF_SPACES = "[ ]+";

	private Util()
	{}

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
		return doubleToString(d).split(REGEX_DOT)[0].length();
	}

	public static int lengthOfDoubleAfterDecimal(double d)
	{
		return doubleToString(d).split(REGEX_DOT)[1].length();
	}

	public static String stringReplaceAtIndex(String stringToModify, int index, char replacement)
	{
		return stringToModify.substring(0, index) + replacement + stringToModify.substring(index + 1);
	}

	public static String doubleToString(double d)
	{
		String str = String.valueOf(d);
		if (str.indexOf('.') == -1)
		{
			str += ".0";
		}
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
