import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.model.*;
import se.kth.iv1350.controller.Controller;

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

		ScanInfoDTO scanInfoThatShouldExist    = controller.scanItem(idThatShouldExist, 1);
		ScanInfoDTO scanInfoThatShouldNotExist = controller.scanItem(idThatShouldNotExist, 1);

		assertEquals(true, scanInfoThatShouldExist.isValid(), "this should exist");
		assertEquals(false, scanInfoThatShouldNotExist.isValid(), "this should not exist");
	}

	@Test
	public void completeTransactionTest()
	{
		controller.endSale();

		assertEquals(true, controller.completeTransaction(100), "should be allowed");
		assertEquals(false, controller.completeTransaction(-100), "should not be allowed");
	}
}
