package se.kth.iv1350.controller;

import se.kth.iv1350.model.*;
import se.kth.iv1350.integration.*;

public class Controller
{
	private ExternalInventorySystem inventory;
	private Printer printer;

	private Sale currentSale;

	public Controller()
	{
		inventory = new ExternalInventorySystem();
		printer = new Printer();
	}

	public void startSale()
	{
		currentSale = new Sale();
	}

	public boolean scanItem(ItemIdDTO itemId)
	{
		ItemInfoDTO itemInfo = inventory.retrieveItemInfo(itemId);

		if (itemInfo.isValid())
		{
			currentSale.recordItem(itemId, itemInfo, 1);
			return true;
		}

		return false;
	}

	public void endSale()
	{}

	public void checkForDiscount()
	{}

	public void completeTransaction()
	{
		printer.printReceipt(currentSale.createInfoDTO());
	}
}
