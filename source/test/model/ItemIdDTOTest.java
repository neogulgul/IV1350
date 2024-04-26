import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.model.*;

class ItemIdDTOTest
{
	@Test
	public void equalsTest()
	{
		ItemIdDTO A = new ItemIdDTO("Dog");
		ItemIdDTO B = new ItemIdDTO("Cat");
		ItemIdDTO C = new ItemIdDTO("Dog");

		assertEquals(false, A.equals(B));
		assertEquals(true, A.equals(C));
	}
}
