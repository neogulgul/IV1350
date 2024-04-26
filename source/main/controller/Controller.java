package se.kth.iv1350.controller;

import se.kth.iv1350.model.*;
import se.kth.iv1350.integration.*;

public class Controller
{
	private DiscountDatabase discountDatabase;
	private SalesLog salesLog;
	private ExternalAccountingSystem accounting;
	private ExternalInventorySystem inventory;
	private Register register;
	private Printer printer;

	private Sale currentSale;

	public Controller()
	{
		discountDatabase = new DiscountDatabase();
		salesLog         = new SalesLog();
		accounting       = new ExternalAccountingSystem();
		inventory        = new ExternalInventorySystem();
		register         = new Register();
		printer          = new Printer();
	}

	public String getItemStockStringFromInventory()
	{
		return inventory.getItemStockString();
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
	{
	}

	public boolean completeTransaction(double payment)
	{
		boolean sufficientPayment = currentSale.handlePayment(payment);

		if (sufficientPayment)
		{
			SaleInfoDTO saleInfo = currentSale.createInfoDTO();

			salesLog.logSale(saleInfo);
			accounting.updateAccounts(saleInfo);
			inventory.updateQuantity(saleInfo);
			register.increaseAmount(payment);
			printer.printReceipt(saleInfo);
		}

		return sufficientPayment;
	}
}
