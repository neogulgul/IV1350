package se.kth.iv1350.integration;

import se.kth.iv1350.util.Util;

/**
 * {@link Register} would represent a register if it were to be implemented.
 */
public class Register
{
	/**
	 * {@link Register} constructor.
	 */
	public Register()
	{}

	/**
	 * Would have increased the amount present in the register if it were to be implemented.
	 * @param amountToAdd Amount to increase by.
	 */
	public void increaseAmount(double amountToAdd)
	{
		if (amountToAdd != 0)
		{
			System.out.println("Amount in register increased by: " + Util.asCurrency(Util.standardDoubleString(amountToAdd)));
		}
	}
}
