package se.kth.iv1350.integration;

import se.kth.iv1350.model.SaleInfoDTO;

/**
 * {@link ExternalAccountingSystem} would represent an external accounting system if it were to be implemented.
 */
public class ExternalAccountingSystem
{
	/**
	 * {@link ExternalAccountingSystem} constructor.
	 */
	public ExternalAccountingSystem()
	{}

	/**
	 * Would have updated the accounting department responsible for sales if it were to be implemented.
	 *
	 * @param saleInfo Information about the sale.
	 */
	public void updateAccounts(SaleInfoDTO saleInfo)
	{
		if (saleInfo.getEmptyStatus())
		{
			System.out.println("No change was made to the external accounting system.");
		}
		else
		{
			System.out.println("Updated external accounting system.");
		}
	}
}
