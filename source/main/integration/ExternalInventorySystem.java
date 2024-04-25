package se.kth.iv1350.integration;

import java.util.Map;
import java.util.HashMap;

import se.kth.iv1350.model.*;

public class ExternalInventorySystem
{
	private Map<ItemIdDTO, ItemInfoDTO> itemStock = new HashMap<>();
	private final ItemInfoDTO INVALID_ITEM_INFO = new ItemInfoDTO();

	private final double SMALL_VAT  = 0.06;
	private final double MEDIUM_VAT = 0.12;
	private final double LARGE_VAT  = 0.25;

	public ExternalInventorySystem()
	{
		itemStock.put(
			new ItemIdDTO("appleRed"),
			new ItemInfoDTO(
				"Red Apple",
				"An apple that is red.",
				3.50,
				SMALL_VAT
			)
		);
		itemStock.put(
			new ItemIdDTO("appleGreen"),
			new ItemInfoDTO(
				"Green Apple",
				"An apple that is green.",
				3.50,
				SMALL_VAT
			)
		);
		itemStock.put(
			new ItemIdDTO("appleMystery"),
			new ItemInfoDTO(
				"Mysterious Apple",
				"???",
				0.50,
				SMALL_VAT
			)
		);
		itemStock.put(
			new ItemIdDTO("bananaNormal"),
			new ItemInfoDTO(
				"Banana",
				"Loved by monkeys all over the world.",
				5.90,
				SMALL_VAT
			)
		);
		itemStock.put(
			new ItemIdDTO("bananaGolden"),
			new ItemInfoDTO(
				"Golden Banana",
				"1 kg of solid gold in the shape of a banana.",
				999999.99,
				LARGE_VAT
			)
		);
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
