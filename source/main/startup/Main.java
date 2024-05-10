package se.kth.iv1350.startup;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.SimulatedSaleDTO;
import se.kth.iv1350.view.View;
import se.kth.iv1350.util.Util;

/**
 * Contains the entry point for the program.
 */
public class Main
{
	private Main() {}

	private static void printHelp()
	{
		try
		{
			String help = Util.readFromFile("help.txt");
			System.out.println(help);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Help file was not found.");
		}
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
		else if (args.length != 0)
		{
			int simulationCount = args.length / 2;
			SimulatedSaleDTO[] salesToSimulate = new SimulatedSaleDTO[simulationCount];

			for (int i = 0; i < simulationCount; i++)
			{
				String a = args[i * 2];
				String b = args[i * 2 + 1];

				switch (a)
				{
					case "--path": case "-p":
					{
						boolean lastCharacterOfPathIsForwardSlash = b.charAt(b.length() - 1) == '/';

						salesToSimulate[i] = new SimulatedSaleDTO(
							b + (lastCharacterOfPathIsForwardSlash ? "" : "/") + "goods.txt",
							b + (lastCharacterOfPathIsForwardSlash ? "" : "/") + "payment.txt"
						);

						break;
					}
					default:
					{
						salesToSimulate[i] = new SimulatedSaleDTO(a, b);
						break;
					}
				}
			}

			view.run(salesToSimulate);
			return;
		}

		System.out.println("Invalid arguments.");
		System.out.println();
		printHelp();
	}
}
