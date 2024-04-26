import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.constants.Constants;
import se.kth.iv1350.util.Util;

class UtilTest
{
	@Test
	public void biggestIntTest()
	{
		assertEquals(100, Util.biggestInt(-100, 50, 0, 50, 100));
	}

	@Test
	public void lengthOfIntTest()
	{
		assertEquals(3, Util.lengthOfInt(123));
	}

	@Test
	public void lengthOfDoubleBeforeDecimalTest()
	{
		assertEquals(4, Util.lengthOfDoubleBeforeDecimal(1234.56789));
	}

	@Test
	public void lengthOfDoubleAfterDecimalTest()
	{
		assertEquals(5, Util.lengthOfDoubleAfterDecimal(1234.56789));
	}

	@Test
	public void roundDoubleTest()
	{
		assertEquals(1234.57, Util.roundDouble(1234.56789, 2));
	}

	@Test
	public void stringReplaceAtIndexTest()
	{
		assertEquals("TEST", Util.stringReplaceAtIndex("BEST", 0, 'T'));
		assertEquals(":)", Util.stringReplaceAtIndex(":(", -1, ')'));
		assertEquals("51234", Util.stringReplaceAtIndex("01234", 5, '5'));
	}

	@Test
	public void replaceDecimalMarkOfDoubleTest()
	{
		assertEquals("9:99", Util.replaceDecimalMarkOfDouble(9.99, ':'));
	}

	@Test
	public void standardDoubleStringTest()
	{
		double d = 123.456;
		String standardized = Util.standardDoubleString(d);
		String rightSide = standardized.split("[" + Constants.DECIMAL_MARK + "]")[1];
		assertEquals(Constants.DECIMAL_PLACE_PRECISION, rightSide.length());
	}

	@Test
	public void intToStringOfAtleastTwoDigitsTest()
	{
		assertEquals("05", Util.intToStringOfAtleastTwoDigits(5));
	}

	@Test
	public void asCurrencyTest()
	{
		double d = 9.99;
		String s = Util.standardDoubleString(d);
		assertEquals(s + " " + Constants.CURRENCY_CODE, Util.asCurrency(s));
	}

	@Test
	public void charRepeatTest()
	{
		assertEquals("++++++++++", Util.charRepeat('+', 10));
	}

	@Test
	public void readFromFileTest()
	{
		assertEquals("This is a test :o", Util.readFromFile("for_testing/textfiletest.txt"));
	}
}
