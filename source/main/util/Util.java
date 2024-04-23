package se.kth.iv1350.util;

public class Util
{
	private static final String REGEX_DOT = "\\.";

	private Util()
	{}

	public static String charRepeat(char character, int length)
	{
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

	public static int lengthOfDoubleBeforeDecimalPoint(double d)
	{
		return doubleToString(d).split(REGEX_DOT)[0].length();
	}

	public static int lengthOfDoubleAfterDecimalPoint(double d)
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
};
