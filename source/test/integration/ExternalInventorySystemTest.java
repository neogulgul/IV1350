import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.integration.ExternalInventorySystem;
import se.kth.iv1350.model.*;

class ExternalInventorySystemTest
{
	private ExternalInventorySystem inventory;

	@BeforeEach
	public void setup()
	{
		inventory = new ExternalInventorySystem();
	}

	@Test
	public void retrieveItemInfoTest()
	{
		ItemInfoDTO itemInfo = inventory.retrieveItemInfo(new ItemIdDTO("THIS_SHOULD_DEFINITELY_NOT_EXIST"));
		assertEquals(false, itemInfo.isValid());
	}
}
