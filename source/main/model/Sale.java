package se.kth.iv1350.model;

import java.util.Map;
import java.util.HashMap;

import se.kth.iv1350.util.Util;

public class Sale
{
	private Map<ItemIdDTO, RecordedItem> recordedItems = new HashMap<>();

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

	public void recordItem(ItemIdDTO itemId, ItemInfoDTO itemInfo, int quantity)
	{
		if (previouslyRecorded(itemId))
		{
			incrementQuantity(itemId, quantity);
		}
		else
		{
			recordedItems.put(itemId, new RecordedItem(itemInfo, quantity));
		}
	}

	private SaleStringLengthInfoDTO createSaleStringLengthInfoDTO()
	{
		int lengthOfLongestName                             = 0;
		int lengthOfLongestQuantity                         = 0;
		int lengthOfLongestPriceBeforeDecimalLength         = 0;
		int lengthOfLongestPriceAfterDecimalLength          = 0;
		int lengthOfLongestCombinedPriceBeforeDecimalLength = 0;
		int lengthOfLongestCombinedPriceAfterDecimalLength  = 0;

		for (RecordedItem item : recordedItems.values())
		{
			ItemInfoDTO info = item.getInfo();

			int currentNameLenght = info.getName().length();
			int currentQuantityLength = Util.lengthOfInt(item.getQuantity());

			double price         = info.getPrice();
			double combinedPrice = item.calculateCombinedPrice();

			int currentPriceBeforeDecimalLength         = Util.lengthOfDoubleBeforeDecimal(price);
			int currentPriceAfterDecimalLength          = Util.lengthOfDoubleAfterDecimal(price);
			int currentCombinedPriceBeforeDecimalLength = Util.lengthOfDoubleBeforeDecimal(combinedPrice);
			int currentCombinedPriceAfterDecimalLength  = Util.lengthOfDoubleAfterDecimal(combinedPrice);

			lengthOfLongestName                             = Math.max(currentNameLenght, lengthOfLongestName);
			lengthOfLongestQuantity                         = Math.max(currentQuantityLength, lengthOfLongestQuantity);
			lengthOfLongestPriceBeforeDecimalLength         = Math.max(currentPriceBeforeDecimalLength, lengthOfLongestPriceBeforeDecimalLength);
			lengthOfLongestPriceAfterDecimalLength          = Math.max(currentPriceAfterDecimalLength, lengthOfLongestPriceAfterDecimalLength);
			lengthOfLongestCombinedPriceBeforeDecimalLength = Math.max(currentCombinedPriceBeforeDecimalLength, lengthOfLongestCombinedPriceBeforeDecimalLength);
			lengthOfLongestCombinedPriceAfterDecimalLength  = Math.max(currentCombinedPriceAfterDecimalLength, lengthOfLongestCombinedPriceAfterDecimalLength);
		}

		return new SaleStringLengthInfoDTO(
			lengthOfLongestName,
			lengthOfLongestQuantity,
			lengthOfLongestPriceBeforeDecimalLength,
			lengthOfLongestPriceAfterDecimalLength,
			lengthOfLongestCombinedPriceBeforeDecimalLength,
			lengthOfLongestCombinedPriceAfterDecimalLength
		);
	}

	public SaleInfoDTO createInfoDTO()
	{
		return new SaleInfoDTO(
			recordedItems,
			createSaleStringLengthInfoDTO()
		);
	}
}
