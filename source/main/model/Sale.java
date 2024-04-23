package se.kth.iv1350.model;

import java.util.Map;
import java.util.HashMap;

import se.kth.iv1350.util.Util;

public class Sale
{
	private Map<ItemIdDTO, RecordedItem> recordedItems = new HashMap<>();
	private int longestNameLengthOfAnyRecordedItem   = 0;
	private int longestPriceBeforeDecimalPointLength = 0;
	private int longestPriceAfterDecimalPointLength  = 0;

	public Sale()
	{}

	private boolean previouslyRecorded(ItemIdDTO itemId)
	{
		return recordedItems.containsKey(itemId);
	}

	private void incrementQuantity(ItemIdDTO itemId, int incrementStep)
	{
		if (!previouslyRecorded(itemId)) return;

		RecordedItem recordedItem = recordedItems.get(itemId);
		recordedItem.modifyQuantity(incrementStep);

		recordedItems.replace(itemId, recordedItem);
	}

	private void checkToUpdateLongestNameOfAnyRecordedItem(int lengthToCompare)
	{
		if (lengthToCompare > longestNameLengthOfAnyRecordedItem)
		{
			longestNameLengthOfAnyRecordedItem = lengthToCompare;
		}
	}

	private void checkToUpdateLongestPriceBeforeDecimalPointLength(int lengthToCompare)
	{
		if (lengthToCompare > longestPriceBeforeDecimalPointLength)
		{
			longestPriceBeforeDecimalPointLength = lengthToCompare;
		}
	}

	private void checkToUpdateLongestPriceAfterDecimalPointLength(int lengthToCompare)
	{
		if (lengthToCompare > longestPriceAfterDecimalPointLength)
		{
			longestPriceAfterDecimalPointLength = lengthToCompare;
		}
	}

	public void recordItem(ItemIdDTO itemId, ItemInfoDTO itemInfo, int quantity)
	{
		if (previouslyRecorded(itemId))
		{
			incrementQuantity(itemId, quantity);
		}
		else
		{
			checkToUpdateLongestNameOfAnyRecordedItem(itemInfo.getName().length());
			checkToUpdateLongestPriceBeforeDecimalPointLength(Util.lengthOfDoubleBeforeDecimalPoint(itemInfo.getPrice()));
			checkToUpdateLongestPriceAfterDecimalPointLength(Util.lengthOfDoubleAfterDecimalPoint(itemInfo.getPrice()));

			recordedItems.put(itemId, new RecordedItem(itemInfo, quantity));
		}
	}

	private int findLargestQuantity()
	{
		int largestQuantity = 0;
		for (RecordedItem item : recordedItems.values())
		{
			int quantityToCompareAgainst = item.getQuantity();
			if (quantityToCompareAgainst > largestQuantity)
			{
				largestQuantity = quantityToCompareAgainst;
			}
		}
		return largestQuantity;
	}

	public SaleInfoDTO createInfoDTO()
	{
		return new SaleInfoDTO(
			recordedItems,
			longestNameLengthOfAnyRecordedItem,
			longestPriceBeforeDecimalPointLength,
			longestPriceAfterDecimalPointLength,
			findLargestQuantity()
		);
	}
}
