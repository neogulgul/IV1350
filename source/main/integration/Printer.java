package se.kth.iv1350.integration;

import java.util.Map;
import java.util.HashMap;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Collection;

import se.kth.iv1350.model.*;
import se.kth.iv1350.util.Util;
import se.kth.iv1350.constants.Constants;

public class Printer
{
	private class TwoPartRow
	{
		private String leftSide;
		private String rightSide;

		TwoPartRow(String leftSide, String rightSide)
		{
			this.leftSide  = leftSide;
			this.rightSide = rightSide;
		}

		public String getLeftSide()
		{
			return leftSide;
		}

		public String getRightSide()
		{
			return rightSide;
		}

		public int calculateCombinedLength()
		{
			return leftSide.length() + rightSide.length();
		}
	}

	private final int    RECEIPT_BORDER_PADDING              = 1;
	private final int    RECEIPT_SECTION_PADDING             = 3;
	private final int    RECEIPT_DECIMAL_POINTS_PER_ITEM_ROW = 2;
	private final String RECEIPT_QUANTITY_DIVIDER            = " x ";
	private final String RECEIPT_CURRENCY_TOKEN              = Util.asCurrency("");
	private final String RECEIPT_BEGIN_RECEIPT_MESSAGE       = "This is your receipt!";
	private final String RECEIPT_END_RECEIPT_MESSAGE         = "Thanks for your purchase!";

	private int longestNameLength;
	private int longestQuantityLength;
	private int longestCostBeforeDecimalLength;
	private int longestCostAfterDecimalLength;
	private int longestCombinedCostBeforeDecimalLength;
	private int longestCombinedCostAfterDecimalLength;

	private String timeOfSaleString;

	private int lengthOfTheLongestRecordedItemRow;

	private int totalLengthInsideReceipt;

	private boolean longestRowInReceiptIsOfAnItem;

	private TwoPartRow timeOfSaleRow;

	private Map<String, TwoPartRow> twoPartRows;
	private int lengthOfLongestTwoPartRow;

	public Printer()
	{}

	private void updateLongestLengthsInfo(SaleStringLengthInfoDTO stringLengthInfo)
	{
		longestNameLength                      = stringLengthInfo.getLengthOfLongestName();
		longestQuantityLength                  = stringLengthInfo.getLengthOfLongestQuantity();
		longestCostBeforeDecimalLength         = stringLengthInfo.getLengthOfLongestCostBeforeDecimal();
		longestCostAfterDecimalLength          = stringLengthInfo.getLengthOfLongestCostAfterDecimal();
		longestCombinedCostBeforeDecimalLength = stringLengthInfo.getLengthOfLongestCombinedCostBeforeDecimal();
		longestCombinedCostAfterDecimalLength  = stringLengthInfo.getLengthOfLongestCombinedCostAfterDecimal();
	}

	private int calculateLengthOfItemRowInReceipt(
		int nameLength,
		int quantityLength,
		int costBeforeDecimalLength,
		int costAfterDecimalLength,
		int combinedCostBeforeDecimalLength,
		int combinedCostAfterDecimalLength
	)
	{
		return nameLength
		     + quantityLength
		     + costBeforeDecimalLength
		     + costAfterDecimalLength
		     + combinedCostBeforeDecimalLength
		     + combinedCostAfterDecimalLength
		     + RECEIPT_BORDER_PADDING * 2
		     + RECEIPT_SECTION_PADDING * 2
		     + RECEIPT_QUANTITY_DIVIDER.length()
		     + RECEIPT_CURRENCY_TOKEN.length()
		     + RECEIPT_DECIMAL_POINTS_PER_ITEM_ROW;
	}

	private void updateTotalLengthInsideReceipt()
	{
		lengthOfTheLongestRecordedItemRow = calculateLengthOfItemRowInReceipt(
			longestNameLength,
			longestQuantityLength,
			longestCostBeforeDecimalLength,
			longestCostAfterDecimalLength,
			longestCombinedCostBeforeDecimalLength,
			longestCombinedCostAfterDecimalLength
		);

		totalLengthInsideReceipt = Util.biggestInt(
			RECEIPT_BEGIN_RECEIPT_MESSAGE.length()  + RECEIPT_BORDER_PADDING * 2,
			RECEIPT_END_RECEIPT_MESSAGE.length()    + RECEIPT_BORDER_PADDING * 2,
			lengthOfLongestTwoPartRow               + RECEIPT_BORDER_PADDING * 2,
			lengthOfTheLongestRecordedItemRow
		);

		longestRowInReceiptIsOfAnItem = lengthOfTheLongestRecordedItemRow == totalLengthInsideReceipt;
	}

	private void updateLengthOfLongestTwoPartRow()
	{
		TwoPartRow[] rowArray = twoPartRows.values().toArray(new TwoPartRow[0]);

		if (rowArray.length == 0)
		{
			lengthOfLongestTwoPartRow = 0;
		}
		else
		{
			lengthOfLongestTwoPartRow = rowArray[0].calculateCombinedLength();

			for (int index = 1; index < rowArray.length; index++)
			{
				int lengthOfCurrentRow = rowArray[index].calculateCombinedLength();
				lengthOfLongestTwoPartRow = Math.max(lengthOfCurrentRow, lengthOfLongestTwoPartRow);
			}
		}
	}

	private void updateTwoPartRows(SaleInfoDTO saleInfo)
	{
		twoPartRows = new HashMap<>();

		String totalCostString = Util.standardDoubleString(saleInfo.getCostOfEntireSale());
		String  totalVatString = Util.standardDoubleString(saleInfo.getVatOfEntireSale());
		String   paymentString = Util.standardDoubleString(saleInfo.getPaymentFromCustomer());
		String    changeString = Util.standardDoubleString(saleInfo.getChangeForCustomer());

		twoPartRows.put("timeOfSale", new TwoPartRow("Time of sale: ", timeOfSaleString));

		twoPartRows.put("totalCost", new TwoPartRow("Total cost (incl. VAT): ", totalCostString + RECEIPT_CURRENCY_TOKEN));
		twoPartRows.put("totalVat", new TwoPartRow("Total cost of VAT: ", totalVatString + RECEIPT_CURRENCY_TOKEN));

		twoPartRows.put("payment", new TwoPartRow("Payment: ", paymentString + RECEIPT_CURRENCY_TOKEN));
		twoPartRows.put("change", new TwoPartRow("Change: ", changeString + RECEIPT_CURRENCY_TOKEN));

		updateLengthOfLongestTwoPartRow();
	}

	private void updateTimeOfSaleString(Calendar timeOfSale)
	{
		String month = Constants.MONTHS[timeOfSale.get(Calendar.MONTH)];
		int day      = timeOfSale.get(Calendar.DAY_OF_MONTH);
		int year     = timeOfSale.get(Calendar.YEAR);
		int hour     = timeOfSale.get(Calendar.HOUR_OF_DAY);
		int minute   = timeOfSale.get(Calendar.MINUTE);

		timeOfSaleString = String.format("%s %d %d %s:%s", month, day, year, Util.timeInt(hour), Util.timeInt(minute));
	}

	private void printReceiptTop()
	{
		System.out.print("┌");
		System.out.print(Util.charRepeat('─', totalLengthInsideReceipt));
		System.out.print("┐");
		System.out.println();

		System.out.print("│");
		System.out.print(Util.charRepeat(' ', RECEIPT_BORDER_PADDING));
		System.out.print(RECEIPT_BEGIN_RECEIPT_MESSAGE);
		System.out.print(Util.charRepeat(' ', totalLengthInsideReceipt - RECEIPT_BEGIN_RECEIPT_MESSAGE.length() - RECEIPT_BORDER_PADDING));
		System.out.print("│");
		System.out.println();

		System.out.print("├");
		System.out.print(Util.charRepeat('─', totalLengthInsideReceipt));
		System.out.print("┤");
		System.out.println();
	}

	private void printReceiptTwoPartRow(TwoPartRow row)
	{
		System.out.print("│");
		System.out.print(Util.charRepeat(' ', RECEIPT_BORDER_PADDING));

		System.out.print(row.getLeftSide());
		System.out.print(Util.charRepeat(' ', totalLengthInsideReceipt - RECEIPT_BORDER_PADDING * 2 - row.calculateCombinedLength()));
		System.out.print(row.getRightSide());

		System.out.print(Util.charRepeat(' ', RECEIPT_BORDER_PADDING));
		System.out.print("│");

		System.out.println();
	}

	private void printReceiptEmptyRow()
	{
		System.out.print("│");
		System.out.print(Util.charRepeat(' ', totalLengthInsideReceipt));
		System.out.print("│");
		System.out.println();
	}

	private void printReceiptItems(Collection<RecordedItem> recordedItems)
	{
		for (RecordedItem currentItem : recordedItems)
		{
			ItemInfoDTO currentInfo = currentItem.getInfo();

			String currentName         = currentInfo.getName();
			int currentQuantity        = currentItem.getQuantity();
			double currentCost         = Util.roundDouble(currentInfo.calculateCostIncludingVat(), Constants.DECIMAL_PLACE_PRECISION);
			double currentCombinedCost = Util.roundDouble(currentItem.calculateCombinedCostIncludingVat(), Constants.DECIMAL_PLACE_PRECISION);

			String currentCostString         = Util.standardDoubleString(currentCost);
			String currentCombinedCostString = Util.standardDoubleString(currentCombinedCost);

			int currentNameLength                      = currentName.length();
			int currentQuantityLength                  = Util.lengthOfInt(currentQuantity);
			int currentCostBeforeDecimalLength         = Util.lengthOfDoubleBeforeDecimal(currentCost);
			int currentCostAfterDecimalLength          = Util.lengthOfDoubleAfterDecimal(currentCost);
			int currentCombinedCostBeforeDecimalLength = Util.lengthOfDoubleBeforeDecimal(currentCombinedCost);
			int currentCombinedCostAfterDecimalLength  = Util.lengthOfDoubleAfterDecimal(currentCombinedCost);

			int lengthAwayFromLongestName                      = longestNameLength                      - currentNameLength;
			int lengthAwayFromLongestQuantity                  = longestQuantityLength                  - currentQuantityLength;
			int lengthAwayFromLongestCostBeforeDecimal         = longestCostBeforeDecimalLength         - currentCostBeforeDecimalLength;
			int lengthAwayFromLongestCostAfterDecimal          = longestCostAfterDecimalLength          - currentCostAfterDecimalLength;
			int lengthAwayFromLongestCombinedCostBeforeDecimal = longestCombinedCostBeforeDecimalLength - currentCombinedCostBeforeDecimalLength;
			int lengthAwayFromLongestCombinedCostAfterDecimal  = longestCombinedCostAfterDecimalLength  - currentCombinedCostAfterDecimalLength;

			System.out.print("│");
			System.out.print(Util.charRepeat(' ', RECEIPT_BORDER_PADDING));

			System.out.print(currentName);

			if (!longestRowInReceiptIsOfAnItem)
			{
				int currentRowLength = calculateLengthOfItemRowInReceipt(
					currentNameLength,
					currentQuantityLength,
					currentCostBeforeDecimalLength,
					currentCostAfterDecimalLength,
					currentCombinedCostBeforeDecimalLength,
					currentCombinedCostAfterDecimalLength
				);

				int lengthAwayFromLongestRow = totalLengthInsideReceipt - currentRowLength;

				System.out.print(Util.charRepeat(' ', lengthAwayFromLongestRow));
			}

			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestName));
			System.out.print(Util.charRepeat(' ', RECEIPT_SECTION_PADDING));
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestQuantity));

			System.out.print(currentQuantity);

			System.out.print(RECEIPT_QUANTITY_DIVIDER);
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestCostBeforeDecimal));

			System.out.print(currentCostString);

			System.out.print(Util.charRepeat(' ', RECEIPT_SECTION_PADDING));
			System.out.print(Util.charRepeat(' ', lengthAwayFromLongestCombinedCostBeforeDecimal));

			System.out.print(currentCombinedCostString);

			System.out.print(RECEIPT_CURRENCY_TOKEN);
			System.out.print(Util.charRepeat(' ', RECEIPT_BORDER_PADDING));
			System.out.print("│");

			System.out.println();
		}
	}

	private void printReceiptBottom()
	{
		System.out.print("├");
		System.out.print(Util.charRepeat('─', totalLengthInsideReceipt));
		System.out.print("┤");
		System.out.println();

		System.out.print("│");
		System.out.print(Util.charRepeat(' ', RECEIPT_BORDER_PADDING));
		System.out.print(RECEIPT_END_RECEIPT_MESSAGE);
		System.out.print(Util.charRepeat(' ', totalLengthInsideReceipt - RECEIPT_END_RECEIPT_MESSAGE.length() - RECEIPT_BORDER_PADDING));
		System.out.print("│");
		System.out.println();

		System.out.print("└");
		System.out.print(Util.charRepeat('─', totalLengthInsideReceipt));
		System.out.print("┘");
		System.out.println();
	}

	public void printReceipt(SaleInfoDTO saleInfo)
	{
		Collection<RecordedItem> recordedItems = saleInfo.getCollectionOfRecordedItems();

		updateTimeOfSaleString(saleInfo.getTimeOfSale());

		updateTwoPartRows(saleInfo);

		updateLongestLengthsInfo(saleInfo.getStringLengthInfo());

		updateTotalLengthInsideReceipt();

		printReceiptTop();

		printReceiptTwoPartRow(twoPartRows.get("timeOfSale"));

		printReceiptEmptyRow();

		printReceiptItems(recordedItems);

		printReceiptEmptyRow();

		printReceiptTwoPartRow(twoPartRows.get("totalCost"));
		printReceiptTwoPartRow(twoPartRows.get("totalVat"));

		printReceiptEmptyRow();

		printReceiptTwoPartRow(twoPartRows.get("payment"));
		printReceiptTwoPartRow(twoPartRows.get("change"));

		printReceiptBottom();
	}
}
