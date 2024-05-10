package se.kth.iv1350.view;

import se.kth.iv1350.model.SaleInfoDTO;
import se.kth.iv1350.model.SaleObserver;
import se.kth.iv1350.util.Util;

/**
 * Sale observer that displays the total income since program start to the user interface.
 */
public class TotalRevenueView implements SaleObserver
{
	private double combinedCostOfAllSalesSinceProgramStart = 0;

	/**
	 * Constructor.
	 */
	public TotalRevenueView()
	{}

	/**
	 * Updates and displays the total income since program start to the user interface.
	 * @param saleInfo Information about the completed sale.
	 */
	public void newCompletedSale(SaleInfoDTO saleInfo)
	{
		combinedCostOfAllSalesSinceProgramStart += saleInfo.getCostOfEntireSale();
		System.out.println("Total revenue since start of program: " + Util.asCurrency(Util.standardDoubleString(combinedCostOfAllSalesSinceProgramStart)));
	}
}
