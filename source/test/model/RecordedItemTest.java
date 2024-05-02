import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.model.*;
import se.kth.iv1350.util.Util;

class RecordedItemTest
{
	private double price = 100;
	private double vat = 0.25;
	private int quantity = 100;
	private RecordedItem recordedItem;

	@BeforeEach
	public void setup()
	{
		recordedItem = new RecordedItem(
			new ItemInfoDTO("test", "this is a test", price, vat),
			quantity
		);
	}

	@Test
	void modifyQuantityTest()
	{
		int extra = 50;
		recordedItem.modifyQuantity(extra);
		assertEquals(recordedItem.getQuantity(), extra + quantity);
		recordedItem.modifyQuantity(-recordedItem.getQuantity() - 1);
		assertEquals(recordedItem.getQuantity(), 0);
	}

	@Test
	void calculateCombinedPriceTest()
	{
		assertEquals(recordedItem.calculateCombinedPrice(), price * quantity);
	}

	@Test
	void calculateCombinedCostIncludingVatTest()
	{
		assertEquals(recordedItem.calculateCombinedCostIncludingVat(), (price + price * vat) * quantity);
	}
}
