package se.kth.iv1350.model;

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

	public RecordedItem(ItemInfoDTO info, int quantity)
	{
		this.info = info;
		this.quantity = quantity;
		guardAgainstNegativeQuantity();
	}

	public void modifyQuantity(int step)
	{
		quantity += step;
		guardAgainstNegativeQuantity();
	}

	public ItemInfoDTO getInfo()
	{
		return info;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public double calculateCombinedPrice()
	{
		return info.getPrice() * quantity;
	}
}
