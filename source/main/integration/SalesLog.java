package se.kth.iv1350.integration;

import se.kth.iv1350.model.SaleInfoDTO;

/**
 * {@link SalesLog} would represent a sales log if it were to be implemented.
 */
public class SalesLog
{
	/**
	 * {@link SalesLog} constructor.
	 */
	public SalesLog()
	{}

	/**
	 * Would have logged the sale if it were to be implemented.
	 * @param saleInfo Sale information to be logged.
	 */
	public void logSale(SaleInfoDTO saleInfo)
	{
		System.out.println("Updated sales log.");
	}
}
