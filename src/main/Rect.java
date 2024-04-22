/**
 * Rectangle representation
 */
public class Rect
{
	/**
	 * X coordinate
	 */
	public double x;
	/**
	 * Y coordinate
	 */
	public double y;
	/**
	 * Width
	 */
	public double w;
	/**
	 * Height
	 */
	public double h;

	/**
	 * Constructor
	 *
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param w width
	 * @param h height
	 */
	public Rect(double x, double y, double w, double h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	/**
	 * Rectangle area
	 * @return Rectangle area
	 */
	public double getArea()
	{
		return w * h;
	}
}
