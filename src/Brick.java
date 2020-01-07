import java.awt.Color;
import acm.graphics.*;

public class Brick extends GRect{
	public Brick(double x, double y, Color c) {
		super(x, y, 50, 30);
		setFilled(true);
		setColor(c);	
	}
}

