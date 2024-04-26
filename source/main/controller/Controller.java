package se.kth.iv1350.controller;

import java.util.HashMap;
import java.util.Map;

import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

/**
 * The {@link Controller} bridges the gap between the {@link se.kth.iv1350.view.View} and other parts of the program.
 */
public class Controller
{
	private DiscountDatabase discountDatabase;
	private SalesLog salesLog;
	private ExternalAccountingSystem accounting;
	private ExternalInventorySystem inventory;
	private Register register;
	private Printer printer;

	private Sale currentSale;

	/**
	 * {@link Controller} constructor.
	 */
	public Controller()
	{
		discountDatabase = new DiscountDatabase();
		salesLog         = new SalesLog();
		accounting       = new ExternalAccountingSystem();
		inventory        = new ExternalInventorySystem();
		register         = new Register();
		printer          = new Printer();
	}

	/**
	 * Prints out all the items and their respective information that is stored in the external inventory system.
	 */
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

	/**
	 * Starts a new sale by creating a new {@link Sale} instance, which erases any prior sale information.
	 */
	public void startSale()
	{
		currentSale = new Sale();
	}

	/**
	 * Scans an item and records it in the current sale depending on whether or not the item is present in the inventory.
	 *
	 * @param itemId The item id that is used to retrieve information about an item from the inventory.
	 * @param quantity The quantity of the item that is being scanned, which the sale keeps track of.
	 *
	 * @return Returns a {@link ScanInfoDTO} which contains all the important information about the scan that just took place.
	 */
	public ScanInfoDTO scanItem(ItemIdDTO itemId, int quantity)
	{
		ItemInfoDTO itemInfo = inventory.retrieveItemInfo(itemId);

		if (itemInfo.isValid())
		{
			currentSale.recordItem(itemId, itemInfo, quantity);
		}

		return new ScanInfoDTO(itemId, itemInfo, quantity, currentSale.getCostOfEntireSale(), currentSale.getVatOfEntireSale());
	}

	/**
	 * Ends the ongoing sale. This logs the current time as the time of sale.
	 */
	public void endSale()
	{
		currentSale.logTimeOfSale();
	}

	/**
	 * Would have been responsible for handling discount logic if it was to be implemented.
	 */
	public void checkForDiscount()
	{
	}

	/**
	 * Completes the transaction of the sale.
	 *
	 * @param payment The payment received from the customer.
	 *
	 * @return Returns a <code>boolean</code> which indicates whether or not the payment was sufficient for covering the cost of the entire sale.
	 */
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
