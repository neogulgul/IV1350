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
	 * Getter for the inventory item stock.
	 * @return Returns a hashmap in the form of <code>Map&lt;ItemIdDTO, ItemInfoDTO&gt;</code>.
	 */
	public Map<ItemIdDTO, ItemInfoDTO> getInventoryItemStock()
	{
		return inventory.getItemStock();
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

		return new ScanInfoDTO(itemId, itemInfo, quantity, currentSale.getCostOfEntireSale(), currentSale.getVatCostOfEntireSale());
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
			register.increaseAmount(saleInfo.getCostOfEntireSale());
			printer.printReceipt(saleInfo);
		}

		return sufficientPayment;
	}
}
