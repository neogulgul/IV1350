package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.ItemIdDTO;
import se.kth.iv1350.model.ItemInfoDTO;

import se.kth.iv1350.util.Util;

public class View
{
	private Controller controller;

	public View(Controller controller)
	{
		this.controller = controller;
	}

	private void handleInfoFromScannedItem(ItemIdDTO scannedItemId, int scannedItemQuantity, ItemInfoDTO scannedItemInfo)
	{
		if (scannedItemInfo.isValid())
		{
			System.out.println(scannedItemInfo.getName() + " [" + scannedItemId + "]" + " x " + scannedItemQuantity + " was added. (" + scannedItemInfo.getDescription() + ")");
		}
		else
		{
			System.out.println(scannedItemId + " is not a valid item...");
		}
	}

	private void scanGoods()
	{
		String goodsTextfile = Util.readFromFile("goods.txt");
		String[] goodsLines = goodsTextfile.split("\n");
		for (String line : goodsLines)
		{
			String[] lineSplit = line.split(Util.REGEX_SEQUENCE_OF_SPACES);
			if (lineSplit.length == 2)
			{
				ItemIdDTO itemId = new ItemIdDTO(lineSplit[0]);
				int quantity = Math.max(1, Integer.parseInt(lineSplit[1]));
				ItemInfoDTO scannedItemInfo = controller.scanItem(itemId, quantity);
				handleInfoFromScannedItem(itemId, quantity, scannedItemInfo);
			}
		}
	}

	public void run()
	{
		controller.startSale();

		scanGoods();

		controller.endSale();

		controller.checkForDiscount();

		controller.completeTransaction();
	}
}
