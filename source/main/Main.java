package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.view.View;

import se.kth.iv1350.util.Util;

public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new Controller();

		View view = new View(controller);

		view.run();
	}
}
