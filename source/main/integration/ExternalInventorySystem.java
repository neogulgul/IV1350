package se.kth.iv1350.integration;

import java.util.Map;
import java.util.HashMap;

import se.kth.iv1350.model.*;

public class ExternalInventorySystem
{
	private Map<ItemIdDTO, ItemInfoDTO> itemStock = new HashMap<>();
	private final ItemInfoDTO INVALID_ITEM_INFO = new ItemInfoDTO();

	public ExternalInventorySystem()
	{
		itemStock.put(new ItemIdDTO("Frog"), new ItemInfoDTO("Frog", "A frog :o", 9.99, 0.10));
		itemStock.put(new ItemIdDTO("Toaster"), new ItemInfoDTO("Toaster", "A frog :o", 10.10, 0.10));
		itemStock.put(new ItemIdDTO("Banana"), new ItemInfoDTO("Banana", "A frog :o", 123.456, 0.10));
		itemStock.put(new ItemIdDTO("Pencil"), new ItemInfoDTO("Pencil", "A frog :o", 1.00, 0.10));
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
}
