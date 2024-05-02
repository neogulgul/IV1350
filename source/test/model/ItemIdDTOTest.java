package se.kth.iv1350.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemIdDTOTest
{
	@Test
	public void equalsTest()
	{
		ItemIdDTO A = new ItemIdDTO("Dog");
		ItemIdDTO B = new ItemIdDTO("Cat");
		ItemIdDTO C = new ItemIdDTO("Dog");

		assertEquals(false, A.equals(B), "this should not be equal, but is");
		assertEquals(true, A.equals(C), "this should be equal, but is not");
	}
}
