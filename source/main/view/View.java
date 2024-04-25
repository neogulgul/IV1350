package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.ItemIdDTO;
import se.kth.iv1350.model.ItemInfoDTO;
import se.kth.iv1350.model.ScanInfoDTO;

import se.kth.iv1350.util.Util;
import se.kth.iv1350.constants.Constants;

public class View
{
	private String goodsFilepath;
	private Controller controller;

	public View(Controller controller, String goodsFilepath)
	{
		this.controller = controller;
		this.goodsFilepath = goodsFilepath;
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

			String scannedItemPriceString = Util.standardizeDouble(scannedItemInfo.getPrice()) + " " + Constants.CURRENCY_CODE;
			String scannedItemVatString   = Util.standardizeDouble(scannedItemInfo.getVat() * 100) + "%";
			String scannedItemCostString  = Util.standardizeDouble(scannedItemInfo.calculateCostIncludingVat()) + " " + Constants.CURRENCY_CODE;

			System.out.println(String.format("Scanned \"%s\" x %d:", scannedItemInfo.getName(), scannedItemQuantity));
			System.out.println("\tItem ID               : " + scannedItemId);
			System.out.println("\tItem price            : " + scannedItemPriceString);
			System.out.println("\tItem VAT              : " + scannedItemVatString);
			System.out.println("\tItem cost (incl. VAT) : " + scannedItemCostString);
			System.out.println("\tItem description      : " + scannedItemInfo.getDescription());
			System.out.println();

			String runningCostString = Util.standardizeDouble(scanInfo.getCostOfEntireSale()) + " " + Constants.CURRENCY_CODE;
			String runningVatString  = Util.standardizeDouble(scanInfo.getVatOfEntireSale())  + " " + Constants.CURRENCY_CODE;

			System.out.println("Total cost (incl. VAT) : " + runningCostString);
			System.out.println("Total cost of VAT      : " + runningVatString);

			System.out.println();
		}
		else
		{
			System.out.println(scanInfo.getItemId() + " is not a valid item...");
		}
	}

	private void scanGoods()
	{
		String goodsTextfile = Util.readFromFile(goodsFilepath);
		String[] goodsLines = goodsTextfile.split("\n");
		for (String line : goodsLines)
		{
			String[] lineSplit = line.split(Constants.REGEX_SEQUENCE_OF_SPACES);
			if (lineSplit.length == 2)
			{
				ItemIdDTO itemId = new ItemIdDTO(lineSplit[0]);
				int quantity = Math.max(1, Integer.parseInt(lineSplit[1]));

				ScanInfoDTO scanInfo = controller.scanItem(itemId, quantity);
				handleScanInfo(scanInfo);
			}
		}
	}

	public void run()
	{
		controller.startSale();

		scanGoods();

		controller.endSale();

		controller.checkForDiscount();

		controller.completeTransaction(1000);
	}
}
