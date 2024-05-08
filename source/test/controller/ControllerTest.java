package se.kth.iv1350.controller;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

class ControllerTest
{
	private Controller controller;

	@BeforeEach
	public void setup()
	{
		controller = new Controller();
		controller.startSale();
	}

	@Test
	public void scanItemTest()
	{
		ItemIdDTO idThatShouldExist    = new ItemIdDTO("apple");
		ItemIdDTO idThatShouldNotExist = new ItemIdDTO("thisShouldNotExist!");


		try
		{
			controller.scanItem(idThatShouldExist, 1);
		}
		catch (ItemNotFoundException e)
		{
			fail("Item is not valid when it should be.");
		}

		try
		{
			controller.scanItem(idThatShouldNotExist, 1);
			fail("Item is valid when it should not be.");
		}
		catch (ItemNotFoundException e)
		{
		}
	}

	@Test
	public void completeTransactionTest()
	{
		controller.endSale();

		try
		{
			System.out.println("Completing transaction during testing which should succeed.");
			controller.completeTransaction(100);
		}
		catch (InsufficientPaymentException e)
		{
			fail("should be allowed, but is not");
		}

		try
		{
			System.out.println("Completing transaction during testing which should not succeed.");
			controller.completeTransaction(-100);
			fail("should not be allowed, but is");
		}
		catch (InsufficientPaymentException e)
		{
		}
	}
}
