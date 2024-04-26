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
	 * Constructor with no parameters which represents invalid item information.
	 */
	public ItemInfoDTO()
	{
		this.name = null;
	}

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
		this.name = name;
		this.description = description;
		this.price = price;
		this.vat = vat;
	}

	/**
	 * Check if the item information is valid.
	 * @return Whether or not the item information is indeed valid or not.
	 */
	public boolean isValid()
	{
		return name != null;
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

	/**
	 * Makes a <code>String</code> from the price.
	 * @return <code>String</code> of the price with a currency code appended.
	 */
	public String makePriceString()
	{
		return Util.asCurrency(Util.standardDoubleString(price));
	}

	/**
	 * Makes a <code>String</code> from the VAT rate.
	 * @return <code>String</code> of the VAT rate in percentage form.
	 */
	public String makeVatString()
	{
		return Util.standardDoubleString(vat * 100) + "%";
	}

	/**
	 * Makes a <code>String</code> from the VAT cost.
	 * @return <code>String</code> of the VAT cost with a currency code appended.
	 */
	public String makeCostOfVatString()
	{
		return Util.asCurrency(Util.standardDoubleString(calculateCostOfVat()));
	}

	/**
	 * Makes a <code>String</code> from the cost including VAT.
	 * @return <code>String</code> of the cost including VAT with a currency code appended.
	 */
	public String makeCostIncludingVatString()
	{
		return Util.asCurrency(Util.standardDoubleString(calculateCostIncludingVat()));
	}
}
