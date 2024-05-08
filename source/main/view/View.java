package se.kth.iv1350.view;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import se.kth.iv1350.constants.Constants;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.ItemNotFoundException;
import se.kth.iv1350.model.InsufficientPaymentException;
import se.kth.iv1350.model.ItemIdDTO;
import se.kth.iv1350.model.ItemInfoDTO;
import se.kth.iv1350.model.ScanInfoDTO;
import se.kth.iv1350.util.Util;

/**
 * The {@link View} bridges the gap between the user and the program.
 * In this case however, the {@link View} is not entirely implemented.
 * It reads the goods and the payment that a customer would have brought from two individual text files.
 */
public class View
{
	private Controller controller;

	/**
	 * {@link View} constructor.
	 *
	 * @param controller Responsible for bridging the gap between other parts of the program.
	 */
	public View(Controller controller)
	{
		this.controller = controller;
	}

	private void printItemInfo(ItemInfoDTO itemInfo)
	{
		String priceString            = Util.asCurrency(Util.standardDoubleString(itemInfo.getPrice()));
		String vatString              = Util.standardDoubleString(itemInfo.getVat() * 100) + "%";
		String costIncludingVatString = Util.asCurrency(Util.standardDoubleString(itemInfo.calculateCostIncludingVat()));

		System.out.println("\tName             : " + itemInfo.getName());
		System.out.println("\tPrice            : " + priceString);
		System.out.println("\tVAT              : " + vatString);
		System.out.println("\tCost (incl. VAT) : " + costIncludingVatString);
		System.out.println("\tDescription      : " + itemInfo.getDescription());
	}

	private void printValidScan(ScanInfoDTO scanInfo)
	{
		ItemIdDTO scannedItemId     = scanInfo.getItemId();
		ItemInfoDTO scannedItemInfo = scanInfo.getItemInfo();
		int scannedItemQuantity     = scanInfo.getQuantity();

		double roundedTotalCost = Util.roundDouble(scanInfo.getCostOfEntireSale(), Constants.DECIMAL_PLACE_PRECISION);
		double roundedTotalVat  = Util.roundDouble(scanInfo.getVatCostOfEntireSale() , Constants.DECIMAL_PLACE_PRECISION);

		System.out.println(String.format("Scanned item with ID \"%s\" x %d:", scannedItemId, scannedItemQuantity));
		printItemInfo(scannedItemInfo);
		System.out.println();

		String runningCostString = Util.asCurrency(Util.standardDoubleString(scanInfo.getCostOfEntireSale()));
		String runningVatString  = Util.asCurrency(Util.standardDoubleString(scanInfo.getVatCostOfEntireSale()));

		System.out.println("Total cost (incl. VAT) : " + runningCostString);
		System.out.println("Total cost of VAT      : " + runningVatString);

		System.out.println();
	}

	private void scanGoods(String goodsFilepath)
	{
		try
		{
			String goodsText = Util.readFromFile(goodsFilepath);
			String[] goodsLines = goodsText.split("\n");
			for (String line : goodsLines)
			{
				String[] lineSplit = line.split(Constants.REGEX_SEQUENCE_OF_SPACES);
				if (lineSplit.length == 2)
				{
					ItemIdDTO itemId = new ItemIdDTO(lineSplit[0]);

					int quantity;

					try
					{
						quantity = Math.max(1, Integer.parseInt(lineSplit[1]));
					}
					catch (NumberFormatException e)
					{
						quantity = 0;
					}

					if (quantity > 0)
					{
						try
						{
							ScanInfoDTO scanInfo = controller.scanItem(itemId, quantity);
							printValidScan(scanInfo);
						}
						catch (ItemNotFoundException e)
						{
							System.out.println(e.getMessage() + "\n");
						}
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Goods file was not found.");
		}
	}

	private double readPayment(String paymentFilepath)
	throws FileNotFoundException
	{
		String paymentText = Util.readFromFile(paymentFilepath);

		double payment;

		try
		{
			payment = Double.parseDouble(paymentText);
		}
		catch (NumberFormatException e)
		{
			payment = 0;
		}

		System.out.println("Customer pays: " + Util.asCurrency(Util.standardDoubleString(payment)));

		return payment;
	}

	/**
	 * Prints out all the items and their respective information that is stored in the external inventory system.
	 */
	public void printInventoryItemStock()
	{
		Map<ItemIdDTO, ItemInfoDTO> itemStock = controller.getInventoryItemStock();

		System.out.println("ID\tINFO");

		for (ItemIdDTO itemId : itemStock.keySet())
		{
			ItemInfoDTO itemInfo = itemStock.get(itemId);

			System.out.println();
			System.out.println(itemId);
			printItemInfo(itemInfo);
		}
	}

	/**
	 * Used to run the bulk of the program.
	 *
	 * @param goodsFilepath   The filepath to the textfile containing information about the customer goods.
	 * @param paymentFilepath The filepath to the textfile containing information about the customer payment.
	 */
	public void run(String goodsFilepath, String paymentFilepath)
	{
		controller.startSale();

		scanGoods(goodsFilepath);

		controller.endSale();

		controller.checkForDiscount();

		try
		{
			double payment = readPayment(paymentFilepath);

			try
			{
				controller.completeTransaction(payment);
			}
			catch (InsufficientPaymentException e)
			{
				System.out.println(e.getMessage());
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Payment file was not found.");
		}
	}
}
