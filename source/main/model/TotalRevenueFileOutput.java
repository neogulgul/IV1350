package se.kth.iv1350.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.util.Util;

/**
 * Sale observer that prints the total income since program start to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver
{
	private static final String TOTAL_REVENUE_FILENAME = "total-revenue-from-latest-run.txt";
	private PrintWriter printWriter;
	private double combinedCostOfAllSalesSinceProgramStart = 0;
	private int completedSales = 0;

	/**
	 * Constructor.
	 */
	public TotalRevenueFileOutput()
	{
		try
		{
			printWriter = new PrintWriter(new FileWriter(TOTAL_REVENUE_FILENAME), true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Updates and prints the total income since program start to a file.
	 * @param saleInfo Information about the completed sale.
	 */
	public void newCompletedSale(SaleInfoDTO saleInfo)
	{
		combinedCostOfAllSalesSinceProgramStart += saleInfo.getCostOfEntireSale();
		completedSales++;
		printWriter.println(String.format(
			"Total revenue after sale #%d: %s",
			completedSales,
			Util.asCurrency(Util.standardDoubleString(combinedCostOfAllSalesSinceProgramStart))
		));
	}
}
