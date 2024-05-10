package se.kth.iv1350.model;

/**
 * DTO containing information for simulating a sale.
 */
public class SimulatedSaleDTO
{
	private String goodsFilepath;
	private String paymentFilepath;

	/**
	 * Constructor.
	 *
	 * @param goodsFilepath   The filepath to the textfile containing information about the customer goods.
	 * @param paymentFilepath The filepath to the textfile containing information about the customer payment.
	 */
	public SimulatedSaleDTO(String goodsFilepath, String paymentFilepath)
	{
		this.goodsFilepath = goodsFilepath;
		this.paymentFilepath = paymentFilepath;
	}

	/**
	 * Getter for goods filepath.
	 * @return Path to goods file.
	 */
	public String getGoodsFilepath()
	{
		return goodsFilepath;
	}

	/**
	 * Getter for payment filepath.
	 * @return Path to payment file.
	 */
	public String getPaymentFilepath()
	{
		return paymentFilepath;
	}
}
