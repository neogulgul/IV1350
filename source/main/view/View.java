package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.ItemIdDTO;

import se.kth.iv1350.util.Util;

public class View
{
	private Controller controller;

	public View(Controller controller)
	{
		this.controller = controller;
	}

	public void run()
	{
		controller.startSale();

		controller.scanItem(new ItemIdDTO("Frog"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Toaster"));
		controller.scanItem(new ItemIdDTO("Banana"));
		controller.scanItem(new ItemIdDTO("Pencil"));
		controller.scanItem(new ItemIdDTO("Frog"));

		controller.endSale();

		controller.checkForDiscount();

		controller.completeTransaction();
	}
}
