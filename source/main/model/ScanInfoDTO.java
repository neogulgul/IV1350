package se.kth.iv1350.model;

/**
 * DTO representing information after an item has been scanned.
 */
public class ScanInfoDTO
{
	private ItemIdDTO itemId;
	private ItemInfoDTO itemInfo;
	private int quantity;
	private double costOfEntireSale;
	private double vatOfEntireSale;

	/**
	 * Constructor.
	 *
	 * @param itemId Id of scanned item.
	 * @param itemInfo Information about scanned item.
	 * @param quantity Quantity of scanned item.
	 * @param costOfEntireSale Cost of the entire sale as of current scan.
	 * @param vatOfEntireSale Combined VAT cost of the entire sale as of current scan.
	 */
	public ScanInfoDTO(ItemIdDTO itemId, ItemInfoDTO itemInfo, int quantity, double costOfEntireSale, double vatOfEntireSale)
	{
		this.itemId           = itemId;
		this.itemInfo         = itemInfo;
		this.quantity         = quantity;
		this.costOfEntireSale = costOfEntireSale;
		this.vatOfEntireSale  = vatOfEntireSale;
	}

	/**
	 * Getter for if the scan info is valid.
	 * @return Whether or not the scan info is valid or not.
	 */
	public boolean isValid()
	{
		return itemInfo.isValid();
	}

	/**
	 * Getter for id of scanned item.
	 * @return Id of scanned item.
	 */
	public ItemIdDTO getItemId()
	{
		return itemId;
	}

	/**
	 * Getter for information about scanned item.
	 * @return Information about scanned item.
	 */
	public ItemInfoDTO getItemInfo()
	{
		return itemInfo;
	}

	/**
	 * Getter for quantity of scanned item.
	 * @return Quantity of scanned item.
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Getter for cost of the entire sale as of current scan.
	 * @return Cost of the entire sale as of current scan.
	 */
	public double getCostOfEntireSale()
	{
		return costOfEntireSale;
	}

	/**
	 * Getter for combined VAT cost of the entire sale as of current scan.
	 * @return Combined VAT cost of the entire sale as of current scan.
	 */
	public double getVatCostOfEntireSale()
	{
		return vatOfEntireSale;
	}
}
