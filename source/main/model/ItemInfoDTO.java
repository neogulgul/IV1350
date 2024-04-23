package se.kth.iv1350.model;

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
}
