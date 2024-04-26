package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.view.View;

public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new Controller();

		View view = new View(controller);

		if (args.length == 1 && args[0].equals("inventory"))
		{
			controller.printInventoryItemStock();
		}
		else if (args.length == 2)
		{
			view.run(args[0], args[1]);
		}
		else
		{
			System.out.println("Invalid arguments!");
			System.out.println("To run normally:");
			System.out.println("\tjava -cp <classpath> se.kth.iv1350.startup.Main <path/to/goods> <path/to/payment>");
			System.out.println("To see inventory:");
			System.out.println("\tjava -cp <classpath> se.kth.iv1350.startup.Main inventory");
		}
	}
}
