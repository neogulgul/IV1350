package se.kth.iv1350.controller;

import java.util.Map;
import java.util.HashMap;

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

	public void printInventoryItemStock()
	{
		Map<ItemIdDTO, ItemInfoDTO> itemStock = inventory.getItemStock();

		System.out.println("ID\tINFO");

		for (ItemIdDTO itemId : itemStock.keySet())
		{
			ItemInfoDTO itemInfo = itemStock.get(itemId);

			System.out.println();

			System.out.println(itemId);
			System.out.println("\tName             : " + itemInfo.getName());
			System.out.println("\tPrice            : " + itemInfo.makePriceString());
			System.out.println("\tVAT              : " + itemInfo.makeVatString());
			System.out.println("\tCost (incl. VAT) : " + itemInfo.makeCostIncludingVatString());
			System.out.println("\tDescription      : " + itemInfo.getDescription());
		}
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
