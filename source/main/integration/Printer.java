package se.kth.iv1350.integration;

import java.util.Collection;
import java.text.MessageFormat;

import se.kth.iv1350.model.SaleInfoDTO;
import se.kth.iv1350.model.RecordedItem;
import se.kth.iv1350.model.ItemInfoDTO;
import se.kth.iv1350.util.Util;

public class Printer
{
	private final int RECIEPT_PADDING = 3;

	public Printer()
	{}

	public void printReceipt(SaleInfoDTO saleInfo)
	{
		Collection<RecordedItem> recordedItems = saleInfo.getCollectionOfRecordedItems();
		int longestNameLength = saleInfo.getLongestNameLengthOfAnyRecordedItem();
		int longestPriceBeforeDecimalPointLength = saleInfo.getLongestPriceBeforeDecimalPointLength();
		int longestPriceAfterDecimalPointLength = saleInfo.getLongestPriceAfterDecimalPointLength();
		int largestQuantity = saleInfo.getLargestQuantity();
		int lengthOfLargestQuantity = Util.lengthOfInt(largestQuantity);

		System.out.println("────────── Begin Reciept ──────────");

		for (RecordedItem recordedItem : recordedItems)
		{
			ItemInfoDTO info = recordedItem.getInfo();
			String name  = info.getName();
			double price = info.getPrice();
			int quantity = recordedItem.getQuantity();
			double combinedPrice = price * quantity;

			int lengthAwayFromLongestName = longestNameLength - name.length();
			int lengthAwayFromLongestPriceBeforeDecimal = longestPriceBeforeDecimalPointLength - Util.lengthOfDoubleBeforeDecimalPoint(price);
			int lengthAwayFromLongestPriceAfterDecimal = longestPriceAfterDecimalPointLength - Util.lengthOfDoubleAfterDecimalPoint(price);
			int lengthAwayFromLargestQuantity = lengthOfLargestQuantity - Util.lengthOfInt(quantity);

			System.out.print(name);
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestName));
			System.out.print(Util.charRepeat(' ', RECIEPT_PADDING));
			System.out.print(Util.charRepeat(' ', lengthAwayFromLargestQuantity));
			System.out.print(quantity);
			System.out.print(" x ");
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestPriceBeforeDecimal));
			System.out.print(price);
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestPriceAfterDecimal));
			System.out.print(Util.charRepeat(' ', RECIEPT_PADDING));
			System.out.print(combinedPrice);
			System.out.print("SEK");
			System.out.println();
		}

		System.out.println("──────────  End Reciept  ──────────");
	}
}
