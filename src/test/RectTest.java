import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectTest
{
	@Test
	public void testArea()
	{
		Rect rect = new Rect(0, 0, 10, 10);
		double area = rect.getArea();
		assertEquals(area, 100);
	}
}
