package se.kth.iv1350.integration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import se.kth.iv1350.model.*;
import se.kth.iv1350.util.ExceptionLogger;

/**
 * {@link ExternalInventorySystem} represents an external inventory system.
 */
public class ExternalInventorySystem
{
	private Map<ItemIdDTO, ItemInfoDTO> itemStock = new HashMap<>();
	private ItemIdDTO databaseErrorItemId = new ItemIdDTO("THROW_DATABASE_UNAVAILABLE_EXCEPTION");

	private class Vat
	{
		public static final double SMALL  = 0.06;
		public static final double MEDIUM = 0.12;
		public static final double LARGE  = 0.25;
	}

	/**
	 * Creates an instance of an {@link ExternalInventorySystem} with items in stock.
	 */
	public ExternalInventorySystem()
	{
		itemStock.put(
			new ItemIdDTO("book"),
			new ItemInfoDTO(
				"Book",
				"A piece of literature.",
				50.50,
				Vat.SMALL
			)
		);
		itemStock.put(
			new ItemIdDTO("apple"),
			new ItemInfoDTO(
				"Apple",
				"Keeps doctors away.",
				4.99,
				Vat.MEDIUM
			)
		);
		itemStock.put(
			new ItemIdDTO("banana"),
			new ItemInfoDTO(
				"Banana",
				"Loved by monkeys.",
				7.99,
				Vat.MEDIUM
			)
		);
		itemStock.put(
			new ItemIdDTO("waterBottle"),
			new ItemInfoDTO(
				"Bottled Water",
				"Filled with water.",
				14.99,
				Vat.LARGE
			)
		);
	}

	/**
	 * <code>itemStock</code> getter.
	 *
	 * @return <code>Map&lt;ItemIdDTO, ItemInfoDTO&gt;</code> which represents the item stock.
	 */
	public Map<ItemIdDTO, ItemInfoDTO> getItemStock()
	{
		return itemStock;
	}

	/**
	 * Retrieves item information from the stock.
	 *
	 * @param itemId The id of the item to retrieve info about.
	 *
	 * @return Information about the item with the given item id.
	 *         If the item id was not found in the stock it returns invalid item information.
	 *
	 * @throws ItemNotFoundException If the item is not found inside the item stock.
	 * @throws DatabaseUnavailableException If the inventory database connection fails.
	 */
	public ItemInfoDTO retrieveItemInfo(ItemIdDTO itemId)
	throws ItemNotFoundException, DatabaseUnavailableException
	{
		if (itemId.equals(databaseErrorItemId))
		{
			DatabaseUnavailableException exception = new DatabaseUnavailableException("external inventory system");
			ExceptionLogger.getInstance().log(exception);
			throw exception;
		}

		if (itemStock.containsKey(itemId))
		{
			return itemStock.get(itemId);
		}

		throw new ItemNotFoundException(itemId);
	}

	/**
	 * Would have updated the quantity of items in stock if it were to be implemented.
	 *
	 * @param saleInfo Sale information that would have been used to update the quantity of the corresponding bought items.
	 */
	public void updateQuantity(SaleInfoDTO saleInfo)
	{
		if (saleInfo.getEmptyStatus())
		{
			System.out.println("No change was made to the external inventory system.");
		}
		else
		{
			System.out.println("Changes in external inventory system:");

			Map<ItemIdDTO, RecordedItem> recordedItems = saleInfo.getRecordedItems();

			for (ItemIdDTO itemId : recordedItems.keySet())
			{
				RecordedItem recordedItem = recordedItems.get(itemId);
				System.out.println(String.format(
					"\tQuantity of \"%s\" was decreased by %d units.",
					itemId,
					recordedItem.getQuantity()
				));
			}
		}
	}
}
