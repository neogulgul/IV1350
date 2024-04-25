package se.kth.iv1350.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Calendar;

public class SaleInfoDTO
{
	private Map<ItemIdDTO, RecordedItem> recordedItems = new HashMap<>();
	private double costOfEntireSale;
	private double vatOfEntireSale;
	private double paymentFromCustomer;
	private double changeForCustomer;
	private Calendar timeOfSale;
	private SaleStringLengthInfoDTO stringLengthInfo;

	public SaleInfoDTO(
		Map<ItemIdDTO, RecordedItem> recordedItems,
		double costOfEntireSale,
		double vatOfEntireSale,
		double paymentFromCustomer,
		double changeForCustomer,
		Calendar timeOfSale,
		SaleStringLengthInfoDTO stringLengthInfo
	)
	{
		this.recordedItems       = recordedItems;
		this.costOfEntireSale    = costOfEntireSale;
		this.vatOfEntireSale     = vatOfEntireSale;
		this.paymentFromCustomer = paymentFromCustomer;
		this.changeForCustomer   = changeForCustomer;
		this.timeOfSale          = timeOfSale;
		this.stringLengthInfo    = stringLengthInfo;
	}

	public Collection<RecordedItem> getCollectionOfRecordedItems()
	{
		return recordedItems.values();
	}

	public double getCostOfEntireSale()
	{
		return costOfEntireSale;
	}

	public double getVatOfEntireSale()
	{
		return vatOfEntireSale;
	}

	public double getPaymentFromCustomer()
	{
		return paymentFromCustomer;
	}

	public double getChangeForCustomer()
	{
		return changeForCustomer;
	}

	public Calendar getTimeOfSale()
	{
		return timeOfSale;
	}

	public SaleStringLengthInfoDTO getStringLengthInfo()
	{
		return stringLengthInfo;
	}
}
