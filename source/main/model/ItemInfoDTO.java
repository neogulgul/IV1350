package se.kth.iv1350.model;

import se.kth.iv1350.util.Util;

/**
 * DTO used for representing an items information.
 */
public class ItemInfoDTO
{
	private String name;
	private String description;
	private double price;
	private double vat;

	/**
	 * Constructor with results in valid item information.
	 *
	 * @param name Item name.
	 * @param description Item description.
	 * @param price Item price.
	 * @param vat Item VAT rate.
	 */
	public ItemInfoDTO(String name, String description, double price, double vat)
	{
		this.name        = name;
		this.description = description;
		this.price       = price;
		this.vat         = vat;
	}

	/**
	 * Getter for item name.
	 * @return Item name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Getter for item description.
	 * @return Item description.
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Getter for item price.
	 * @return Item price.
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * Getter for item VAT rate.
	 * @return Item VAT rate.
	 */
	public double getVat()
	{
		return vat;
	}

	/**
	 * Calculates cost of the item VAT rate.
	 * @return Cost of item VAT rate.
	 */
	public double calculateCostOfVat()
	{
		return price * vat;
	}

	/**
	 * Calculates cost of the item including VAT.
	 * @return Cost of item including VAT.
	 */
	public double calculateCostIncludingVat()
	{
		return price + calculateCostOfVat();
	}
}
