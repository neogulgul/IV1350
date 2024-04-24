package se.kth.iv1350.integration;

import java.util.Collection;
import java.text.MessageFormat;

import se.kth.iv1350.model.*;
import se.kth.iv1350.util.Util;

public class Printer
{
	private final int    RECIEPT_BORDER_PADDING          = 1;
	private final int    RECIEPT_SECTION_PADDING         = 3;
	private final int    RECIEPT_DECIMAL_POINTS_PER_LINE = 2;
	private final String RECIEPT_QUANTITY_DIVIDER        = " x ";
	private final String RECIEPT_CURRENCY_TOKEN          = " SEK";
	private final String RECIEPT_BEGIN_RECIEPT_MESSAGE   = "Here is your reciept!";
	private final String RECIEPT_END_RECIEPT_MESSAGE     = "Thanks for your purchase!";

	public Printer()
	{}

	public void printReceipt(SaleInfoDTO saleInfo)
	{
		Collection<RecordedItem> recordedItems = saleInfo.getCollectionOfRecordedItems();
		SaleStringLengthInfoDTO stringLengthInfo = saleInfo.getStringLengthInfo();

		int longestNameLength                       = stringLengthInfo.getLengthOfLongestName();
		int longestQuantityLength                   = stringLengthInfo.getLengthOfLongestQuantity();
		int longestPriceBeforeDecimalLength         = stringLengthInfo.getLengthOfLongestPriceBeforeDecimal();
		int longestPriceAfterDecimalLength          = stringLengthInfo.getLengthOfLongestPriceAfterDecimal();
		int longestCombinedPriceBeforeDecimalLength = stringLengthInfo.getLengthOfLongestCombinedPriceBeforeDecimal();
		int longestCombinedPriceAfterDecimalLength  = stringLengthInfo.getLengthOfLongestCombinedPriceAfterDecimal();

		int totalLengthInsideReciept = longestNameLength
		                             + longestQuantityLength
		                             + longestPriceBeforeDecimalLength
		                             + longestPriceAfterDecimalLength
		                             + longestCombinedPriceBeforeDecimalLength
		                             + longestCombinedPriceAfterDecimalLength
		                             + RECIEPT_SECTION_PADDING * 2
		                             + RECIEPT_QUANTITY_DIVIDER.length()
		                             + RECIEPT_CURRENCY_TOKEN.length()
		                             + RECIEPT_DECIMAL_POINTS_PER_LINE;

		totalLengthInsideReciept = Math.max(totalLengthInsideReciept, RECIEPT_BEGIN_RECIEPT_MESSAGE.length());
		totalLengthInsideReciept = Math.max(totalLengthInsideReciept, RECIEPT_END_RECIEPT_MESSAGE.length());

		totalLengthInsideReciept += RECIEPT_BORDER_PADDING * 2;

		System.out.print("┌");
		System.out.print(Util.charRepeat('─', totalLengthInsideReciept));
		System.out.print("┐");
		System.out.println();

		System.out.print("│");
		System.out.print(Util.charRepeat(' ', RECIEPT_BORDER_PADDING));
		System.out.print(RECIEPT_BEGIN_RECIEPT_MESSAGE);
		System.out.print(Util.charRepeat(' ', totalLengthInsideReciept - RECIEPT_BEGIN_RECIEPT_MESSAGE.length() - RECIEPT_BORDER_PADDING));
		System.out.print("│");
		System.out.println();

		System.out.print("├");
		System.out.print(Util.charRepeat('─', totalLengthInsideReciept));
		System.out.print("┤");
		System.out.println();

		for (RecordedItem currentItem : recordedItems)
		{
			ItemInfoDTO currentInfo = currentItem.getInfo();

			String currentName          = currentInfo.getName();
			int currentQuantity         = currentItem.getQuantity();
			double currentPrice         = currentInfo.getPrice();
			double currentCombinedPrice = currentItem.calculateCombinedPrice();

			int lengthAwayFromLongestName                       = longestNameLength                       - currentName.length();
			int lengthAwayFromLongestQuantity                   = longestQuantityLength                   - Util.lengthOfInt(currentQuantity);
			int lengthAwayFromLongestPriceBeforeDecimal         = longestPriceBeforeDecimalLength         - Util.lengthOfDoubleBeforeDecimal(currentPrice);
			int lengthAwayFromLongestPriceAfterDecimal          = longestPriceAfterDecimalLength          - Util.lengthOfDoubleAfterDecimal(currentPrice);
			int lengthAwayFromLongestCombinedPriceBeforeDecimal = longestCombinedPriceBeforeDecimalLength - Util.lengthOfDoubleBeforeDecimal(currentCombinedPrice);
			int lengthAwayFromLongestCombinedPriceAfterDecimal  = longestCombinedPriceAfterDecimalLength  - Util.lengthOfDoubleAfterDecimal(currentCombinedPrice);

			System.out.print("│");
			System.out.print(Util.charRepeat(' ', RECIEPT_BORDER_PADDING));
			System.out.print(currentName);
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestName));
			System.out.print(Util.charRepeat(' ', RECIEPT_SECTION_PADDING));
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestQuantity));
			System.out.print(currentQuantity);
			System.out.print(RECIEPT_QUANTITY_DIVIDER);
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestPriceBeforeDecimal));
			System.out.print(currentPrice);
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestPriceAfterDecimal));
			System.out.print(Util.charRepeat(' ', RECIEPT_SECTION_PADDING));
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestCombinedPriceBeforeDecimal));
			System.out.print(currentCombinedPrice);
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestCombinedPriceAfterDecimal));
			System.out.print(RECIEPT_CURRENCY_TOKEN);
			System.out.print(Util.charRepeat(' ', RECIEPT_BORDER_PADDING));
			System.out.print("│");
			System.out.println();
		}

		System.out.print("├");
		System.out.print(Util.charRepeat('─', totalLengthInsideReciept));
		System.out.print("┤");
		System.out.println();

		System.out.print("│");
		System.out.print(Util.charRepeat(' ', RECIEPT_BORDER_PADDING));
		System.out.print(RECIEPT_END_RECIEPT_MESSAGE);
		System.out.print(Util.charRepeat(' ', totalLengthInsideReciept - RECIEPT_END_RECIEPT_MESSAGE.length() - RECIEPT_BORDER_PADDING));
		System.out.print("│");
		System.out.println();

		System.out.print("└");
		System.out.print(Util.charRepeat('─', totalLengthInsideReciept));
		System.out.print("┘");
		System.out.println();
	}
}
