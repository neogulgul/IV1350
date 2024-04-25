package se.kth.iv1350.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

import se.kth.iv1350.util.Util;
import se.kth.iv1350.constants.Constants;

public class Sale
{
	private Map<ItemIdDTO, RecordedItem> recordedItems = new HashMap<>();
	private double costOfEntireSale = 0;
	private double vatOfEntireSale  = 0;
	private double paymentFromCustomer;
	private double changeForCustomer;
	private Calendar timeOfSale;

	public Sale()
	{}

	public double getCostOfEntireSale()
	{
		return costOfEntireSale;
	}

	public double getVatOfEntireSale()
	{
		return vatOfEntireSale;
	}

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
		if (quantity <= 0) return;

		if (previouslyRecorded(itemId))
		{
			incrementQuantity(itemId, quantity);
		}
		else
		{
			recordedItems.put(itemId, new RecordedItem(itemInfo, quantity));
		}

		costOfEntireSale += itemInfo.calculateCostIncludingVat() * quantity;
		vatOfEntireSale  += itemInfo.calculateCostOfVat() * quantity;
	}

	private SaleStringLengthInfoDTO createSaleStringLengthInfoDTO()
	{
		int lengthOfLongestName                      = 0;
		int lengthOfLongestQuantity                  = 0;
		int lengthOfLongestCostBeforeDecimal         = 0;
		int lengthOfLongestCostAfterDecimal          = 0;
		int lengthOfLongestCombinedCostBeforeDecimal = 0;
		int lengthOfLongestCombinedCostAfterDecimal  = 0;

		for (RecordedItem currentItem : recordedItems.values())
		{
			ItemInfoDTO currentInfo = currentItem.getInfo();

			int lengthOfCurrentName     = currentInfo.getName().length();
			int lengthOfCurrentQuantity = Util.lengthOfInt(currentItem.getQuantity());

			double currentCost         = Util.roundDouble(currentInfo.calculateCostIncludingVat(), Constants.DECIMAL_PLACE_PRECISION);
			double currentCombinedCost = Util.roundDouble(currentItem.calculateCombinedCostIncludingVat(), Constants.DECIMAL_PLACE_PRECISION);

			int lengthOfCurrentCostBeforeDecimal         = Util.lengthOfDoubleBeforeDecimal(currentCost);
			int lengthOfCurrentCostAfterDecimal          = Util.lengthOfDoubleAfterDecimal(currentCost);
			int lengthOfCurrentCombinedCostBeforeDecimal = Util.lengthOfDoubleBeforeDecimal(currentCombinedCost);
			int lengthOfCurrentCombinedCostAfterDecimal  = Util.lengthOfDoubleAfterDecimal(currentCombinedCost);

			lengthOfLongestName                      = Math.max(lengthOfLongestName                     , lengthOfCurrentName);
			lengthOfLongestQuantity                  = Math.max(lengthOfLongestQuantity                 , lengthOfCurrentQuantity);
			lengthOfLongestCostBeforeDecimal         = Math.max(lengthOfLongestCostBeforeDecimal        , lengthOfCurrentCostBeforeDecimal);
			lengthOfLongestCostAfterDecimal          = Math.max(lengthOfLongestCostAfterDecimal         , lengthOfCurrentCostAfterDecimal);
			lengthOfLongestCombinedCostBeforeDecimal = Math.max(lengthOfLongestCombinedCostBeforeDecimal, lengthOfCurrentCombinedCostBeforeDecimal);
			lengthOfLongestCombinedCostAfterDecimal  = Math.max(lengthOfLongestCombinedCostAfterDecimal , lengthOfCurrentCombinedCostAfterDecimal);
		}

		return new SaleStringLengthInfoDTO(
			lengthOfLongestName,
			lengthOfLongestQuantity,
			lengthOfLongestCostBeforeDecimal,
			lengthOfLongestCostAfterDecimal,
			lengthOfLongestCombinedCostBeforeDecimal,
			lengthOfLongestCombinedCostAfterDecimal
		);
	}

	public SaleInfoDTO createInfoDTO()
	{
		return new SaleInfoDTO(
			recordedItems,
			costOfEntireSale,
			vatOfEntireSale,
			paymentFromCustomer,
			changeForCustomer,
			timeOfSale,
			createSaleStringLengthInfoDTO()
		);
	}

	public void logTimeOfSale()
	{
		timeOfSale = Calendar.getInstance();
	}

	public void handlePayment(double payment)
	{
		paymentFromCustomer = payment;
		changeForCustomer   = payment - costOfEntireSale;
	}
}
