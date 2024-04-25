package se.kth.iv1350.model;

public class ScanInfoDTO
{
	private ItemIdDTO itemId;
	private ItemInfoDTO itemInfo;
	private int quantity;
	private double costOfEntireSale;
	private double vatOfEntireSale;

	public ScanInfoDTO(ItemIdDTO itemId, ItemInfoDTO itemInfo, int quantity, double costOfEntireSale, double vatOfEntireSale)
	{
		this.itemId           = itemId;
		this.itemInfo         = itemInfo;
		this.quantity         = quantity;
		this.costOfEntireSale = costOfEntireSale;
		this.vatOfEntireSale  = vatOfEntireSale;
	}

	public ItemIdDTO getItemId()
	{
		return itemId;
	}

	public ItemInfoDTO getItemInfo()
	{
		return itemInfo;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public double getCostOfEntireSale()
	{
		return costOfEntireSale;
	}

	public double getVatOfEntireSale()
	{
		return vatOfEntireSale;
	}

	public boolean isValid()
	{
		return itemInfo.isValid();
	}
}
