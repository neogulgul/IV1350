package se.kth.iv1350.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

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
		assertEquals(true, itemInfo.isValid(), "this should be valid, but is not");
	}

	@Test
	public void getNameTest()
	{
		assertEquals(name, itemInfo.getName(), "the name is wrong");
	}

	@Test
	public void getDescriptionTest()
	{
		assertEquals(description, itemInfo.getDescription(), "the description is wrong");
	}

	@Test
	public void getPriceTest()
	{
		assertEquals(price, itemInfo.getPrice(), "the price is wrong");
	}

	@Test
	public void getVatTest()
	{
		assertEquals(vat, itemInfo.getVat(), "the vat is wrong");
	}

	@Test
	public void calculateCostOfVatTest()
	{
		assertEquals(price * vat, itemInfo.calculateCostOfVat(), "the cost of vat is wrong");
	}

	@Test
	public void calculateCostIncludingVatTest()
	{
		assertEquals(price + itemInfo.calculateCostOfVat(), itemInfo.calculateCostIncludingVat(), "the cost including vat is wrong");
	}

	@Test
	public void makeVatStringTest()
	{
		assertEquals(Util.standardDoubleString(vat * 100) + "%", itemInfo.makeVatString(), "the vat string is wrong");
	}
}
