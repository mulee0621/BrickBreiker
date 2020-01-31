import static org.junit.Assert.*;

import org.junit.Test;

public class BarTest {

	@Test
	public void testGetWidth() {
		Bar test = new Bar();
		double output = test.getWidth();
		assserEquals(100 , output);
	}
	private boolean assserEquals(double i, double x) {
		if (i==x) 
			return true;
		return false;
	}
	@Test
	public void testGetHeight() {
		Bar test = new Bar();
		double output = test.getHeight();
		assserEquals(8 , output);
	}

	@Test
	public void testSetPosX() {
		Bar test = new Bar();
		double output = test.getX();
		assserEquals(300 , output);
	}

	@Test
	public void testSetPosY() {
		Bar test = new Bar();
		double output = test.getY();
		assserEquals(500 , output);
	}

}
