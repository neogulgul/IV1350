package se.kth.iv1350.startup;

import java.util.HashMap;
import java.util.Map;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.view.View;
import se.kth.iv1350.util.Logger;

/**
 * Contains the entry point for the program.
 */
public class Main
{
	private Main() {}

	private static void helpMessage(String options, String information)
	{
		System.out.println(options + "\n\t" + information);
	}

	private static void printHelp()
	{
		System.out.println("Usage: \"java -cp <classpath> se.kth.iv1350.startup.Main <options...>\"");
		System.out.println("Options:");
		helpMessage("--help, -h", "Displays helpful information");
		helpMessage("--inventory, -i", "Displays inventory");
		helpMessage("--path <path>, -p <path>", "Default behaviour with goods from <path>/goods.txt and payment from <path>/payment.txt");
		helpMessage("<goods> <payment>", "Default behaviour with goods from <goods> and payment from <payment>");
	}

	/**
	 * Entry point for the program.
	 *
	 * @param args Command line arguments that specify what the program should do.
	 */
	public static void main(String[] args)
	{
		Controller controller = new Controller();

		View view = new View(controller);

		if (args.length == 1)
		{
			switch (args[0])
			{
				case "--help": case "-h":
				{
					printHelp();
					return;
				}
				case "--inventory": case "-i":
				{
					view.printInventoryItemStock();
					return;
				}
			}
		}
		else if (args.length == 2)
		{
			String a = args[0];
			String b = args[1];

			switch (a)
			{
				case "--path": case "-p":
				{
					view.run(b + "/goods.txt", b + "/payment.txt");
					return;
				}
			}

			view.run(a, b);
			return;
		}

		System.out.println("Invalid arguments.");
		System.out.println();
		printHelp();
	}
}
