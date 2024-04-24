import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.util.Util;

class UtilTest
{
	@Test
	public void charRepeatTest()
	{
		char character = 'x';
		int length = 100;
		String str = Util.charRepeat(character, length);

		boolean characterTest = true;
		for (char characterToCompare : str.toCharArray())
		{
			if (character != characterToCompare)
			{
				characterTest = false;
				break;
			}
		}

		assertEquals(length, str.length());
	}

	@Test
	public void lengthOfIntTest()
	{
		assertEquals(3, Util.lengthOfInt( 100));
		assertEquals(4, Util.lengthOfInt(-100));
	}

	@Test
	public void lengthOfDoubleBeforeDecimal()
	{
		assertEquals(1, Util.lengthOfDoubleBeforeDecimal(0));
		assertEquals(3, Util.lengthOfDoubleBeforeDecimal(123.456));
	}

	@Test
	public void lengthOfDoubleAfterDecimal()
	{
		assertEquals(1, Util.lengthOfDoubleAfterDecimal(0));
		assertEquals(6, Util.lengthOfDoubleAfterDecimal(0.123456));
	}
}
