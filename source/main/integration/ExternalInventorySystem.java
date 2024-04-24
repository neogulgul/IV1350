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
		itemStock.put(new ItemIdDTO("frog"), new ItemInfoDTO("Frog", "A frog :o", 9.99, 0.10));
		itemStock.put(new ItemIdDTO("toaster"), new ItemInfoDTO("Toaster", "You can make toast ᕕ(ᐛ)ᕗ", 10.10, 0.10));
		itemStock.put(new ItemIdDTO("banan"), new ItemInfoDTO("Banana", "Be like monke. Eat banana.", 123.456, 0.10));
		itemStock.put(new ItemIdDTO("pen"), new ItemInfoDTO("Pencil", "Write stuff with this.", 1.0, 0.10));
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
