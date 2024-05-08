package se.kth.iv1350.integration;

import se.kth.iv1350.model.ItemIdDTO;

/**
 * Exception for when an item could not be found.
 */
public class ItemNotFoundException extends Exception
{
	/**
	 * Constructor.
	 * @param itemId Identifier for the item that was not found.
	 */
	public ItemNotFoundException(ItemIdDTO itemId)
	{
		super("The item ID \"" + itemId + "\" is invalid.");
	}
}
