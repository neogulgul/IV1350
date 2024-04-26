import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.model.*;
import se.kth.iv1350.util.Util;

class ItemInfoDTOTest
{
	private String name        = "TESTING";
	private String description = "TESTING TESTING TESTING";
	private double price       = 123.456;
	private double vat         = 0.789;

	private ItemInfoDTO itemInfo;

	@BeforeEach
	public void setup()
	{
		itemInfo = new ItemInfoDTO(name, description, price, vat);
	}

	@Test
	public void isValidTest()
	{
		assertEquals(true, itemInfo.isValid());
	}

	@Test
	public void getNameTest()
	{
		assertEquals(name, itemInfo.getName());
	}

	@Test
	public void getDescriptionTest()
	{
		assertEquals(description, itemInfo.getDescription());
	}

	@Test
	public void getPriceTest()
	{
		assertEquals(price, itemInfo.getPrice());
	}

	@Test
	public void getVatTest()
	{
		assertEquals(vat, itemInfo.getVat());
	}

	@Test
	public void calculateCostOfVatTest()
	{
		assertEquals(price * vat, itemInfo.calculateCostOfVat());
	}

	@Test
	public void calculateCostIncludingVatTest()
	{
		assertEquals(price + itemInfo.calculateCostOfVat(), itemInfo.calculateCostIncludingVat());
	}

	@Test
	public void makeVatStringTest()
	{
		assertEquals(Util.standardDoubleString(vat * 100) + "%", itemInfo.makeVatString());
	}
}
