package se.kth.iv1350.model;

public class SaleStringLengthInfoDTO
{
	private int lengthOfLongestName;
	private int lengthOfLongestQuantity;
	private int lengthOfLongestCostBeforeDecimal;
	private int lengthOfLongestCostAfterDecimal;
	private int lengthOfLongestCombinedCostBeforeDecimal;
	private int lengthOfLongestCombinedCostAfterDecimal;

	public SaleStringLengthInfoDTO(
		int lengthOfLongestName,
		int lengthOfLongestQuantity,
		int lengthOfLongestCostBeforeDecimal,
		int lengthOfLongestCostAfterDecimal,
		int lengthOfLongestCombinedCostBeforeDecimal,
		int lengthOfLongestCombinedCostAfterDecimal
	)
	{
		this.lengthOfLongestName                      = lengthOfLongestName;
		this.lengthOfLongestQuantity                  = lengthOfLongestQuantity;
		this.lengthOfLongestCostBeforeDecimal         = lengthOfLongestCostBeforeDecimal;
		this.lengthOfLongestCostAfterDecimal          = lengthOfLongestCostAfterDecimal;
		this.lengthOfLongestCombinedCostBeforeDecimal = lengthOfLongestCombinedCostBeforeDecimal;
		this.lengthOfLongestCombinedCostAfterDecimal  = lengthOfLongestCombinedCostAfterDecimal;
	}

	public int getLengthOfLongestName()
	{
		return lengthOfLongestName;
	}

	public int getLengthOfLongestQuantity()
	{
		return lengthOfLongestQuantity;
	}

	public int getLengthOfLongestCostBeforeDecimal()
	{
		return lengthOfLongestCostBeforeDecimal;
	}

	public int getLengthOfLongestCostAfterDecimal()
	{
		return lengthOfLongestCostAfterDecimal;
	}

	public int getLengthOfLongestCombinedCostBeforeDecimal()
	{
		return lengthOfLongestCombinedCostBeforeDecimal;
	}

	public int getLengthOfLongestCombinedCostAfterDecimal()
	{
		return lengthOfLongestCombinedCostAfterDecimal;
	}
}
