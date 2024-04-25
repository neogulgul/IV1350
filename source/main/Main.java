package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.view.View;

public class Main
{
	public static void main(String[] args)
	{
		if (args.length == 1)
		{
			Controller controller = new Controller();

			View view = new View(controller, args[0]);

			view.run();
		}
		else
		{
			System.out.println("Pass in one argument (path/to/goods). No more, no less.");
		}
	}
}
