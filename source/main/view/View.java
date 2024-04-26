package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.ItemIdDTO;
import se.kth.iv1350.model.ItemInfoDTO;
import se.kth.iv1350.model.ScanInfoDTO;

import se.kth.iv1350.util.Util;
import se.kth.iv1350.constants.Constants;

public class View
{
	private Controller controller;

	public View(Controller controller)
	{
		this.controller = controller;
	}

	private void handleScanInfo(ScanInfoDTO scanInfo)
	{
		if (scanInfo.isValid())
		{
			ItemIdDTO scannedItemId     = scanInfo.getItemId();
			ItemInfoDTO scannedItemInfo = scanInfo.getItemInfo();
			int scannedItemQuantity     = scanInfo.getQuantity();

			double roundedTotalCost = Util.roundDouble(scanInfo.getCostOfEntireSale(), Constants.DECIMAL_PLACE_PRECISION);
			double roundedTotalVat  = Util.roundDouble(scanInfo.getVatOfEntireSale() , Constants.DECIMAL_PLACE_PRECISION);

			System.out.println(String.format("Scanned \"%s\" x %d:", scannedItemInfo.getName(), scannedItemQuantity));
			System.out.println("\tItem ID               : " + scannedItemId);
			System.out.println("\tItem price            : " + scannedItemInfo.makePriceString());
			System.out.println("\tItem VAT              : " + scannedItemInfo.makeVatString());
			System.out.println("\tItem cost (incl. VAT) : " + scannedItemInfo.makeCostIncludingVatString());
			System.out.println("\tItem description      : " + scannedItemInfo.getDescription());
			System.out.println();

			String runningCostString = Util.asCurrency(Util.standardDoubleString(scanInfo.getCostOfEntireSale()));
			String runningVatString  = Util.asCurrency(Util.standardDoubleString(scanInfo.getVatOfEntireSale()));

			System.out.println("Total cost (incl. VAT) : " + runningCostString);
			System.out.println("Total cost of VAT      : " + runningVatString);

			System.out.println();
		}
		else
		{
			System.out.println(scanInfo.getItemId() + " is not a valid item...");
		}
	}

	private void scanGoods(String goodsFilepath)
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
					ScanInfoDTO scanInfo = controller.scanItem(itemId, quantity);
					handleScanInfo(scanInfo);
				}
			}
		}
	}

	private double readPayment(String paymentFilepath)
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

	private void sendPaymentError()
	{
		System.out.println("Insufficient payment >:(");
	}

	public void run(String goodsFilepath, String paymentFilepath)
	{
		controller.startSale();

		scanGoods(goodsFilepath);

		controller.endSale();

		controller.checkForDiscount();

		double payment = readPayment(paymentFilepath);

		boolean transactionSuccess = controller.completeTransaction(payment);

		if (!transactionSuccess)
		{
			sendPaymentError();
		}
	}
}
