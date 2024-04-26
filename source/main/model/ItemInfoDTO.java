package se.kth.iv1350.model;

import se.kth.iv1350.util.Util;

public class ItemInfoDTO
{
	private String name;
	private String description;
	private double price;
	private double vat;

	public ItemInfoDTO()
	{
		this.name = null;
	}

	public ItemInfoDTO(String name, String description, double price, double vat)
	{
		this.name = name;
		this.description = description;
		this.price = price;
		this.vat = vat;
	}

	public boolean isValid()
	{
		return name != null;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public double getPrice()
	{
		return price;
	}

	public double getVat()
	{
		return vat;
	}

	public double calculateCostOfVat()
	{
		return price * vat;
	}

	public double calculateCostIncludingVat()
	{
		return price + calculateCostOfVat();
	}

	public String makePriceString()
	{
		return Util.asCurrency(Util.standardDoubleString(price));
	}

	public String makeVatString()
	{
		return Util.standardDoubleString(vat * 100) + "%";
	}

	public String makeCostOfVatString()
	{
		return Util.asCurrency(Util.standardDoubleString(calculateCostOfVat()));
	}

	public String makeCostIncludingVatString()
	{
		return Util.asCurrency(Util.standardDoubleString(calculateCostIncludingVat()));
	}
}
