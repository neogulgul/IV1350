package se.kth.iv1350.model;

public class SaleStringLengthInfoDTO
{
	private int lengthOfLongestName;
	private int lengthOfLongestQuantity;
	private int lengthOfLongestPriceBeforeDecimal;
	private int lengthOfLongestPriceAfterDecimal;
	private int lengthOfLongestCombinedPriceBeforeDecimal;
	private int lengthOfLongestCombinedPriceAfterDecimal;

	public SaleStringLengthInfoDTO(
		int lengthOfLongestName,
		int lengthOfLongestQuantity,
		int lengthOfLongestPriceBeforeDecimal,
		int lengthOfLongestPriceAfterDecimal,
		int lengthOfLongestCombinedPriceBeforeDecimal,
		int lengthOfLongestCombinedPriceAfterDecimal
	)
	{
		this.lengthOfLongestName                       = lengthOfLongestName;
		this.lengthOfLongestQuantity                   = lengthOfLongestQuantity;
		this.lengthOfLongestPriceBeforeDecimal         = lengthOfLongestPriceBeforeDecimal;
		this.lengthOfLongestPriceAfterDecimal          = lengthOfLongestPriceAfterDecimal;
		this.lengthOfLongestCombinedPriceBeforeDecimal = lengthOfLongestCombinedPriceBeforeDecimal;
		this.lengthOfLongestCombinedPriceAfterDecimal  = lengthOfLongestCombinedPriceAfterDecimal;
	}

	public int getLengthOfLongestName()
	{
		return lengthOfLongestName;
	}

	public int getLengthOfLongestQuantity()
	{
		return lengthOfLongestQuantity;
	}

	public int getLengthOfLongestPriceBeforeDecimal()
	{
		return lengthOfLongestPriceBeforeDecimal;
	}

	public int getLengthOfLongestPriceAfterDecimal()
	{
		return lengthOfLongestPriceAfterDecimal;
	}

	public int getLengthOfLongestCombinedPriceBeforeDecimal()
	{
		return lengthOfLongestCombinedPriceBeforeDecimal;
	}

	public int getLengthOfLongestCombinedPriceAfterDecimal()
	{
		return lengthOfLongestCombinedPriceAfterDecimal;
	}
}
