package se.kth.iv1350.model;

/**
 * DTO used for representing a recorded item in a sale.
 */
public class RecordedItem
{
	private ItemInfoDTO info;
	private int quantity;

	private void guardAgainstNegativeQuantity()
	{
		if (quantity < 0)
		{
			quantity = 0;
		}
	}

	/**
	 * {@link RecordedItem} constructor.
	 * @param info Information about items being recorded.
	 * @param quantity Quantity of items having been recorded as of construction.
	 */
	public RecordedItem(ItemInfoDTO info, int quantity)
	{
		this.info = info;
		this.quantity = quantity;
		guardAgainstNegativeQuantity();
	}

	/**
	 * Modifies the quantity of the recoreded item.
	 * @param step The amount of which the quantity is to be modified.
	 */
	public void modifyQuantity(int step)
	{
		quantity += step;
		guardAgainstNegativeQuantity();
	}

	/**
	 * Getter for item info.
	 * @return Item info of item being recorded.
	 */
	public ItemInfoDTO getInfo()
	{
		return info;
	}

	/**
	 * Getter for quantity.
	 * @return Quantity of item being recorded.
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Calculates combined price of items recorded.
	 * @return Combined price of recorded items.
	 */
	public double calculateCombinedPrice()
	{
		return info.getPrice() * quantity;
	}

	/**
	 * Calculates combined cost of all recorded items including their VAT rate.
	 * @return Combined cost of recorded items include their VAT rate.
	 */
	public double calculateCombinedCostIncludingVat()
	{
		return info.calculateCostIncludingVat() * quantity;
	}
}
