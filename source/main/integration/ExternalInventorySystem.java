package se.kth.iv1350.integration;

import java.util.Map;
import java.util.HashMap;

import se.kth.iv1350.model.*;

public class ExternalInventorySystem
{
	private Map<ItemIdDTO, ItemInfoDTO> itemStock = new HashMap<>();
	private final ItemInfoDTO INVALID_ITEM_INFO = new ItemInfoDTO();

	private class Vat
	{
		public static final double SMALL  = 0.06;
		public static final double MEDIUM = 0.12;
		public static final double LARGE  = 0.25;
	}

	public ExternalInventorySystem()
	{
		itemStock.put(
			new ItemIdDTO("appleGreen"),
			new ItemInfoDTO(
				"Green Apple",
				"An apple that is green.",
				2.99,
				Vat.SMALL
			)
		);
		itemStock.put(
			new ItemIdDTO("appleRed"),
			new ItemInfoDTO(
				"Red Apple",
				"An apple that is red.",
				4.99,
				Vat.SMALL
			)
		);
		itemStock.put(
			new ItemIdDTO("appleMystery"),
			new ItemInfoDTO(
				"Mysterious Apple",
				"???",
				0.99,
				Vat.SMALL
			)
		);
		itemStock.put(
			new ItemIdDTO("bananaNormal"),
			new ItemInfoDTO(
				"Banana",
				"Loved by monkeys all over the world.",
				8.00,
				Vat.SMALL
			)
		);
		itemStock.put(
			new ItemIdDTO("bananaGolden"),
			new ItemInfoDTO(
				"Golden Banana",
				"1 kg of solid gold in the shape of a banana.",
				800000.00,
				Vat.LARGE
			)
		);
		itemStock.put(
			new ItemIdDTO("eggDispenser"),
			new ItemInfoDTO(
				"Chicken",
				"Produces eggs.",
				500.00,
				Vat.MEDIUM
			)
		);
	}

	public Map<ItemIdDTO, ItemInfoDTO> getItemStock()
	{
		return itemStock;
	}

	public ItemInfoDTO retrieveItemInfo(ItemIdDTO itemId)
	{
		if (itemStock.containsKey(itemId))
		{
			return itemStock.get(itemId);
		}
		else
		{
			return INVALID_ITEM_INFO;
		}
	}

	public void updateQuantity(SaleInfoDTO saleInfo)
	{
	}
}
