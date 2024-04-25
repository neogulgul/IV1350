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

	public ScanInfoDTO scanItem(ItemIdDTO itemId, int quantity)
	{
		ItemInfoDTO itemInfo = inventory.retrieveItemInfo(itemId);

		if (itemInfo.isValid())
		{
			currentSale.recordItem(itemId, itemInfo, quantity);
		}

		return new ScanInfoDTO(itemId, itemInfo, quantity, currentSale.getCostOfEntireSale(), currentSale.getVatOfEntireSale());
	}

	public ScanInfoDTO scanItem(ItemIdDTO itemId)
	{
		return scanItem(itemId, 1);
	}

	public void endSale()
	{
		currentSale.logTimeOfSale();
	}

	public void checkForDiscount()
	{}

	public void completeTransaction(double payment)
	{
		currentSale.handlePayment(payment);
		printer.printReceipt(currentSale.createInfoDTO());
	}
}
