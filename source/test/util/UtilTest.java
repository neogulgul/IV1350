package se.kth.iv1350.util;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import se.kth.iv1350.constants.Constants;

class UtilTest
{
	@Test
	public void biggestIntTest()
	{
		assertEquals(100, Util.biggestInt(-100, 50, 0, 50, 100), "biggest int is wrong");
	}

	@Test
	public void lengthOfIntTest()
	{
		assertEquals(3, Util.lengthOfInt(123), "length of int is wrong");
	}

	@Test
	public void lengthOfDoubleBeforeDecimalTest()
	{
		assertEquals(4, Util.lengthOfDoubleBeforeDecimal(1234.56789), "length of double before decimal is wrong");
	}

	@Test
	public void lengthOfDoubleAfterDecimalTest()
	{
		assertEquals(5, Util.lengthOfDoubleAfterDecimal(1234.56789), "length of double after decimal is wrong");
	}

	@Test
	public void roundDoubleTest()
	{
		assertEquals(1234.57, Util.roundDouble(1234.56789, 2), "rounding a double does not work as expected");
	}

	@Test
	public void stringReplaceAtIndexTest()
	{
		assertEquals("TEST", Util.stringReplaceAtIndex("BEST", 0, 'T'), "string replacement failed");
		assertEquals(":)", Util.stringReplaceAtIndex(":(", -1, ')'), "string replacement failed");
		assertEquals("51234", Util.stringReplaceAtIndex("01234", 5, '5'), "string replacement failed");
	}

	@Test
	public void replaceDecimalMarkOfDoubleTest()
	{
		assertEquals("9:99", Util.replaceDecimalMarkOfDouble(9.99, ':'), "decimal replacement failed");
	}

	@Test
	public void standardDoubleStringTest()
	{
		double d = 123.456;
		String standardized = Util.standardDoubleString(d);
		String rightSide = standardized.split("[" + Constants.DECIMAL_MARK + "]")[1];
		assertEquals(Constants.DECIMAL_PLACE_PRECISION, rightSide.length(), "conversion to standard double string failed");
	}

	@Test
	public void intToStringOfAtLeastTwoDigitsTest()
	{
		assertEquals("05", Util.intToStringOfAtLeastTwoDigits(5), "conversion of int to a string of at least two digits failed");
	}

	@Test
	public void asCurrencyTest()
	{
		double d = 9.99;
		String s = Util.standardDoubleString(d);
		assertEquals(s + " " + Constants.CURRENCY_CODE, Util.asCurrency(s), "appending the currency did not work as expected");
	}

	@Test
	public void charRepeatTest()
	{
		assertEquals("++++++++++", Util.charRepeat('+', 10), "char repeat did not work as expected");
	}

	@Test
	public void readFromFileTest()
	{
		try
		{
			String fileContent = Util.readFromFile("for_testing/textfiletest.txt");
			assertEquals("This is a test :o", fileContent, "reading from file did not work as expected");
		}
		catch (FileNotFoundException e)
		{
			fail("file was not found even though it should exist");
		}
	}
}
