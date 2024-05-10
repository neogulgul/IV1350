package se.kth.iv1350.model;

/**
 * Interface for sale observers.
 */
public interface SaleObserver
{
	/**
	 * Notifies the observer that a sale has been completed.
	 * @param saleInfo Information about the completed sale.
	 */
	public void newCompletedSale(SaleInfoDTO saleInfo);
}
