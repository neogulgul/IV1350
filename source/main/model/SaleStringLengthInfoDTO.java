package se.kth.iv1350.model;

/**
 * DTO representing information about string lengths of item information which is used by the printer to align text in the receipt.
 */
public class SaleStringLengthInfoDTO
{
	private int lengthOfLongestName;
	private int lengthOfLongestQuantity;
	private int lengthOfLongestCostBeforeDecimal;
	private int lengthOfLongestCostAfterDecimal;
	private int lengthOfLongestCombinedCostBeforeDecimal;
	private int lengthOfLongestCombinedCostAfterDecimal;

	/**
	 * Constructor
	 *
	 * @param lengthOfLongestName                      In a sale, length of longest name
	 * @param lengthOfLongestQuantity                  In a sale, length of longest quantity
	 * @param lengthOfLongestCostBeforeDecimal         In a sale, length of longest cost before decimal
	 * @param lengthOfLongestCostAfterDecimal          In a sale, length of longest cost after decimal
	 * @param lengthOfLongestCombinedCostBeforeDecimal In a sale, length of longest combined cost before decimal
	 * @param lengthOfLongestCombinedCostAfterDecimal  In a sale, length of longest combined cost after decimal
	 */
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

	/**
	 * Getter for length of longest name.
	 * @return Length of longest name.
	 */
	public int getLengthOfLongestName()
	{
		return lengthOfLongestName;
	}

	/**
	 * Getter for length of longest quantity.
	 * @return Length of longest quantity.
	 */
	public int getLengthOfLongestQuantity()
	{
		return lengthOfLongestQuantity;
	}

	/**
	 * Getter for length of longest cost before decimal.
	 * @return Length of longest cost before decimal.
	 */
	public int getLengthOfLongestCostBeforeDecimal()
	{
		return lengthOfLongestCostBeforeDecimal;
	}

	/**
	 * Getter for length of longest cost after decimal.
	 * @return Length of longest cost after decimal.
	 */
	public int getLengthOfLongestCostAfterDecimal()
	{
		return lengthOfLongestCostAfterDecimal;
	}

	/**
	 * Getter for length of longest combined cost before decimal.
	 * @return Length of longest combined cost before decimal.
	 */
	public int getLengthOfLongestCombinedCostBeforeDecimal()
	{
		return lengthOfLongestCombinedCostBeforeDecimal;
	}

	/**
	 * Getter for length of longest combined cost after decimal.
	 * @return Length of longest combined cost after decimal.
	 */
	public int getLengthOfLongestCombinedCostAfterDecimal()
	{
		return lengthOfLongestCombinedCostAfterDecimal;
	}
}
