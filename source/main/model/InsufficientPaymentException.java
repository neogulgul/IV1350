package se.kth.iv1350.model;

import se.kth.iv1350.util.Util;

/**
 * Exception for when a payment is insufficient to cover the cost of an entire sale.
 */
public class InsufficientPaymentException extends Exception
{
	/**
	 * Constructor.
	 * @param payment Customer payment.
	 * @param costOfEntireSale Cost of the entire sale.
	 */
	public InsufficientPaymentException(double payment, double costOfEntireSale)
	{
		super(
			"The payment of "
			+
			Util.asCurrency(Util.standardDoubleString(payment))
			+
			" was not enough to cover the whole cost of "
			+
			Util.asCurrency(Util.standardDoubleString(costOfEntireSale))
			+
			" for the entire sale."
			+
			"\n"
			+
			"Payment missing: "
			+
			Util.asCurrency(Util.standardDoubleString(costOfEntireSale - payment))
		);
	}
}
