package se.kth.iv1350.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class SaleInfoDTO
{
	private Map<ItemIdDTO, RecordedItem> recordedItems = new HashMap<>();
	private int longestNameLengthOfAnyRecordedItem;
	private int longestPriceBeforeDecimalPointLength;
	private int longestPriceAfterDecimalPointLength;
	private int largestQuantity;

	public SaleInfoDTO(
		Map<ItemIdDTO, RecordedItem> recordedItems,
		int longestNameLengthOfAnyRecordedItem,
		int longestPriceBeforeDecimalPointLength,
		int longestPriceAfterDecimalPointLength,
		int largestQuantity
	)
	{
		this.recordedItems = recordedItems;
		this.longestNameLengthOfAnyRecordedItem = longestNameLengthOfAnyRecordedItem;
		this.longestPriceBeforeDecimalPointLength = longestPriceBeforeDecimalPointLength;
		this.longestPriceAfterDecimalPointLength = longestPriceAfterDecimalPointLength;
		this.largestQuantity = largestQuantity;
	}

	public Collection<RecordedItem> getCollectionOfRecordedItems()
	{
		return recordedItems.values();
	}

	public int getLongestNameLengthOfAnyRecordedItem()
	{
		return longestNameLengthOfAnyRecordedItem;
	}

	public int getLongestPriceBeforeDecimalPointLength()
	{
		return longestPriceBeforeDecimalPointLength;
	}

	public int getLongestPriceAfterDecimalPointLength()
	{
		return longestPriceAfterDecimalPointLength;
	}

	public int getLargestQuantity()
	{
		return largestQuantity;
	}
}
