package se.kth.iv1350.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import se.kth.iv1350.constants.Constants;
import se.kth.iv1350.util.Util;

/**
 * Represents a sale.
 */
public class Sale
{
	private Map<ItemIdDTO, RecordedItem> recordedItems = new HashMap<>();
	private double costOfEntireSale = 0;
	private double vatOfEntireSale  = 0;
	private double paymentFromCustomer;
	private double changeForCustomer;
	private Calendar timeOfSale;

	private boolean previouslyRecorded(ItemIdDTO itemId)
	{
		return recordedItems.containsKey(itemId);
	}

	private void incrementQuantity(ItemIdDTO itemId, int incrementStep)
	{
		RecordedItem recordedItem = recordedItems.get(itemId);
		recordedItem.modifyQuantity(incrementStep);

		recordedItems.replace(itemId, recordedItem);
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

	/**
	 * {@link Sale} constructor.
	 */
	public Sale()
	{}

	/**
	 * Getter for cost of entire sale.
	 * @return Cost of entire sale.
	 */
	public double getCostOfEntireSale()
	{
		return costOfEntireSale;
	}

	/**
	 * Getter for VAT cost of entire sale.
	 * @return VAT cost of entire sale.
	 */
	public double getVatCostOfEntireSale()
	{
		return vatOfEntireSale;
	}

	/**
	 * Records an item.
	 *
	 * @param itemId Id of item to record.
	 * @param itemInfo Info about the item to be recorded.
	 * @param quantity Quantity of the item to be recorded.
	 */
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

		costOfEntireSale += itemInfo.calculateCostIncludingVat() * quantity;
		vatOfEntireSale  += itemInfo.calculateCostOfVat()        * quantity;
	}

	/**
	 * Creates information about the sale.
	 * @return Sale information.
	 */
	public SaleInfoDTO createInfoDTO()
	{
		Set<ItemIdDTO> recordedIds = recordedItems.keySet();
		int uniqueIds = recordedIds.size();
		boolean emptyStatus = uniqueIds == 0;

		return new SaleInfoDTO(
			emptyStatus,
			recordedItems,
			costOfEntireSale,
			vatOfEntireSale,
			paymentFromCustomer,
			changeForCustomer,
			timeOfSale,
			createSaleStringLengthInfoDTO()
		);
	}

	/**
	 * Logs the time of sale.
	 */
	public void logTimeOfSale()
	{
		timeOfSale = Calendar.getInstance();
	}

	/**
	 * Handles payment by calculating change.
	 *
	 * @param payment Customer payment.
	 *
	 * @throws InsufficientPaymentException When the payment was not sufficient to cover the costs of the entire sale.
	 */
	public void handlePayment(double payment)
	throws InsufficientPaymentException
	{
		if (payment < costOfEntireSale)
		{
			throw new InsufficientPaymentException(payment, costOfEntireSale);
		}

		paymentFromCustomer = payment;
		changeForCustomer   = payment - costOfEntireSale;
	}
}
