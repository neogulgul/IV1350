package se.kth.iv1350.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * DTO representing information about a sale.
 */
public class SaleInfoDTO
{
	private boolean emptyStatus;
	private Map<ItemIdDTO, RecordedItem> recordedItems = new HashMap<>();
	private double costOfEntireSale;
	private double vatOfEntireSale;
	private double paymentFromCustomer;
	private double changeForCustomer;
	private Calendar timeOfSale;
	private SaleStringLengthInfoDTO stringLengthInfo;

	/**
	 * Constructor.
	 *
	 * @param emptyStatus Whether or not the sale was empty (no recorded items).
	 * @param recordedItems Items that have been recorded.
	 * @param costOfEntireSale Cost of the entire sale.
	 * @param vatOfEntireSale VAT cost of the entire sale.
	 * @param paymentFromCustomer Payment received from the customer.
	 * @param changeForCustomer Change the customer will receive once the transaction is complete.
	 * @param timeOfSale Time the sale took place.
	 * @param stringLengthInfo Information about string lengths of item information which is used by the printer to align text in the receipt.
	 */
	public SaleInfoDTO(
		boolean emptyStatus,
		Map<ItemIdDTO, RecordedItem> recordedItems,
		double costOfEntireSale,
		double vatOfEntireSale,
		double paymentFromCustomer,
		double changeForCustomer,
		Calendar timeOfSale,
		SaleStringLengthInfoDTO stringLengthInfo
	)
	{
		this.emptyStatus         = emptyStatus;
		this.recordedItems       = recordedItems;
		this.costOfEntireSale    = costOfEntireSale;
		this.vatOfEntireSale     = vatOfEntireSale;
		this.paymentFromCustomer = paymentFromCustomer;
		this.changeForCustomer   = changeForCustomer;
		this.timeOfSale          = timeOfSale;
		this.stringLengthInfo    = stringLengthInfo;
	}

	/**
	 * Getter for the empty status of the sale which indicates whether or not the sale had any items added to it.
	 * @return Empty status of the sale.
	 */
	public boolean getEmptyStatus()
	{
		return emptyStatus;
	}

	/**
	 * Getter for recorded items that is deep copied, so changing the return value of this method avoids changing {@link recordedItems}.
	 * @return All recorded items of the entire sale.
	 */
	public Map<ItemIdDTO, RecordedItem> getRecordedItems()
	{
		Map<ItemIdDTO, RecordedItem> deepCopy = new HashMap<>();

		for (ItemIdDTO itemId : recordedItems.keySet())
		{
			deepCopy.put(itemId, recordedItems.get(itemId));
		}

		return deepCopy;
	}

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
	 * Getter for payment from customer.
	 * @return Payment from customer.
	 */
	public double getPaymentFromCustomer()
	{
		return paymentFromCustomer;
	}

	/**
	 * Getter for change for customer.
	 * @return Change for customer.
	 */
	public double getChangeForCustomer()
	{
		return changeForCustomer;
	}

	/**
	 * Getter for time of sale.
	 * @return Time of sale.
	 */
	public Calendar getTimeOfSale()
	{
		return timeOfSale;
	}

	/**
	 * Getter for string length info.
	 * @return Information about string lengths of item information which is used by the printer to align text in the receipt.
	 */
	public SaleStringLengthInfoDTO getStringLengthInfo()
	{
		return stringLengthInfo;
	}
}
